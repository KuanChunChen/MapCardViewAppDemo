package k.c.mapcardviewdemo.app.network.model.gps

import com.google.gson.annotations.SerializedName

data class FetchServiceTypeResult(

	@field:SerializedName("SrvTypeCode")
	val srvTypeCode: Int? = null,

	@field:SerializedName("ShowOIM")
	val showOIM: Boolean? = null,

	@field:SerializedName("OrderInfoMsg")
	val orderInfoMsg: String? = null
)

