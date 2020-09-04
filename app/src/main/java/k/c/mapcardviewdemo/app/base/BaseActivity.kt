package k.c.mapcardviewdemo.app.base

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import k.c.mapcardviewdemo.R


abstract class BaseActivity : TransitionActivity() {



    @get:LayoutRes
    protected abstract val contentViewLayout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewLayout)
        initView()
        setStatusColor(this, isTranslate = true, isDarkText = true,
            bgColor = R.color.colorPrimaryDark
        )
        supportActionBar?.hide()
    }


    protected abstract fun initView()



    fun setStatusColor(
        activity: Activity,
        isTranslate: Boolean,
        isDarkText: Boolean, @ColorRes bgColor: Int
    ) {
        //如果系统为6.0及以上，就可以使用Android自带的方式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val window = activity.window
            val decorView = window.decorView
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS) //可有可无
            decorView.systemUiVisibility  = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = ContextCompat.getColor(
                activity,
                bgColor
            ) //Android5.0就可以用
        }
    }
}