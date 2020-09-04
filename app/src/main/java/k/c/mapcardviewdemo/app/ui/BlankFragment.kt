package k.c.mapcardviewdemo.app.ui

import android.os.Bundle
import k.c.mapcardviewdemo.R
import k.c.mapcardviewdemo.app.base.BaseFragment


class BlankFragment(override val contentViewLayout: Int) : BaseFragment() {


    override fun initView(savedInstanceState: Bundle?) {

    }



    companion object {

        @JvmStatic
        fun newInstance() =
            BlankFragment(R.layout.fragment_blank)
    }

}
