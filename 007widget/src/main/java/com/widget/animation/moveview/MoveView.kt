package com.widget.animation.moveview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.TextView

/**
 * User: BrightLi
 * Date: 2022-11-19-01:05
 * DESC:
 */

/**
 * 自定义View实战之控件随手指滑动 https://blog.csdn.net/qq_36188774/article/details/99322848
 */
@SuppressLint("AppCompatCustomView")
class MoveView @JvmOverloads constructor(
     context: Context?,
     attrs: AttributeSet? = null,
     defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    var pLeft = 0
    var pTop = 0
    var pRight = 0
    var pBottom = 0
    var startX = 0
    var startY = 0

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                pLeft = getLeft()
                pTop = getTop()
                pRight = getRight()
                pBottom = getBottom()

                startX = Math.round(event.getRawX())
                startY = Math.round(event.getRawY())
            }
            MotionEvent.ACTION_MOVE -> {
                val moveX = Math.round(event.rawX)
                val moveY = Math.round(event.rawY)

                val dx = moveX - startX
                val dy = moveY - startY

                pLeft = pLeft + dx
                pTop = pTop + dy
                pRight = pRight + dx
                pBottom = pBottom + dy

                //更新布局，layout函数移动的只是控件的内容，并没有移动控件本身
                layout(pLeft, pTop, pRight, pBottom)

                startX = moveX
                startY = moveY
            }
        }
        return true
    }
}