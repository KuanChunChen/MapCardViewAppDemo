package k.c.mapcardviewdemo.app.network.model.gps

import com.google.gson.annotations.SerializedName
import k.c.mapcardviewdemo.app.network.model.base.BaseHttpRequest

class FetchServiceTypeRequest :BaseHttpRequest() {

	@field:SerializedName("Lat")
	var latitude: Double? = null

	@field:SerializedName("Lng")
	var longitude: Double? = null
}
