package models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RedditResponse {
    @SerializedName("data")
    @Expose
    var data: Data? = null

}