package k.c.mapcardviewdemo.app.ui.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import k.c.mapcardviewdemo.R
import android.graphics.Bitmap


class CustomMarkerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    private lateinit var customMarkerView: View

    private var markerIcon: ImageView? = null

    init{

        customMarkerView = LayoutInflater.from(context).inflate(R.layout.item_custom_marker, this, true)
        markerIcon = customMarkerView!!.findViewById(R.id.image_marker) as ImageView
        val textSubject = customMarkerView!!.findViewById(R.id.text_subject) as TextView


    }




    fun getMarkerBitmapFromView(@DrawableRes resId: Int): Bitmap? {



        markerIcon!!.setImageResource(resId)

        var test = customMarkerView!!.layoutParams as FrameLayout.LayoutParams
        Log.d("teqteqt", test.width.toString())
        Log.d("teqteqt", test.height.toString())

//        val cache = customMarkerView.getDrawingCache()


//        val returnedBitmap = Bitmap.createBitmap(
//            customMarkerView!!.layoutParams.width,
//            customMarkerView!!.layoutParams.height,
//            Bitmap.Config.ARGB_8888
//        )
//        val canvas = Canvas(returnedBitmap)
//        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN)
//        val drawable = customMarkerView!!.background
//        if (drawable != null)
//            drawable!!.draw(canvas)
//        customMarkerView!!.draw(canvas)
        return null
    }

}