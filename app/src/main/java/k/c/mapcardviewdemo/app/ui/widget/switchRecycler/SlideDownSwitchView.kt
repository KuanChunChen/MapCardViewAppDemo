package k.c.mapcardviewdemo.app.ui.widget.switchRecycler

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.Glide
import k.c.mapcardviewdemo.R
import k.c.mapcardviewdemo.app.base.dialog.ShowDialogManager
import k.c.mapcardviewdemo.app.model.SwitchViewModel
import k.c.mapcardviewdemo.app.ui.widget.CenterLayoutManager
import kotlinx.android.synthetic.main.partial_bottom_card.view.*
import java.util.*

class SlideDownSwitchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {



    private lateinit var centerLayoutManager : CenterLayoutManager


    init{

        LayoutInflater.from(context).inflate(R.layout.partial_bottom_card, this, true)

        initRecyclerView()

    }


    private fun initRecyclerView(){


        var switchViewList = getList()


        val spaceItemDecoration = SpaceItemDecoration(9)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(switchView)


        val switchRecyclerViewAdapter =
            object : SwitchRecyclerViewAdapter() {
                override fun onItemViewClick(position: Int, itemView: View) {

//                    centerLayoutManager.smoothScrollToPosition(switchView, RecyclerView.State(), position)
//                    centerLayoutManager.scrollToPositionWithOffset(position,spaceItemDecoration.sideVisibleWidth)

//                    switchRecyclerViewAdapter.notifyItemChanged(position, 1)
//                    testLoadView(position,switchViewList)
                }
            }

        switchRecyclerViewAdapter.reset(switchViewList)


        centerLayoutManager = CenterLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)



        switchView.adapter = switchRecyclerViewAdapter
        switchView.layoutManager = centerLayoutManager
        switchView.addItemDecoration(spaceItemDecoration)


        val currentPosition = switchView.adapter!!.itemCount / 2

        val offset = spaceItemDecoration.sideVisibleWidth



        centerLayoutManager.scrollToPositionWithOffset(currentPosition,offset)

        switchView.post{

            val galleryScrollerListener = object : SwitchRecyclerScrollerListener(spaceItemDecoration.mItemConsumeX) {
                override fun changeView(position: Int) {

                    testLoadView(position,switchViewList)

                }

            }


            switchView.addOnScrollListener(galleryScrollerListener)

            galleryScrollerListener.setItemAnim(switchView,currentPosition,0f)

            galleryScrollerListener.updatePosition(currentPosition)

        }


        btn_confirm.setOnClickListener {
            context?.let { ShowDialogManager.showToasterHint(it, "您好，我看到您了", "司機 陳建和(TDH-1333)") }

        }


    }

    open fun setTest(){

    }




    /***
     * 此線以下的數個function皆為測試用
     * TODO 屆時設為可設項
     * */

    private fun getList(): List<SwitchViewModel> {

        val titleList = ArrayList<SwitchViewModel>()
        val titleArray = arrayOf("微搬家", "酒後代駕", "現在叫車", "預約叫車","機場送機","代接電","代叫車")
        val buttonTextArray = arrayOf("輸入下車點", "輸入乘客資訊", "叫車", "輸入下車點","選擇欲前往機場","選擇CC數","輸入乘客資訊")
        val imageIconArray = arrayOf(
            R.drawable.ic_home_services_moving,
            R.drawable.ic_home_services_designated_a,
            R.drawable.ic_home_services_taxi_01,
            R.drawable.ic_home_services_booking,
            R.drawable.ic_home_services_airport,
            R.drawable.ic_home_services_plug,
            R.drawable.ic_home_services_assist)


        for (i in 0..6) {
            val switchViewModel = SwitchViewModel()
            switchViewModel.textTitle = titleArray[i]
            switchViewModel.buttonText = buttonTextArray[i]
            switchViewModel.imageIcon = imageIconArray[i]

            titleList.add(switchViewModel)
        }
        return titleList

    }





    private fun testLoadView(position: Int , switchViewList: List<SwitchViewModel>) {

        btn_confirm.text = switchViewList[position].buttonText
        Glide.with(context).load<Any>(switchViewList[position].imageIcon).into(image_icon)

    }


}