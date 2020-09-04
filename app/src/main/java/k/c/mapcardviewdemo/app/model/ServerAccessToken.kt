package k.c.mapcardviewdemo.app.model

import com.google.gson.annotations.SerializedName

data class ServerAccessToken(

	@field:SerializedName("KeyToken")
	val keyToken: String? = null,

	@field:SerializedName("RefreshToken")
	val refreshToken: String? = null,

	@field:SerializedName("ExpiredUTCT")
	val expiredUTCT: Long? = null,

	@field:SerializedName("AccessToken")
	val accessToken: String? = null,

	@field:SerializedName("Expired")
	val expired: String? = null,

	@field:SerializedName("ServerUTCT")
	val serverUTCT: Long? = null
)
