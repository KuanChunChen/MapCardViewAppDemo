package k.c.mapcardviewdemo.app.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import k.c.mapcardviewdemo.R


class AnchorSheetBehavior<V : ViewGroup>(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<V>(context, attrs) {

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: V, dependency: View): Boolean {
        if (isBottomSheet(dependency)) {
            val behavior =
                (dependency.layoutParams as CoordinatorLayout.LayoutParams).behavior as BottomSheetBehavior<*>?

            val peekHeight = behavior?.peekHeight
//            Log.d("BottomSheetDemo", "peekHeight:$peekHeight")





            // The default peek height is -1, which
            // gets resolved to a 16:9 ratio with the parent
            val actualPeek =
                if (peekHeight!! >= 0) peekHeight else (parent.height * 1.0 / 16.0 * 9.0).toInt()
            if (dependency.top >= actualPeek) {
                // Only perform translations when the
                // view is between "hidden" and "collapsed" states
                val dy = dependency.top - parent.height + peekHeight
                ViewCompat.setTranslationY(child, (dy).toFloat())

                val imageNotification = child.findViewById<ImageView>(R.id.image_notification)
                ViewCompat.setTranslationY(imageNotification, -(dy).toFloat())


                val currentHeight =  parent.height - dependency.height
                val bottomSheetShiftDown =    dependency.top - currentHeight

                var alphaValue:Float = 1F - (bottomSheetShiftDown.toFloat()/(dependency.height - peekHeight).toFloat())

                var imageIcon  = dependency.findViewById<ImageView>(R.id.image_icon)
                imageIcon.alpha = alphaValue

                return true
            }
        }

        return false
    }




    private fun isBottomSheet(view: View): Boolean {
        val lp = view.layoutParams
        return if (lp is CoordinatorLayout.LayoutParams) {
            lp
                .behavior is BottomSheetBehavior<*>
        } else false
    }


}