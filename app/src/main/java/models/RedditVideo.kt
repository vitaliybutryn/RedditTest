package models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RedditVideo {
    @SerializedName("fallback_url")
    @Expose
    var fallbackUrl: String? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null
    @SerializedName("scrubber_media_url")
    @Expose
    var scrubberMediaUrl: String? = null
    @SerializedName("dash_url")
    @Expose
    var dashUrl: String? = null
    @SerializedName("duration")
    @Expose
    var duration: Int? = null
    @SerializedName("hls_url")
    @Expose
    var hlsUrl: String? = null
    @SerializedName("is_gif")
    @Expose
    var isGif: Boolean? = null
    @SerializedName("transcoding_status")
    @Expose
    var transcodingStatus: String? = null

}