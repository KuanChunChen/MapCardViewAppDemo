package k.c.mapcardviewdemo.app.CommonBusiness

import k.c.mapcardviewdemo.app.model.*

object CommonSingle{


    /**
     *  uuid
     * */

    var UUID: String = ""

    var refreshToken: String? = null


    var customerProfile: CustomerProfile = CustomerProfile()

    var serverAccessToken: ServerAccessToken = ServerAccessToken()


    var signature: Signature = Signature()

    var mapConfig: MapConfig = MapConfig()

    var temporaryPin = TemporaryPin()


}