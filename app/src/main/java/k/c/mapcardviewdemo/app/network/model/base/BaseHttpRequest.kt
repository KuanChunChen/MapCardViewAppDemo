package k.c.mapcardviewdemo.app.network.model.base

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class BaseHttpRequest : Serializable {




    @SerializedName("UserId")
    var userId: String? = null

    var accessToken: String? = null

    @SerializedName("Signature")
    var signature: String? = null

    var keyToken: String? = null

    @SerializedName("Caller")
    var caller = "APP55688-Android"

}