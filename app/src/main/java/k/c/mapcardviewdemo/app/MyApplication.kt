package k.c.mapcardviewdemo.app

import android.app.Application
import k.c.mapcardviewdemo.app.base.CommonLib

class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        CommonLib.init(this)

    }



}