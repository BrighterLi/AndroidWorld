package com.widget.recyclerview.heightchange

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.widget.R
import com.widget.recyclerview.heightchange.RvAdapter.*
import kotlinx.android.synthetic.main.item_base.view.*

/**
 * User: BrightLi
 * Date: 2022-11-06-20:42
 * DESC:
 */
class RvAdapter(var data: List<String>) : RecyclerView.Adapter<MyHolder>() {

    /**
     * 返回每一个item的布局
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_height, parent, false)
        return MyHolder(itemView)
    }

    /**
     * 将控件和数据绑定
     */
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.itemView.tv.text = data[position]
    }

    /**
     * 返回item数量
     */
    override fun getItemCount(): Int {
        return data.size
    }

    /**
     * 绑定控件
     * 定义一些控件来绑定itemview的控件，但在kotlin中不用findviewbyid,所以不用绑定
     */
    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}