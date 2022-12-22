package com.widget.recyclerview.scroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.widget.databinding.ActivityScrollBinding
import com.widget.recyclerview.heightchange.RvAdapter

class RvScrollControlActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityScrollBinding
    private var msgList = mutableListOf<String>()

    private val msgRvLayoutManager by lazy {
        LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }
    private val msgListAdapter by lazy {
        RvAdapter(msgList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityScrollBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initData()
        initView()
    }

    private fun initData() {
        msgList.add("1111111111111")
        msgList.add("22222222222222")
        msgList.add("333333333333333")
        msgList.add("4444444444444444444")
        msgList.add("555555555555555555")
        msgList.add("66666666666666666666666")
        msgList.add("7777777777777777777777777")
        msgList.add("8888888888888888888888888888")
        msgList.add("9999999999999999999999999999999999")
        msgList.add("111111111111111111111111111111111")
        msgList.add("1111111111111")
        msgList.add("22222222222222")
        msgList.add("333333333333333")
        msgList.add("4444444444444444444")
        msgList.add("555555555555555555")
        msgList.add("66666666666666666666666")
        msgList.add("7777777777777777777777777")
        /*msgList.add(
            "8888888888888888888888888888ddddddddfffffffffffffffffffffffeeeeeeeeeeeeeeeee" +
                    "eeeeeeeeettttttttttttttttttttttttttttttttttttqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq" +
                    "qqqqqqqqqwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwweeeeeeeeeeee" +
                    "eeeeeeeeeeeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrryyyyyyyyyyyyyyyyyyyyyyyyyyyy" +
                    "yyyyyygggggggggggggggggggggggg"
        )
        msgList.add("1111111111111")
        msgList.add("22222222222222")
        msgList.add("333333333333333")
        msgList.add("4444444444444444444")
        msgList.add("555555555555555555")
        msgList.add("66666666666666666666666")
        msgList.add("7777777777777777777777777")
        msgList.add("8888888888888888888888888888")
        msgList.add("9999999999999999999999999999999999")
        msgList.add("111111111111111111111111111111111")
        msgList.add("1111111111111")
        msgList.add("22222222222222")
        msgList.add("333333333333333")
        msgList.add("4444444444444444444")
        msgList.add("555555555555555555")
        msgList.add("66666666666666666666666")
        msgList.add("7777777777777777777777777")
        msgList.add("1111111111111")
        msgList.add("22222222222222")
        msgList.add("333333333333333")
        msgList.add("4444444444444444444")
        msgList.add("555555555555555555")
        msgList.add("66666666666666666666666")
        msgList.add("7777777777777777777777777")
        msgList.add("8888888888888888888888888888")
        msgList.add("9999999999999999999999999999999999")
        msgList.add("111111111111111111111111111111111")
        msgList.add("1111111111111")
        msgList.add("22222222222222")
        msgList.add("333333333333333")
        msgList.add("4444444444444444444")
        msgList.add("555555555555555555")
        msgList.add("66666666666666666666666")
        msgList.add("7777777777777777777777777")*/
    }

    private fun initView() {
        initMsgRvView()

        //scrollMsgRvToBottom()
        viewBinding.button.setOnClickListener {
            changeViewHeight(viewBinding.button)
            smoothScrollMsgRvToBottom()
        }
    }

    private fun initMsgRvView() {
        viewBinding.rvScroll.apply {
            layoutManager = msgRvLayoutManager
            adapter = msgListAdapter
        }
    }

    private fun scrollMsgRvToBottom() {
        viewBinding.rvScroll.scrollToPosition(msgListAdapter.data.size - 1)
    }

    private fun smoothScrollMsgRvToBottom() {
        viewBinding.rvScroll.smoothScrollToPosition(msgListAdapter.data.size - 1)
    }

    private fun changeViewHeight(view: View) {
        val linearParams: ViewGroup.LayoutParams = view.layoutParams
        linearParams.height = dip2px(200f)
        view.layoutParams = linearParams
    }

    private fun dip2px(value: Float): Int {
        val scale = resources.displayMetrics.density
        return (scale * value + 0.5f).toInt()
    }
}