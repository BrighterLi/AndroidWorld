package com.xiaoming.a010kotlin.androidpoints.activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xiaoming.a010kotlin.R

//全透明
//A Activity被全透明B Activity覆盖后，只会执行onPause，不会执行onStop
class BActivity : AppCompatActivity() {
    companion object {
        const val TAG = "BActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "lifecycle#onCreate")
        setContentView(R.layout.activity_b)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "lifecycle#onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "lifecycle#onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "lifecycle#onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "lifecycle#onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "lifecycle#onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "lifecycle#onDestroy")
    }
}
