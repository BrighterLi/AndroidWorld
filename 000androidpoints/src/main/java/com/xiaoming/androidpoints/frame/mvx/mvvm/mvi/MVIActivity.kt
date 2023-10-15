package com.xiaoming.androidpoints.frame.mvx.mvvm.mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xiaoming.androidpoints.R
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.collect

class MVIActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MVIActivity"
    }

    private val viewModel: MyViewModel = MyViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mviactivity)
        title = "MVI"

        runBlocking {
            viewModel.flow.collect {
                Log.d(TAG, "onCreate collect number: $it")
            }
        }
    }

}