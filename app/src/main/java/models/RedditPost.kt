package models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RedditPost {
    @SerializedName("subreddit")
    @Expose
    var subreddit: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("ups")
    @Expose
    var ups: Int? = null
    @SerializedName("created")
    @Expose
    var created: Double? = null
    @SerializedName("author")
    @Expose
    var author: String? = null
    @SerializedName("permalink")
    @Expose
    var permalink: String? = null
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null
    @SerializedName("num_comments")
    @Expose
    var numComments: Int? = null


}