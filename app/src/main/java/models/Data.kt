package models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {
    @SerializedName("dist")
    @Expose
    var dist: Int? = null
    @SerializedName("children")
    @Expose
    var redditData: List<RedditData>? = null
    @SerializedName("after")
    @Expose
    var after: String? = null
}