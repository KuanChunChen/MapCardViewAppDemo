package k.c.mapcardviewdemo.app.network.model.login

import com.google.gson.annotations.SerializedName
import k.c.mapcardviewdemo.app.network.model.base.BaseHttpRequest

class RefreshTokenRequest : BaseHttpRequest() {

    @field:SerializedName("ServiceToken")
    var serviceToken: String? = null

    @field:SerializedName("RefreshToken")
    var refreshToken: String? = null

    @field:SerializedName("CompanyId")
    val companyId: Int? = 1

    @field:SerializedName("UDID")
    var uDID: String? = null

}

