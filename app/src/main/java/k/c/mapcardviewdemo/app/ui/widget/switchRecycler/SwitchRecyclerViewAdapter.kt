package k.c.mapcardviewdemo.app.ui.widget.switchRecycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import k.c.mapcardviewdemo.R
import k.c.mapcardviewdemo.app.base.adapter.BaseListAdapter
import k.c.mapcardviewdemo.app.model.SwitchViewModel

abstract class SwitchRecyclerViewAdapter : BaseListAdapter<SwitchViewModel, SwitchRecyclerViewAdapter.ViewHolder>() {




    override val itemViewLayout: Int get() = R.layout.item_text


    override fun getItemViewHolder(itemView: View): ViewHolder {
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        viewHolder.bind(position)
    }

    inner class ViewHolder(var mItemView: View) : BaseViewHolder(mItemView) {

        var textTitle : TextView  = mItemView.findViewById(R.id.text_title)

        override fun bind(position: Int) {

            textTitle.text = getItem(position % contentItemCount).textTitle

            mItemView.setOnClickListener {
                onItemViewClick(position, mItemView)
            }


        }


    }

    abstract fun onItemViewClick(position: Int, itemView: View)
}
