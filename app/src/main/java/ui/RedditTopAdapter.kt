package ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import butryn.reddittop.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.reddit_post_item.view.*
import models.RedditData
import utils.Utils.setVisibility


class RedditTopAdapter(private var context: Context) :
    RecyclerView.Adapter<RedditTopAdapter.ViewHolder>() {

    private var viewHolder: ViewHolder? = null
    private var redditTop: ArrayList<RedditData> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.reddit_post_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = redditTop.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val top = redditTop[position]?.redditPost
        val pastTime = top?.created!!.toLong() * 1000

        val timeAgo = DateUtils.getRelativeTimeSpanString(
            pastTime,
            System.currentTimeMillis(),
            DateUtils.HOUR_IN_MILLIS
        )

        if (top.thumbnail == "default") {
            holder.thumbnail.setImageResource(R.drawable.img_not_found)
        } else {
            Picasso.get().load(top.thumbnail).into(holder.thumbnail)
        }

        holder.subreddit.text = top.subreddit
        holder.title.text = top.title
        holder.author.text = top.author + " posted " + timeAgo
        holder.rating.text = top.ups.toString()
        holder.comments.text = top.numComments.toString()
        holder.item.setOnClickListener {
            val builder = CustomTabsIntent.Builder()
            builder.setToolbarColor(ContextCompat.getColor(context, R.color.mainAppColor))
            builder.setShowTitle(true)
            builder.addDefaultShareMenuItem()
            val customTabsIntent = builder.build()
            customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            customTabsIntent.launchUrl(
                context,
                Uri.parse(POST_URL + top.permalink)
            )
        }
    }

    fun setData(list: List<RedditData>) {
        this.redditTop.addAll(list)
        notifyDataSetChanged()
    }

    fun clearData() {
        this.redditTop.clear()
    }

    fun showPaginationProgress(isVisible: Boolean) {
        viewHolder?.apply {
            setVisibility(updateProgress!!, isVisible, true)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item: CardView = itemView.container
        val thumbnail: ImageView = itemView.iv_thumbnail
        val title: TextView = itemView.tv_title
        val subreddit: TextView = itemView.tv_subreddit
        val author: TextView = itemView.author
        val rating: TextView = itemView.tv_rating
        val comments: TextView = itemView.tv_comments
        var updateProgress: ProgressBar? = itemView.update_progress
    }

    companion object {
        const val POST_URL = "https://www.reddit.com/"
    }
}
