package com.widget.animation.pagescroll.demo2

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.Scroller
import kotlin.math.abs

/**
 * User: BrightLi
 * Date: 2023-02-13-00:14
 * DESC:
 */
class SildingFinishLayout @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet?,
    defStyle: Int = 0
) :
    RelativeLayout(context, attrs, defStyle), View.OnTouchListener {

    /**
     * SildingFinishLayout布局的父布局
     */
    private var mParentView: ViewGroup? = null

    /**
     * 处理滑动逻辑的View
     */
    private var touchView: View? = null

    /**
     * 滑动的最小距离
     */
    private val mTouchSlop: Int

    /**
     * 按下点的X坐标
     */
    private var downX = 0

    /**
     * 按下点的Y坐标
     */
    private var downY = 0

    /**
     * 临时存储X坐标
     */
    private var tempX = 0

    /**
     * 滑动类
     */
    private val mScroller: Scroller

    /**
     * SildingFinishLayout的宽度
     */
    private var viewWidth = 0

    /**
     * 记录是否正在滑动
     */
    private var isSilding = false
    private var onSildingFinishListener: OnSildingFinishListener? = null
    private var isFinish = false


    init {
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
        mScroller = Scroller(context)
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        if (changed) {
            // 获取SildingFinishLayout所在布局的父布局
            mParentView = this.parent as ViewGroup
            viewWidth = this.width
        }
    }

    /**
     * 设置OnSildingFinishListener, 在onSildingFinish()方法中finish Activity
     *
     * @param onSildingFinishListener
     */
    fun setOnSildingFinishListener(
        onSildingFinishListener: OnSildingFinishListener?
    ) {
        this.onSildingFinishListener = onSildingFinishListener
    }

    /**
     * 设置Touch的View
     *
     * @param touchView
     */
    fun setTouchView(touchView: View) {
        this.touchView = touchView
        touchView.setOnTouchListener(this)
    }

    fun getTouchView(): View? {
        return touchView
    }

    /**
     * 滚动出界面
     */
    private fun scrollRight() {
        val delta: Int = viewWidth + mParentView!!.getScrollX()
        // 调用startScroll方法来设置一些滚动的参数，我们在computeScroll()方法中调用scrollTo来滚动item
        mScroller.startScroll(
            mParentView!!.getScrollX(), 0, -delta + 1, 0,
            Math.abs(delta)
        )
        postInvalidate()
    }

    /**
     * 滚动到起始位置
     */
    private fun scrollOrigin() {
        val delta: Int = mParentView!!.scrollX
        mScroller.startScroll(
            mParentView!!.scrollX, 0, -delta, 0,
            abs(delta)
        )
        postInvalidate()
    }

    /**
     * touch的View是否是AbsListView， 例如ListView, GridView等其子类
     *
     * @return
     */
    private fun isTouchOnAbsListView(): Boolean {
        return if (touchView is AbsListView) true else false
    }

    /**
     * touch的view是否是ScrollView或者其子类
     *
     * @return
     */
    private fun isTouchOnScrollView(): Boolean {
        return if (touchView is ScrollView) true else false
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                run {
                    tempX = event.rawX.toInt()
                    downX = tempX
                }
                downY = event.rawY.toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                val moveX = event.rawX.toInt()
                val deltaX = tempX - moveX
                tempX = moveX
                if (Math.abs(moveX - downX) > mTouchSlop
                    && Math.abs(event.rawY.toInt() - downY) < mTouchSlop
                ) {
                    isSilding = true

                    // 若touchView是AbsListView，
                    // 则当手指滑动，取消item的点击事件，不然我们滑动也伴随着item点击事件的发生
                    if (isTouchOnAbsListView()) {
                        val cancelEvent = MotionEvent.obtain(event)
                        cancelEvent.action = (MotionEvent.ACTION_CANCEL
                                or (event.actionIndex shl MotionEvent.ACTION_POINTER_INDEX_SHIFT))
                        v.onTouchEvent(cancelEvent)
                    }
                }
                if (moveX - downX >= 0 && isSilding) {
                    mParentView!!.scrollBy(deltaX, 0)

                    // 屏蔽在滑动过程中ListView ScrollView等自己的滑动事件
                    if (isTouchOnScrollView() || isTouchOnAbsListView()) {
                        return true
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                isSilding = false
                if (mParentView!!.getScrollX() <= -viewWidth / 2) {
                    isFinish = true
                    scrollRight()
                } else {
                    scrollOrigin()
                    isFinish = false
                }
            }
        }

        // 假如touch的view是AbsListView或者ScrollView 我们处理完上面自己的逻辑之后
        // 再交给AbsListView, ScrollView自己处理其自己的逻辑
        return if (isTouchOnScrollView() || isTouchOnAbsListView()) {
            v.onTouchEvent(event)
        } else true

        // 其他的情况直接返回true
    }

    override fun computeScroll() {
        // 调用startScroll的时候scroller.computeScrollOffset()返回true，
        if (mScroller.computeScrollOffset()) {
            mParentView!!.scrollTo(mScroller.getCurrX(), mScroller.getCurrY())
            postInvalidate()
            if (mScroller.isFinished()) {
                if (onSildingFinishListener != null && isFinish) {
                    onSildingFinishListener!!.onSildingFinish()
                }
            }
        }
    }

    interface OnSildingFinishListener {
        fun onSildingFinish()
    }


}
