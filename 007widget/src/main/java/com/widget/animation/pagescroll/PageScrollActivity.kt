package com.widget.animation.pagescroll

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.widget.databinding.ActivityPageScrollBinding

/**
 * Android 仿微信侧滑关闭页面效果: https://blog.csdn.net/qwe511455842/article/details/49496511
 */
class PageScrollActivity : AppCompatActivity() {
    companion object {
      private const val TAG = "PageScrollActivity"
    }
    private lateinit var viewBinding: ActivityPageScrollBinding
    private var gestureDetector: GestureDetector? = null
    private var rootView: View? = null
    private var windowWidth = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPageScrollBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        rootView = window.decorView
        windowWidth = window.windowManager.defaultDisplay.width
       scrollEvent()
    }

    private fun scrollEvent() {
        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onScroll(
                e1: MotionEvent,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                rootView?.translationX = e2.x
                if (e2.x > windowWidth - 20) {
                    Log.d(TAG, "[scrollEvent]")
                   finish()
                }
                return super.onScroll(e1, e2, distanceX, distanceY)
            }
        })
    }

    @SuppressLint("ObjectAnimatorBinding")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        //退出当前Activity
        if (event.x > windowWidth / 2 ) {
            var moveIn = ObjectAnimator.ofFloat(rootView, "translationX", event.x, windowWidth.toFloat())
            moveIn.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    finish()
                }
            })
            moveIn.setDuration(500).start()
        } else if (event.x < windowWidth / 2) { //反弹回来
            ObjectAnimator.ofFloat(rootView, "translationX", event.x, 0f).setDuration(500).start()
        }
        //gestureDetector?.onTouchEvent(event)
        return true
    }
}