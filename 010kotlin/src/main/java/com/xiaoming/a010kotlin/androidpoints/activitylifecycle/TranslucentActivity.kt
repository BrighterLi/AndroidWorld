package com.xiaoming.a010kotlin.androidpoints.activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xiaoming.a010kotlin.R

class TranslucentActivity : AppCompatActivity() {

    companion object {
        const val TAG = "TranslucentActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "lifecycle#onCreate")
        setContentView(R.layout.activity_translucent)
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
