package com.widget.animation.pagescroll

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.Scroller
import androidx.customview.widget.ViewDragHelper

/**
 * User: BrightLi
 * Date: 2022-11-20-21:04
 * DESC:
 */
class BaseSwipeLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var dragView: View
    private lateinit var viewDragHelper: ViewDragHelper
    private var autoBackOriginalPoint = Point()
    private var curArrivePoint = Point()
    private var curEdgeFlag = ViewDragHelper.EDGE_LEFT
    private var swipeEdge = ViewDragHelper.EDGE_LEFT

}