package k.c.mapcardviewdemo.app.base

import android.app.Application
import android.content.Context

object CommonLib {

    @Volatile
    private var hasInit = false

    lateinit var appContext: Context


    /**
     * Init
     */
    fun init(application: Application) {
        if (!hasInit) {
            appContext = application
            hasInit = true
        }
    }



}
