package com.xiaoming.a010kotlin.xiangxuedemo.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ListView

class CustomListView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :

/*{

    // 传递一个 也能帮你掉i用 三个参数的构造函数
    CustomListView(this)

}*/

        ListView(context, attrs, defStyleAttr)
{
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {


        // 测量高度   解决显示不全问题（16 网络复杂的）
        // Java
        // int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        // KT
        // shl(bits) – 左移位 (Java’s <<)
        // shr(bits) – 右移位 (Java’s >>)
        val expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE shr(2), View.MeasureSpec.AT_MOST)

        super.onMeasure(widthMeasureSpec, expandSpec)
    }
}