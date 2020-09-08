package k.c.mapcardviewdemo.app.manager

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import k.c.mapcardviewdemo.R

import android.widget.TextView




class MarkerManager {

    enum class MarkerType(val value: Int) {
        HOME(1),
        FAVORITE(2),
        SHOP(3),
        HISTORY(4),
        LBS(5),
        AIRPORT(6),
        SCHEDULE(7);
    }

    companion object{

        fun getCustomerMarker(context: Context, @DrawableRes id :Int , subjectText :String ): Bitmap {


            var customMarkerView = LayoutInflater.from(context).inflate(R.layout.item_custom_marker,null)
            var imageView = customMarkerView.findViewById(R.id.image_marker) as ImageView
            var textSubject = customMarkerView.findViewById<TextView>(R.id.text_subject)
            imageView.setImageResource(id)
            textSubject.text = subjectText
//            imageView.setImageResource()
            customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            customMarkerView.layout(0, 0, customMarkerView.measuredWidth, customMarkerView.measuredHeight)
//            customMarkerView.buildDrawingCache();

            //Define a bitmap with the same size as the view
            val returnedBitmap =
                Bitmap.createBitmap(customMarkerView.width, customMarkerView.height, Bitmap.Config.ARGB_8888)

            //Bind a canvas to it
            val canvas = Canvas(returnedBitmap)
            //Get the view's background
            val bgDrawable = customMarkerView.background
            if (bgDrawable != null)
            //has background drawable, then draw it on the canvas
                bgDrawable!!.draw(canvas)
            else
            //does not have background drawable, then draw white background on the canvas
                canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN)

            // draw the view on the canvas
            customMarkerView.draw(canvas)
            //return the bitmap
            return returnedBitmap
        }

        fun setMarkerIcon(){
            var markerIconNormal = arrayOf(
                R.drawable.ic_map_location_home,
                R.drawable.ic_map_location_saved,
                R.drawable.ic_map_location_bz,
                R.drawable.ic_map_location_history,
                R.drawable.ic_map_location_lbs,
                R.drawable.ic_map_location_saved,
                R.drawable.ic_map_location_lbs)
            var markerIconBoarding = arrayOf(R.drawable.ic_map_location_home)
            var markerIconGetOff = arrayOf(R.drawable.ic_map_location_home)

        }

    }
}