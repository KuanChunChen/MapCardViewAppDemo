package k.c.mapcardviewdemo.app.network.model.login

import com.google.gson.annotations.SerializedName
import k.c.mapcardviewdemo.app.network.model.base.BaseHttpRequest

class AppLoginRequest : BaseHttpRequest() {

    @SerializedName("CompanyId")
    var companyId: Int? = 1
    @SerializedName("ServiceToken")
    var serviceToken: String? = null
    @SerializedName("CUSTPIN")
    var custpin: String? = null
    @SerializedName("CarID")
    var carID: String? = null
    @SerializedName("UDID")
    var uuid: String? = null
    @SerializedName("NcpmType")
    var ncpmType: Int? = null

    @SerializedName("Appversion")
    var appversion: String? = null
}