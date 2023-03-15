package com.widget.animation.pagescroll.demo2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.widget.databinding.ActivityViewPager2Binding

class PageScrollActivity2 : AppCompatActivity() {
    private lateinit var viewBinding: ActivityViewPager2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityViewPager2Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initView()
    }

    private fun initView() {
        viewBinding.btJump.setOnClickListener {
            val intent = Intent(this, SecondScrollActivity::class.java)
            startActivity(intent)
        }
    }
}