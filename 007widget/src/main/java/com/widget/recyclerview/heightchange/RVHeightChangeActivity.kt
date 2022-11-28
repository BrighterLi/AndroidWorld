/**
 * User: BrightLi
 * Date: 2022-11-06-02:01
 * DESC: RecyclerView高度动态变化;滑动到底部状态变化；smooth滑动
 * https://blog.csdn.net/weixin_36110673/article/details/117633153
 * ScrollView滑动到底部继续向上滑和滑动到顶部继续向下滑 https://blog.csdn.net/XiFangzheng/article/details/53543800
 */

package com.widget.recyclerview.heightchange

import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.widget.databinding.ActivityRvheightChangeBinding

class RVHeightChangeActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "RVHeightChangeActivity"
        private const val STATE_RV_EXPANDED = 0
        private const val STATE_RV_NOT_EXPANDED = 1
    }

    private lateinit var viewBinding: ActivityRvheightChangeBinding
    private var msgList = mutableListOf<String>()
    private var curRvState = STATE_RV_NOT_EXPANDED
    private var rvScrollState = -1

    private var startX = 0
    private var startY = 0
    private var offsetX = 0
    private var offsetY = 0

    private val msgRvLayoutManager by lazy {
        LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }
    private val msgListAdapter by lazy {
        RvAdapter(msgList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRvheightChangeBinding.inflate(layoutInflater)
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
        msgList.add(
            "8888888888888888888888888888ddddddddfffffffffffffffffffffffeeeeeeeeeeeeeeeee" +
                    "eeeeeeeeettttttttttttttttttttttttttttttttttttqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq" +
                    "qqqqqqqqqwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwweeeeeeeeeeee" +
                    "eeeeeeeeeeeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrryyyyyyyyyyyyyyyyyyyyyyyyyyyy" +
                    "yyyyyygggggggggggggggggggggggg"
        )
    }

    private fun initView() {
        initMsgRvView()

        curRvState = STATE_RV_NOT_EXPANDED
        viewBinding.bottom1.visibility = View.VISIBLE
        viewBinding.bottom2.visibility = View.GONE
        viewBinding.bottom1.setOnClickListener {
            Log.d(TAG, "[initView] bottom1 click curRvState: $curRvState")
            if (curRvState == STATE_RV_NOT_EXPANDED) {
                TransitionManager.beginDelayedTransition(viewBinding.root)
                viewBinding.bottom1.visibility = View.GONE
                viewBinding.bottom2.visibility = View.VISIBLE
                curRvState = STATE_RV_EXPANDED
                Log.d(TAG, "[initView] bottom1 2 click curRvState: $curRvState")
                smoothScrollMsgRvToBottom()
            }
        }

        viewBinding.bottom2.setOnClickListener {
            Log.d(TAG, "[initView] bottom2 click curRvState: $curRvState")
            /*if (curRvState == STATE_RV_EXPANDED) {
                TransitionManager.beginDelayedTransition(viewBinding.root)
                viewBinding.bottom1.visibility = View.VISIBLE
                viewBinding.bottom2.visibility = View.GONE
                curRvState = STATE_RV_NOT_EXPANDED
                Log.d(TAG, "[initView] bottom2 2 click curRvState: $curRvState")
                smoothScrollMsgRvToBottom()
            }*/
        }

        viewBinding.rv.setOnTouchListener { v, event ->
            Log.d(TAG, "[initView] rv touch1 curRvState: $curRvState offsetY: $offsetY")
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startX = event.x.toInt()
                    startY = event.y.toInt()
                }
                MotionEvent.ACTION_UP -> {
                    offsetX = (event.x - startX).toInt()
                    offsetY = (event.y - startY).toInt()
                    /*if(offsetY > 1 && curRvState == STATE_RV_NOT_EXPANDED) {
                        Log.d(TAG, "[initView] rv touch2 curRvState: $curRvState offsetY: $offsetY")
                        viewBinding.bottom1.visibility = View.VISIBLE
                        viewBinding.bottom2.visibility = View.GONE
                        curRvState = STATE_RV_EXPANDED
                        true
                    }*/
                }
            }
            false
        }

        /*viewBinding.rv.setOnClickListener {
            Log.d(TAG, "[initView] rv click curRvState: $curRvState")
            if(curRvState == STATE_RV_NOT_EXPANDED) {
                TransitionManager.beginDelayedTransition(viewBinding.root)
                viewBinding.bottom1.visibility = View.GONE
                viewBinding.bottom2.visibility = View.VISIBLE
                curRvState = STATE_RV_EXPANDED
                Log.d(TAG, "[initView] rv 2 click curRvState: $curRvState")
                smoothScrollMsgRvToBottom()
            }
        }*/
    }

    private fun initMsgRvView() {
        viewBinding.rv.apply {
            layoutManager = msgRvLayoutManager
            adapter = msgListAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    rvScrollState = newState
                    Log.d(TAG, "[initMsgRvView] curRvState: $curRvState offsetY: $offsetY newState: $newState")
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && curRvState == STATE_RV_EXPANDED && offsetY < 0) {
                        if (!canScrollVertically(1)) {
                            Log.d(TAG, "[initMsgRvView] to bottom curRvState: $curRvState")
                            TransitionManager.beginDelayedTransition(viewBinding.root)
                            viewBinding.bottom1.visibility = View.VISIBLE
                            viewBinding.bottom2.visibility = View.GONE
                            curRvState = STATE_RV_NOT_EXPANDED
                            //smoothScrollMsgRvToBottom()
                            Log.d(TAG, "[initMsgRvView] to bottom curRvState2: $curRvState")
                        }
                    }
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    Log.d(TAG, "[initMsgRvView] onScrolled curRvState: $curRvState offsetY: $offsetY dy: $dy rvScrollState: $rvScrollState")
                    /*if (rvScrollState == RecyclerView.SCROLL_STATE_IDLE && curRvState == STATE_RV_EXPANDED && offsetY < 0) {
                        if (!canScrollVertically(1)) {
                            Log.d(TAG, "[initMsgRvView] to bottom curRvState: $curRvState")
                            TransitionManager.beginDelayedTransition(viewBinding.root)
                            viewBinding.bottom1.visibility = View.VISIBLE
                            viewBinding.bottom2.visibility = View.GONE
                            curRvState = STATE_RV_NOT_EXPANDED
                            //smoothScrollMsgRvToBottom()
                            Log.d(TAG, "[initMsgRvView] onScrolled curRvState2: $curRvState")
                        }
                    }*/
                }
            })
        }
    }

    private fun scrollMsgRvToBottom() {
        viewBinding.rv.scrollToPosition(msgListAdapter.data.size - 1)
    }

    private fun smoothScrollMsgRvToBottom() {
        viewBinding.rv.smoothScrollToPosition(msgListAdapter.data.size - 1)
    }

}