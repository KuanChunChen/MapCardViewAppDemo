package k.c.mapcardviewdemo.app.util

import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.pm.PackageInfoCompat.getLongVersionCode
import k.c.mapcardviewdemo.app.base.CommonLib


class PackageUtil{

    companion object {
        fun getVersionName(): String {

            var versionName = "0.0"

            try {
                val packageInfo = CommonLib.appContext.packageManager.getPackageInfo(CommonLib.appContext.packageName, 0)

                if (packageInfo != null) {

                    versionName = packageInfo!!.versionName
                    val longVersionCode = getLongVersionCode(packageInfo)
                    val versionCode = longVersionCode.toInt()
                    Log.d(
                        "test 123",
                        "versionName : $versionName ,versionCode : $versionCode "
                    )
                }
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }



            return versionName
        }



        fun getVersionCode(): Int {

             var versionCode = 0

            try {
                val packageInfo = CommonLib.appContext.packageManager.getPackageInfo(CommonLib.appContext.packageName, 0)

                if (packageInfo != null) {

                    val longVersionCode = getLongVersionCode(packageInfo)
                    versionCode = longVersionCode.toInt()
                    Log.d(
                        "test 123",
                        "versionCode : $versionCode "
                    )
                }
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }



            return versionCode
        }
    }

}