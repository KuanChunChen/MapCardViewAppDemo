package k.c.mapcardviewdemo.app.base.adapter

import android.content.Context
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import java.util.*

abstract class BaseListAdapter<T, V :RecyclerView.ViewHolder> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val headerCount = 1
    private val bottomCount = 1


    private val headerViews: SparseArray<View> = SparseArray()
    private val footerViews: SparseArray<View> = SparseArray()

    private val dataSet = ArrayList<T>()


    val contentItemCount: Int get() = dataSet.size

    @get:LayoutRes
    abstract val itemViewLayout: Int

    fun getItem(position: Int): T {
        return dataSet[position]
    }

    protected abstract fun getItemViewHolder(itemView: View): V

    override fun getItemCount(): Int {
        return dataSet.size + headerViews.size() + footerViews.size()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        if (isHeaderViewType(viewType)) {
            val headerView = headerViews.get(viewType)
            return createHeaderFooterViewHolder(headerView)
        }

        if (isFooterViewType(viewType)) {
            val footerView = footerViews.get(viewType)
            return createHeaderFooterViewHolder(footerView)
        }


        val itemView = createItemView(parent)
        return getItemViewHolder(itemView)
    }


    override fun getItemViewType(position: Int): Int {

        val dataItemCount = contentItemCount
        return if (headerCount != 0 && position < headerCount) {
            //頂部View
            ITEM_TYPE_HEADER
        } else if (bottomCount != 0 && position >= headerCount + dataItemCount) {
            //底部View
            ITEM_TYPE_BOTTOM
        } else {
            //内容View
            ITEM_TYPE_CONTENT
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var position = position

        if (isHeaderPosition(position) || isFooterPosition(position)) {
            return
        }

        position -= headerViews.size()


        val viewHolder = holder as BaseListAdapter<*, *>.BaseViewHolder
        viewHolder.bind(position)

    }


    private fun createItemView(parent: ViewGroup): View {

        //        itemView.setTag(getItemViewHolder(itemView));

        return LayoutInflater.from(parent.context).inflate(itemViewLayout, parent, false)
    }

    /**
     * update data.
     *
     * @param data
     */
    fun reset(data: List<T>?) {
        dataSet.clear()
        Log.d("reset :" , Gson().toJson(data))
        if (data != null) dataSet.addAll(data)
        notifyDataSetChanged()
    }

    /**
     * add data
     *
     * @param data
     */
    fun addAll(data: List<T>?) {
        if (data != null) dataSet.addAll(data)
        notifyDataSetChanged()
    }


    /**
     * Check is the recycler view position on footer.
     */
    private fun isFooterPosition(position: Int): Boolean {
        return position >= headerViews.size() + dataSet.size
    }

    /**
     * Check is the recycler view position on header.
     */
    private fun isHeaderPosition(position: Int): Boolean {
        return position < headerViews.size()
    }

    fun addHeaderView(view: View) {
        val position = headerViews.indexOfValue(view)
        if (position < 0) {
            headerViews.put(ITEM_TYPE_HEADER, view)
        }
        notifyDataSetChanged()
    }

    fun addFooterView(view: View) {
        val position = footerViews.indexOfValue(view)
        if (position < 0) {
            footerViews.put(ITEM_TYPE_BOTTOM, view)
        }
        notifyDataSetChanged()
    }

    private fun isHeaderViewType(viewType: Int): Boolean {
        val position = headerViews.indexOfKey(viewType)
        return position >= 0
    }

    private fun isFooterViewType(viewType: Int): Boolean {
        val position = footerViews.indexOfKey(viewType)
        return position >= 0
    }

    private fun createHeaderFooterViewHolder(view: View): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(view) {

        }
    }


    open abstract inner class BaseViewHolder(private val mItemView: View) : RecyclerView.ViewHolder(mItemView) {

        protected val context: Context get() = mItemView.context

        /**
         * callback and sent position to last layer.
         *
         * @param position
         */
        abstract fun bind(position: Int)
    }
    companion object {


        private const val ITEM_TYPE_HEADER = 0
        private const val ITEM_TYPE_CONTENT = 1
        private const val ITEM_TYPE_BOTTOM = 2
    }
}
