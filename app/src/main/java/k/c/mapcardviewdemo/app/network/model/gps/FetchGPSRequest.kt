package k.c.mapcardviewdemo.app.network.model.gps

import com.google.gson.annotations.SerializedName
import k.c.mapcardviewdemo.app.network.model.base.BaseHttpRequest

class FetchGPSRequest : BaseHttpRequest() {

    @SerializedName("mode")
    var mode: Int? = null
    @SerializedName("lng")
    var longitude: Double? = null
    @SerializedName("lat")
    var latitude: Double? = null
    @SerializedName("compFavorite")
    var compFavorite: Boolean? = false
    @SerializedName("scenarioType")
    var scenarioType: Int? = null
}
