package k.c.mapcardviewdemo.app.manager

import android.location.Location
import android.location.LocationManager


class MainMapManager{


    companion object{
        fun getBestLocation(): Location? {

            val gpsLoc = LocationManagerHelper.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            val networkLoc = LocationManagerHelper.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

            return if (LocationManagerHelper.isBetterLocation(gpsLoc, networkLoc)) gpsLoc else networkLoc
        }


    }


}