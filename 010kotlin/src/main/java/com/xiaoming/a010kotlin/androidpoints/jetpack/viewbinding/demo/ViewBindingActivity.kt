package com.xiaoming.a010kotlin.androidpoints.jetpack.viewbinding.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xiaoming.a010kotlin.R
import com.xiaoming.a010kotlin.databinding.ActivityViewBindingBinding

class ViewBindingActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "ViewBindingActivity"
    }
    private lateinit var viewBinding: ActivityViewBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityViewBindingBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.tvViewBinding.text = "viewBinding test"
        viewBinding.btViewBinding.setOnClickListener {
            Log.d(TAG, "click")
        }

    }
}
