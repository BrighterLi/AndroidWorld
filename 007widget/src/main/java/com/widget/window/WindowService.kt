package com.widget.window

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.PixelFormat
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Button
import com.widget.R

/**
 * https://www.cnblogs.com/mythou/p/3244208.html
 * https://blog.csdn.net/zy_style/article/details/54973934
 */
class WindowService : Service() {

    companion object {
        const val OPERATION = "operation"
        const val OPERATION_SHOW = 100
        const val OPERATION_HIDE = 101

        private const val HANDLE_CHECK_ACTIVITY = 200
    }

    private var isAdded = false
    private var wm: WindowManager? = null
    private var params: WindowManager.LayoutParams? = null
    private var btnFloatView: Button? = null
    private var activityManager: ActivityManager? = null
    private lateinit var homeList: List<String>

    private val handler: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        @SuppressLint("HandlerLeak")
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                HANDLE_CHECK_ACTIVITY -> {
                    if (isHome()) {
                        if (!isAdded) {
                            wm?.addView(
                                btnFloatView,
                                params
                            )
                            isAdded = true
                        }
                    } else {
                        if (isAdded) {
                            wm?.removeView(btnFloatView)
                            isAdded = false
                        }
                    }
                    sendEmptyMessageDelayed(
                        HANDLE_CHECK_ACTIVITY,
                        1000
                    )
                }
            }
        }
    }

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        homeList = getHomes()
        createFloatView()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        val operation = intent?.getIntExtra(
            OPERATION,
            OPERATION_SHOW
        )
        when (operation) {
            OPERATION_SHOW -> {
                handler.removeMessages(HANDLE_CHECK_ACTIVITY)
                handler.sendEmptyMessage(HANDLE_CHECK_ACTIVITY)
            }
            OPERATION_HIDE -> handler.removeMessages(HANDLE_CHECK_ACTIVITY)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun createFloatView() {
        btnFloatView = Button(applicationContext)
        btnFloatView?.setText("悬浮窗")
        btnFloatView?.setBackgroundResource(R.drawable.eyyarth)
        wm = applicationContext.getSystemService(
            WINDOW_SERVICE
        ) as WindowManager
        params = WindowManager.LayoutParams()

        // 设置window type
        params?.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY

        /**
         * 如果设置为params.type = WindowManager.LayoutParams.TYPE_PHONE; 那么优先级会降低一些,
         * 即拉下通知栏不可见
         */
        params?.format =
            PixelFormat.RGBA_8888

        // 设置Window flag
        params?.flags =
            (WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)

        /**
         * 下面的flags属性的效果形同“锁定”。 悬浮窗不可触摸，不接受任何事件,同时不影响后面的事件响应。
         * wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL |
         * LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_NOT_TOUCHABLE;
         */

        // 设置悬浮窗的长得宽
        params?.width = 300
        params?.height = 300

        // 设置悬浮窗的Touch监听
        btnFloatView?.setOnTouchListener(object : View.OnTouchListener {
            var lastX = 0
            var lastY = 0
            var paramX = 0
            var paramY = 0
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        lastX = event.rawX.toInt()
                        lastY = event.rawY.toInt()
                        paramX = params?.x ?: 0
                        paramY = params?.y ?: 0
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val dx = event.rawX.toInt() - lastX
                        val dy = event.rawY.toInt() - lastY
                        params?.x = paramX + dx
                        params?.y = paramY + dy
                        // 更新悬浮窗位置
                        wm?.updateViewLayout(
                            btnFloatView,
                            params
                        )
                    }
                }
                return true
            }
        })
        wm?.addView(
            btnFloatView,
            params
        )
        isAdded = true
    }

    private fun getHomes(): List<String> {
        val names: MutableList<String> = ArrayList()
        val packageManager = this.packageManager
        //
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        val resolveInfo = packageManager.queryIntentActivities(
            intent, PackageManager.MATCH_DEFAULT_ONLY
        )
        for (ri in resolveInfo) {
            names.add(ri.activityInfo.packageName)
        }
        return names
    }

    fun isHome(): Boolean {
        if (activityManager == null) {
            activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        }
        return true
    }
}