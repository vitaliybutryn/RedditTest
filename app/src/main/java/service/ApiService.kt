package service

import models.RedditResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val REDDIT_POST_LIMIT = 25
    }

    @GET("/top/.json")
    fun getRedditTop(@Query("limit") limit: Int = REDDIT_POST_LIMIT)
            : Call<RedditResponse>

    @GET("/top/.json")
    fun getRedditTopPagination(@Query("limit") limit: Int = REDDIT_POST_LIMIT,
                               @Query ("after") after: String)
            : Call<RedditResponse>
}