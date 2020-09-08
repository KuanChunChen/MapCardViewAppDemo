package k.c.mapcardviewdemo.app.network.model.gps

import com.google.gson.annotations.SerializedName

data class QueryMarkerResult(

	@field:SerializedName("DrawGeohash")
	val drawGeoHash: List<String?>? = null,

	@field:SerializedName("Data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("Result")
	val result: Result? = null
)

data class MarkListItem(

	@field:SerializedName("Geocode")
	val geocode: Geocode? = null,

	@field:SerializedName("MarkName")
	val markName: String? = null,

	@field:SerializedName("MapIcon")
	val mapIcon: String? = null,

	@field:SerializedName("SCStoreInfo")
	val sCStoreInfo: SCStoreInfo? = null,

	@field:SerializedName("BannerIcon")
	val bannerIcon: String? = null,

	@field:SerializedName("MarkType")
	val markType: Int? = null,

	@field:SerializedName("SubName")
	val subName: String? = null,

	@field:SerializedName("FavAddrInfo")
	val favAddrInfo: FavAddrInfo? = null
)

data class Addr(

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

data class SCStoreInfo(

	@field:SerializedName("OnPromAct2")
	val onPromAct2: Boolean? = null,

	@field:SerializedName("OnPromAct1")
	val onPromAct1: Boolean? = null,

	@field:SerializedName("HasPromote")
	val hasPromote: Boolean? = null,

	@field:SerializedName("Lng")
	val lng: Double? = null,

	@field:SerializedName("ShopNa")
	val shopNa: String? = null,

	@field:SerializedName("Logo1Url")
	val logo1Url: String? = null,

	@field:SerializedName("Logo2Url")
	val logo2Url: String? = null,

	@field:SerializedName("Addr")
	val addr: String? = null,

	@field:SerializedName("PMMsg")
	val pMMsg: String? = null,

	@field:SerializedName("AddrGisInfo")
	val addrGisInfo: AddrGisInfo? = null,

	@field:SerializedName("CSID")
	val cSID: Int? = null,

	@field:SerializedName("InfoBtnText")
	val infoBtnText: String? = null,

	@field:SerializedName("ShopType")
	val shopType: Int? = null,

	@field:SerializedName("WebSite")
	val webSite: String? = null,

	@field:SerializedName("InfoBtnUrl")
	val infoBtnUrl: String? = null,

	@field:SerializedName("StoreNa")
	val storeNa: String? = null,

	@field:SerializedName("LBSSiteUrl")
	val lBSSiteUrl: String? = null,

	@field:SerializedName("Lat")
	val lat: Double? = null,

	@field:SerializedName("IsPartner")
	val isPartner: Boolean? = null
)

data class FavAddrInfo(

	@field:SerializedName("Type")
	val type: Int? = null,

	@field:SerializedName("Id")
	val id: Int? = null,

	@field:SerializedName("Memo")
	val memo: String? = null,

	@field:SerializedName("Addr")
	val addr: Addr? = null,

	@field:SerializedName("Name")
	val name: String? = null
)

data class Result(

	@field:SerializedName("Msg")
	val msg: String? = null,

	@field:SerializedName("State")
	val state: Int? = null
)

data class Geocode(

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

data class DataItem(

	@field:SerializedName("MarkList")
	val markList: List<MarkListItem?>? = null,

	@field:SerializedName("GeoHash")
	val geoHash: String? = null
)

data class AddrGisInfo(

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
