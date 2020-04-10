package butryn.reddittop

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import interfaces.EndlessRecyclerOnScrollListener
import kotlinx.android.synthetic.main.activity_main.*
import models.RedditData
import models.RedditResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import service.ApiService
import ui.RedditTopAdapter

class MainActivity : AppCompatActivity() {

    companion object {
        const val REDDIT_POSTS_LIMIT = 25
        const val BASE_URL = "https://www.reddit.com"
    }

    private lateinit var recyclerAdapter: RedditTopAdapter
    private var mScrollListener: EndlessRecyclerOnScrollListener? = null
    private var retrofit: Retrofit? = null
    private var paginationAfter: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        initRetrofit()
        loadData()
        initRecycler()
        initRefresh()
    }

    private fun initRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun initToolbar() {
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Reddit Top"
            setIcon(resources.getDrawable(R.drawable.actionbar_space_between_icon_and_title))
        }
    }

    private fun loadData() {
        progressBar.visibility = View.VISIBLE
        val api = retrofit?.create(ApiService::class.java)
        api?.getRedditTop(REDDIT_POSTS_LIMIT)!!.enqueue(object : Callback<RedditResponse> {
            override fun onResponse(call: Call<RedditResponse>, response: Response<RedditResponse>) {
                scrollListener(true)
                paginationAfter = response.body()?.data?.after
                if (response.body()?.data!!.redditData!!.size < REDDIT_POSTS_LIMIT) {
                    endOfList()
                }
                showData(response.body()?.data?.redditData)
            }

            override fun onFailure(call: Call<RedditResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun initRecycler() {
        recyclerAdapter = RedditTopAdapter(applicationContext)
        initScrollListener()
        recycler_data.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = recyclerAdapter
            addOnScrollListener(mScrollListener!!)
        }
    }

    private fun showData(body: List<RedditData>?) {
        progressBar.visibility = View.GONE
        recyclerAdapter.setData(body!!)
    }

    private fun initRefresh() {
        swipe_refresh.setOnRefreshListener {
            clearPaginationLoadedPosts()
            loadData()
            swipe_refresh.isRefreshing = false
        }
    }

    private fun initScrollListener() {
        mScrollListener = object : EndlessRecyclerOnScrollListener() {
            override fun showUpdateProgress(isVisible: Boolean) {
                recyclerAdapter.showPaginationProgress(isVisible)
            }

            override fun onLoadMore() {
                loadMore()
            }
        }
        mScrollListener!!.paginationLimit = REDDIT_POSTS_LIMIT
    }

    private fun endOfList() {
        mScrollListener?.isLastPage = true
    }

    private fun clearPaginationLoadedPosts() {
        mScrollListener?.isLastPage = false
        recyclerAdapter.clearData()
    }

    private fun scrollListener(isEnable: Boolean) {
        mScrollListener?.isLoading = !isEnable
    }

    private fun loadMore() {
        val api = retrofit?.create(ApiService::class.java)
        api?.getRedditTopPagination(after = paginationAfter!!)?.enqueue(object : Callback<RedditResponse> {
            override fun onResponse(call: Call<RedditResponse>, response: Response<RedditResponse>) {
                paginationAfter = response.body()?.data?.after
                if (response.body()?.data!!.redditData!!.size < REDDIT_POSTS_LIMIT) {
                    endOfList()
                }
                scrollListener(false)
                showData(response.body()?.data?.redditData)
            }

            override fun onFailure(call: Call<RedditResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}