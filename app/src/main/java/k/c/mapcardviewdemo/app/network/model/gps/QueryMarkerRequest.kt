package k.c.mapcardviewdemo.app.network.model.gps

import com.google.gson.annotations.SerializedName
import k.c.mapcardviewdemo.app.network.model.base.BaseHttpRequest

class QueryMarkerRequest : BaseHttpRequest(){

	@field:SerializedName("ZoomValue")
	var zoomValue: Float? = null

	@field:SerializedName("SouthWestLat")
	var southWestLat: Double? = null

	@field:SerializedName("NorthEastLat")
	var northEastLat: Double? = null

	@field:SerializedName("SouthWestLng")
	var southWestLng: Double? = null

	@field:SerializedName("NorthEastLng")
	var northEastLng: Double? = null

	@field:SerializedName("ExistingGeoHash")
	var existingGeoHash: Array<String>? = null

	@field:SerializedName("MarkType")
	var markerType: IntArray? = null

	@field:SerializedName("ScenarioType")
	var scenarioType: Int? = null





}
