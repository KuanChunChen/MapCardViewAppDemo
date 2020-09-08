package k.c.mapcardviewdemo.app.manager

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import k.c.mapcardviewdemo.app.base.CommonLib

class PermissionManager

    /**
     * @param activity       for it activity
     * @param premissions all permission that would check and request
     * @param requestCode    this time's request code
     */

    (activity: Activity, permissions: Array<String>, requestCode: Int) {
    init {
        if (!checkPermission(permissions)) {
            requestPermissions(activity, permissions, requestCode)
        }
    }



    companion object {


        /**
         * @param permissions the permission that need to open
         * @param requestCode    the request code
         */

        fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int) {

            ActivityCompat.requestPermissions(activity, permissions, requestCode)
        }


        /**
         * @param premissions
         * @return true : all permission are granted.
         */
        fun checkPermission(premissions: Array<String>): Boolean {
            for (singlePermission in premissions) {
                if (ActivityCompat.checkSelfPermission(
                        CommonLib.appContext,
                        singlePermission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
            return true

        }
    }


}