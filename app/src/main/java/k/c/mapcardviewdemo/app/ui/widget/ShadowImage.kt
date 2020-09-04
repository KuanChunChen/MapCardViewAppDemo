package k.c.mapcardviewdemo.app.ui.widget

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView
import android.util.Log
import k.c.mapcardviewdemo.R


class ShadowImage @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr){

    var paint = Paint()

    init{
        paint.color = Color.BLACK
        this.setLayerType(LAYER_TYPE_SOFTWARE,null)

    }

//    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
//        paint.setShadowLayer(10F, 15F, 15F, Color.GRAY);
//
//
//        var rect = RectF(0F, 0F, 200F, 200F)
//
//        canvas?.drawRoundRect(rect, 75F, 75F,paint)
//    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                this.setColorFilter(R.color.moudle_common_view_color_orange_red)
                //重寫觸控事件的方法,當按鈕被點選的時候
                mOnClickListener?.onClick()
                Log.d("tasett","123434test")
                return true
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> this.colorFilter = null
        }
        return super.onTouchEvent(event)
    }

    interface OnClickListener {
        fun onClick()
    }

    private var mOnClickListener: OnClickListener? = null
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.mOnClickListener = onClickListener
    }

}