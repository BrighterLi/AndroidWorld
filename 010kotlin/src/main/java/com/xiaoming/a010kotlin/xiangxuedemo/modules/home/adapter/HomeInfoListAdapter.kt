package com.xiaoming.a010kotlin.xiangxuedemo.modules.home.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.xiaoming.a010kotlin.R
import android.widget.BaseAdapter
import android.widget.TextView;

import com.xiaoming.a010kotlin.xiangxuedemo.entity.HomeDataResponse

//“首页” 列表的适配器
class HomeInfoListAdapter private constructor(): BaseAdapter() {

    // 数据
    private lateinit var mNews : List<HomeDataResponse.NewsListBean>
    private lateinit var context: Context

    constructor(context: Context, mNews: List<HomeDataResponse.NewsListBean>) : this() {
        this.mNews = mNews
        this.context = context
    }

    // View ?  允许你返回null
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View ? {
        var viewHolder: ViewHolder

        var view : View? = null

        if (view == null) {
            view = View.inflate(this.context, R.layout.home_list_item, null)

            viewHolder = ViewHolder
            viewHolder.newsText = view.findViewById(R.id.news_text);
            viewHolder.newDate = view.findViewById(R.id.news_date);

            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }

        // 设置条目数据
        val news : HomeDataResponse.NewsListBean = mNews.get(position);
        viewHolder.newsText.setText(news.title);
        viewHolder.newDate.setText(news.create_time);
        return view
    }

    override fun getItem(position: Int): Any = mNews.get(position)

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = mNews.size

    // ListView Item
    object ViewHolder {
        lateinit var newsText: TextView // 标题
        lateinit var newDate: TextView // 时间
    }

}