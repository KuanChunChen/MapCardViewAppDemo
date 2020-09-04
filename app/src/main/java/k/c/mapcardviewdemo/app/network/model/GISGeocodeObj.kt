package k.c.mapcardviewdemo.app.network.model

import java.io.Serializable

class GISGeocodeObj : Serializable {

    var address: String? = null
    var city: String? = null
    var lat: Double? = null
    var lng: Double? = null
    var memo: String? = null
    var provider: Int? = null
    var providerKey: String? = null
    var accuracy: Double? = null
    var addrId: String? = null
    var flag: Int? = null

    val roadDetail: String
        get() {
            var addressDetail = address
            var firstArea = 0

            addressDetail = addressDetail!!.substring(3)

            if (addressDetail.substring(0, 4).contains("區")) {
                firstArea = addressDetail.indexOf("區")
            } else if (addressDetail.substring(0, 4).contains("市")) {
                firstArea = addressDetail.indexOf("市")
            } else if (addressDetail.substring(0, 4).contains("鎮")) {
                firstArea = addressDetail.indexOf("鎮")
            } else if (addressDetail.substring(0, 4).contains("鄉")) {
                firstArea = addressDetail.indexOf("鄉")
            }

            addressDetail = addressDetail.substring(firstArea + 1)

            return addressDetail
        }

    val roadTitle: String
        get() {
            val addressTitleLength = address!!.length - roadDetail.length

            return address!!.substring(0, addressTitleLength)
        }
}
