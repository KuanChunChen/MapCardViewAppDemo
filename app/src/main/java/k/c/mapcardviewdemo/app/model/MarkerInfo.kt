package k.c.mapcardviewdemo.app.model

import com.google.gson.annotations.SerializedName
import k.c.mapcardviewdemo.app.network.model.gps.MarkListItem

data class MarkerInfo(


    @field:SerializedName("GeoHash")
    var geoHash: String? = null,

    @field:SerializedName("DrawGeohash")
    var drawGeoHash: List<String?>? = null,

    @field:SerializedName("MarkList")
    var markList: List<MarkListItem?>? = null


)