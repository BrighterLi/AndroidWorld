package com.widget.animation.pagescroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.widget.databinding.ActivityPageScrollBinding

/**
 * Android从上往下滑动或从下往上滑动结束Activity https://blog.csdn.net/qq_18612815/article/details/52353078
 * 实现Activity滑动退出: https://www.jianshu.com/p/7b0dfd2bf011
 */
class PageScrollActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityPageScrollBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPageScrollBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        //setTheme(android.R.style.Theme_Translucent)
    }
}