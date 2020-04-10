package models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Media {
    @SerializedName("reddit_video")
    @Expose
    var redditVideo: RedditVideo? = null

}