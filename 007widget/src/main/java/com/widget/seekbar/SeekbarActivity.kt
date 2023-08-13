package com.widget.seekbar

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import com.widget.R
import com.widget.databinding.ActivitySeekbarBinding

class SeekbarActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "SeekbarActivity"
    }

    private lateinit var viewBinding: ActivitySeekbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySeekbarBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.seekBar.setOnSeekBarChangeListener(@SuppressLint("AppCompatCustomView")
        object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.d(TAG, "setOnSeekBarChangeListener onProgressChanged progress: $progress")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Log.d(TAG, "setOnSeekBarChangeListener onStartTrackingTouch")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Log.d(TAG, "setOnSeekBarChangeListener onStopTrackingTouch")
            }
        })
    }
}