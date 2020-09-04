package k.c.mapcardviewdemo.app.model

import com.google.gson.annotations.SerializedName

data class TemporaryPin(

    @field:SerializedName("ExpiredUTCT")
    val expiredUTCT: Long? = null,

    @field:SerializedName("TmpPin")
    val tmpPin: String? = null
)