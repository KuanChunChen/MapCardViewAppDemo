package k.c.mapcardviewdemo.app.network.api

interface ApiPath {


    companion object {

        val APP_SETTING = "/api/AppSetting"
        const val DIRECT_DISPATCH_AUTH = "/DispatchAuth"
        const val DISPATCH_ORDER = "/DispatchOrder"
        const val DIRECTORY_GIS = "/GIS"
        const val APP_API = "AppApi"
        const val NCPM = "/NCPM"

        val DISPATCH_LOGIN = "/AppLogin"
        val DISPATCH_REFRESH_TOKEN = "/RefreshToken"
        const val GET_TOKEN_BY_LOGIN = "/GetTokenByLogin"
        val DISPATCH_SETTING_COMMON = "/Settings/Common"

        val DISPATCH_ORDER_COMP = "/COMP"
        val DISPATCH_ORDER_CONFIRM_RULE = "/ConfirmRule"
        val DISPATCH_ORDER_DIS_TYPE_RULES = "/DisTypeRules"
        val DISPATCH_ORDER_GOOGLE_GECOCODEING = "/GoogleGeocoding"
        val DISPATCH_ORDER_GOOGLE_DIRECT = "/GoogleDirection"
        val DISPATCH_ORDER_SRV_TYPE_LIST = "/SrvTypeList"
        val DISPATCH_ORDER_LAST_ORDER_INFO = "/LastOrderInfo"
        val DISPATCH_ORDER_DEFAULT_RULES = "/DefaultRules"
    }

}