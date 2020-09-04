package k.c.mapcardviewdemo.app.util

import k.c.mapcardviewdemo.app.CommonBusiness.CommonSingle
import java.util.*

object TokenUtil{

    fun isTokenExpired(): Boolean {

        if (CommonSingle.serverAccessToken.accessToken == null) {
            return true
        }


        if (CommonSingle.serverAccessToken.expiredUTCT == null) {
            return true
        }



        val expired = CommonSingle.serverAccessToken.expiredUTCT


        if (getServerTimeLong() - expired!! < 0) {
            return true
        }

        return false
    }


    private fun getServerTimeLong(): Long {


        val locationTimeDate = Calendar.getInstance().timeInMillis
        val gapTime = CommonSingle.serverAccessToken.serverUTCT?.minus(locationTimeDate)


        val calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Taipei"))
        calendar.add(Calendar.MILLISECOND, gapTime!!.toInt())
        return calendar.timeInMillis
    }

}