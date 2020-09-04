package k.c.mapcardviewdemo.app.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class CenterLayoutManager : LinearLayoutManager {

    constructor(context: Context) : super(context)

    constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(
        context,
        orientation,
        reverseLayout
    )

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State?, position: Int) {

        val smoothScroller = CenterSmoothScroller(recyclerView.context)
        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }


    private class CenterSmoothScroller internal constructor(context: Context) :
        LinearSmoothScroller(context) {

        private val SMOOTH_SPEED_PER_PIXEL = 100f


//        override fun calculateDtToFit(viewStart: Int, viewEnd: Int, boxStart: Int, boxEnd: Int, snapPreference: Int): Int {
//            return boxStart + (boxEnd - boxStart) / 2 - (viewStart + (viewEnd - viewStart) / 2)
//        }

        /**
         * 控制滑動速度
         */
        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
            return SMOOTH_SPEED_PER_PIXEL / displayMetrics.densityDpi
        }
    }

}