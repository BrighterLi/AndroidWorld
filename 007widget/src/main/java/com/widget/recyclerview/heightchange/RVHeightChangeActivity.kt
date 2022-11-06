/**
 * User: BrightLi
 * Date: 2022-11-06-02:01
 * DESC: RecyclerView高度动态变化
 */

package com.widget.recyclerview.heightchange

import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.widget.databinding.ActivityRvheightChangeBinding

class RVHeightChangeActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "RVHeightChangeActivity"
    }

    private lateinit var viewBinding: ActivityRvheightChangeBinding
    private var msgList = mutableListOf<String>()
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
        msgList.add("8888888888888888888888888888")
    }

    private fun initView() {
        initMsgRvView()
        viewBinding.bottom1.setOnClickListener {
            TransitionManager.beginDelayedTransition(viewBinding.root)
            viewBinding.bottom1.visibility = View.GONE
            viewBinding.bottom2.visibility = View.VISIBLE
        }

        viewBinding.bottom2.setOnClickListener {
            TransitionManager.beginDelayedTransition(viewBinding.root)
            viewBinding.bottom1.visibility = View.VISIBLE
            viewBinding.bottom2.visibility = View.GONE
        }
    }

    private fun initMsgRvView() {
        viewBinding.rv.apply {
            layoutManager = msgRvLayoutManager
            adapter = msgListAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    Log.d(TAG, "[initMsgRvView] newState: $newState" )
                    if(newState == RecyclerView.SCROLL_STATE_IDLE) {
                        if(!canScrollVertically(1)) {
                            Log.d(TAG, "[initMsgRvView] to bottom newState: $newState")
                            TransitionManager.beginDelayedTransition(viewBinding.root)
                            viewBinding.bottom1.visibility = View.GONE
                            viewBinding.bottom2.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }
    }
}