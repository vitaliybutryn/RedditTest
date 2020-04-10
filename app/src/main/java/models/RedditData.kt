package models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RedditData {
    @SerializedName("data")
    @Expose
    var redditPost: RedditPost? = null
}