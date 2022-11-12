package com.widget.window

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.widget.databinding.ActivityWindowBinding

class WindowActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityWindowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityWindowBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initView()
    }

    private fun initView() {
        viewBinding.btShow.setOnClickListener {
            val show = Intent(this, WindowService::class.java)
            show.putExtra(
                WindowService.OPERATION,
                WindowService.OPERATION_SHOW
            )
            startService(show)
        }
        viewBinding.btHide.setOnClickListener {
            val hide = Intent(this, WindowService::class.java)
            hide.putExtra(
                WindowService.OPERATION,
                WindowService.OPERATION_HIDE
            )
            startService(hide)
        }
    }
}