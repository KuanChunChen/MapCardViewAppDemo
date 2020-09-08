package k.c.mapcardviewdemo.app.manager

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import androidx.core.content.PermissionChecker
import k.c.mapcardviewdemo.app.base.CommonLib

object LocationManagerHelper{


    private val FIFTEEN_MINUTES = 1000 * 900
    private val MAX_ACCURACY_TOLERATE_RANGE = 100
    private val MAX_ACCURACY_TOLERATE_DELTA = 30

    private val mLocationManager: LocationManager = CommonLib.appContext.getSystemService(
        Context.LOCATION_SERVICE) as LocationManager


    fun isLocationEnabled(): Boolean {
        return isLocationPermissionGranted() && (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || mLocationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        ))

    }


    fun isLocationPermissionGranted(): Boolean {

        return PermissionChecker.checkSelfPermission(CommonLib.appContext, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PermissionChecker.PERMISSION_GRANTED ||
                PermissionChecker.checkSelfPermission(CommonLib.appContext, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PermissionChecker.PERMISSION_GRANTED
    }


    fun getLastKnownLocation(provider: String): Location? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && CommonLib.appContext.checkSelfPermission(
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            null
        } else mLocationManager.getLastKnownLocation(provider)
    }

    fun isBetterLocation(location: Location?, currentBestLocation: Location?): Boolean {

        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true
        } else if (location == null) {
            return false
        }

        // Check whether the new location fix is newer or older
        val timeDelta = location.time - currentBestLocation.time
        val isSignificantlyNewer = timeDelta > FIFTEEN_MINUTES
        val isSignificantlyOlder = timeDelta < -FIFTEEN_MINUTES
        val isNewer = timeDelta > 0

        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true
            // If the new location is more than two minutes older, it must be worse
        } else if (isSignificantlyOlder) {
            return false
        }

        // Check whether the new location fix is more or less accurate
        val accuracyDelta = (location.accuracy - currentBestLocation.accuracy).toInt()
        val isLessAccurate = accuracyDelta > 0 && accuracyDelta < MAX_ACCURACY_TOLERATE_DELTA
        val isMoreAccurate = accuracyDelta <= 0
        val isSignificantlyLessAccurate = accuracyDelta > MAX_ACCURACY_TOLERATE_DELTA

        // Check if the old and new location are from the same provider
        val isFromSameProvider = location.provider == currentBestLocation.provider

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true
        } else if (isNewer && isLessAccurate) {
            return true
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true
        }
        return false
    }
}