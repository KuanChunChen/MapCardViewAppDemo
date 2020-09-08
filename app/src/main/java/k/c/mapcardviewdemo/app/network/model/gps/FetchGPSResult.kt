package k.c.mapcardviewdemo.app.network.model.gps

import com.google.gson.annotations.SerializedName


data class FetchGPSResult(

	@field:SerializedName("Accuracy")
	val accuracy: Double? = null,

	@field:SerializedName("Lng")
	val lng: Double? = null,

	@field:SerializedName("Address")
	val address: String? = null,

	@field:SerializedName("ProviderKey")
	val providerKey: String? = null,

	@field:SerializedName("AddrId")
	val addrId: String? = null,

	@field:SerializedName("City")
	val city: String? = null,

	@field:SerializedName("Flag")
	val flag: Int? = null,

	@field:SerializedName("Lat")
	val lat: Double? = null,

	@field:SerializedName("Memo")
	val memo: String? = null,

	@field:SerializedName("Provider")
	val provider: Int? = null
)

