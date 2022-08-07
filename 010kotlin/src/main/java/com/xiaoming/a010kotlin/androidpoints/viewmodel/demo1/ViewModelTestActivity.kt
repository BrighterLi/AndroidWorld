package com.xiaoming.a010kotlin.androidpoints.viewmodel.demo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.xiaoming.a010kotlin.R

class ViewModelTestActivity : AppCompatActivity() {
    companion object {
        const val TAG = "ViewModelTestActivity"
    }

    lateinit var viewModel: ViewModelTest
    lateinit var textView: TextView
    lateinit var button1: Button
    lateinit var button2: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_test)

        viewModel = ViewModelProvider(this).get(ViewModelTest::class.java)
        initView()
    }

    fun initView() {
        Log.d(TAG, "onCleared")
        button1 = findViewById(R.id.bt1_view_model)
        button2 = findViewById(R.id.bt2_view_model)
        textView = findViewById(R.id.tv_view_model)

        button1.setOnClickListener {
            viewModel.number++
            textView.text = viewModel.number.toString()
        }

        button2.setOnClickListener {
            viewModel.number += 2
            textView.text = viewModel.number.toString()
        }
    }
}
