package com.xiaoming.a010kotlin.androidpoints.activitylifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xiaoming.a010kotlin.R
import android.widget.Button

class AActivity : AppCompatActivity() {
    companion object {
        const val TAG = "AActivity"
    }

    private var btSwitch: Button? = null

    private fun init() {
        btSwitch = findViewById(R.id.bt)
        btSwitch?.setOnClickListener{
            Log.d(TAG, "lifecycle#setOnClickListener")
            //startActivity(Intent(this, BActivity::class.java))
            startActivity(Intent(this, TranslucentActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)
        Log.d(TAG, "lifecycle#onCreate")
        init()
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
