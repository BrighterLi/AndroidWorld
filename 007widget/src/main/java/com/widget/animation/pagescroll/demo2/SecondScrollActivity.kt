package com.widget.animation.pagescroll.demo2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.widget.databinding.ActivitySecondScrollBinding

class SecondScrollActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySecondScrollBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySecondScrollBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initView()
    }

    private fun initView() {
        viewBinding.slideLayout.setOnSildingFinishListener(object:
            SildingFinishLayout.OnSildingFinishListener {
            override fun onSildingFinish() {
                finish()
            }

        })
        viewBinding.slideLayout
    }
}