package k.c.mapcardviewdemo.app.ui.widget.switchRecycler

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.Typeface
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import k.c.mapcardviewdemo.R


abstract class SwitchRecyclerScrollerListener(private val itemWith: Int ) : RecyclerView.OnScrollListener() {

    private var scrolledWidth = 0
    private var mPosition = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

//        Log.d("test","dx : $dx , dy : $dy")
        setScrollInfo(recyclerView, dx)

    }

    abstract fun changeView(position: Int)



    fun updatePosition(currentPosition: Int) {
        mPosition = currentPosition
        scrolledWidth = 0

    }


    private fun setScrollInfo(recyclerView: RecyclerView, dx: Int) {
        scrolledWidth += dx

        // 位置移動數值(-1 - 0 - 1) =（單一物件距離(-(單一物件長度)-(單一物件長度) / 單一物件長度
        val offset = scrolledWidth.toFloat() / itemWith.toFloat()

        val percent = if (offset > 0) {
            offset - offset.toInt()
        } else {
            1f + offset
        }

        var movementPosition = 0
        if (offset > 0) {
            if ((offset.toInt() > 0)) {
                mPosition += offset.toInt()
                scrolledWidth -= itemWith
                movementPosition = mPosition
            } else {
                movementPosition += mPosition
            }

        } else if (offset < 0) {
            movementPosition--
            if (offset.toInt() < 0) {
                mPosition += offset.toInt()
                scrolledWidth += itemWith
                movementPosition = mPosition
            } else {
                movementPosition += mPosition
            }
        }

        setItemAnim(recyclerView, movementPosition, percent)


    }
    private fun setTextViewStyles(textView: TextView, colors: IntArray ,position :FloatArray  , isBold :Boolean) {

        if (colors == null || position == null) {

            return
        }

        val mLinearGradient = LinearGradient(
            0f,
            0f,
            textView.paint.textSize * textView.text.length,
            0f,
            colors,
            position,
            Shader.TileMode.CLAMP
        )
        textView.paint.shader = mLinearGradient
        textView.invalidate()


        if (isBold) {

            textView.setTypeface(textView.typeface, Typeface.BOLD)

        } else {
            textView.setTypeface(null, Typeface.NORMAL)

        }
    }


    fun setItemAnim(recyclerView: RecyclerView, position: Int, percent: Float) {

        // 中間頁面
        var mCurView = recyclerView.layoutManager!!.findViewByPosition(position)

        // 右邊頁面
        var mRightView = recyclerView.layoutManager!!.findViewByPosition(position + 1)
        // 左邊頁面
        var mLeftView = recyclerView.layoutManager!!.findViewByPosition(position - 1)

        var mRRView = recyclerView.layoutManager!!.findViewByPosition(position + 2)

        var mLLView = recyclerView.layoutManager!!.findViewByPosition(position - 2)



        if (mRRView != null) {
            var textView :TextView = mRRView!!.findViewById(R.id.text_title)

            setTextViewStyles(
                textView,
                intArrayOf(Color.parseColor("#ababab"), Color.parseColor("#00000000")),
                floatArrayOf(0.0f, 0.5f),
                false
            )
        }

        if (mLLView != null) {
            var textView :TextView = mLLView!!.findViewById(R.id.text_title)

            setTextViewStyles(
                textView,
                intArrayOf(Color.parseColor("#00000000"), Color.parseColor("#ababab")),
                floatArrayOf(0.5f, 1.0f),
                false
            )

        }


        if (mLeftView != null) {
            var textView :TextView = mLeftView!!.findViewById(R.id.text_title)

            setTextViewStyles(
                textView,
                intArrayOf(Color.parseColor("#ababab"), Color.parseColor("#ababab")),
                floatArrayOf(0.5f, 1.0f),
                false
            )
        }

        if (mRightView != null) {
            var textView :TextView = mRightView!!.findViewById(R.id.text_title)

            setTextViewStyles(
                textView,
                intArrayOf(Color.parseColor("#ababab"), Color.parseColor("#ababab")),
                floatArrayOf(0.5f, 1.0f),
                false
            )
        }

        if (mCurView != null) {
            var textView :TextView = mCurView!!.findViewById(R.id.text_title)
            setTextViewStyles(
                textView,
                intArrayOf(Color.parseColor("#000000"), Color.parseColor("#000000")),
                floatArrayOf(0.5f, 1.0f),
                true
            )
        }

        changeView(position)
    }



}
