package models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AllAwarding {
    @SerializedName("giver_coin_reward")
    @Expose
    var giverCoinReward: Int? = null
    @SerializedName("subreddit_id")
    @Expose
    var subredditId: Any? = null
    @SerializedName("is_new")
    @Expose
    var isNew: Boolean? = null
    @SerializedName("days_of_drip_extension")
    @Expose
    var daysOfDripExtension: Int? = null
    @SerializedName("coin_price")
    @Expose
    var coinPrice: Int? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("penny_donate")
    @Expose
    var pennyDonate: Int? = null
    @SerializedName("coin_reward")
    @Expose
    var coinReward: Int? = null
    @SerializedName("icon_url")
    @Expose
    var iconUrl: String? = null
    @SerializedName("days_of_premium")
    @Expose
    var daysOfPremium: Int? = null
    @SerializedName("icon_height")
    @Expose
    var iconHeight: Int? = null
    @SerializedName("icon_width")
    @Expose
    var iconWidth: Int? = null
    @SerializedName("start_date")
    @Expose
    var startDate: Any? = null
    @SerializedName("is_enabled")
    @Expose
    var isEnabled: Boolean? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("end_date")
    @Expose
    var endDate: Any? = null
    @SerializedName("subreddit_coin_reward")
    @Expose
    var subredditCoinReward: Int? = null
    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("icon_format")
    @Expose
    var iconFormat: String? = null
    @SerializedName("award_sub_type")
    @Expose
    var awardSubType: String? = null
    @SerializedName("penny_price")
    @Expose
    var pennyPrice: Int? = null
    @SerializedName("award_type")
    @Expose
    var awardType: String? = null

}