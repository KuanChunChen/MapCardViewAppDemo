package k.c.mapcardviewdemo.app.network.model.login

import com.google.gson.annotations.SerializedName
import k.c.module.http.BaseResult

data class RefreshTokenResult(

	@field:SerializedName("KeyToken")
	val keyToken: String? = null,

	@field:SerializedName("ExpiredUTCT")
	val expiredUTCT: Long? = null,

	@field:SerializedName("TmpPin")
	val tmpPin: TmpPin? = null,

	@field:SerializedName("AccessToken")
	val accessToken: String? = null,

	@field:SerializedName("Signature")
	val signature: Signature? = null,

	@field:SerializedName("Expired")
	val expired: String? = null,

	@field:SerializedName("ServerUTCT")
	val serverUTCT: Long? = null,

	@field:SerializedName("Result")
	val result: BaseResult? = null
)


