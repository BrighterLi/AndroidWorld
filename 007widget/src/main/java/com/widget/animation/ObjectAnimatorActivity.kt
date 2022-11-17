package com.widget.animation

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.widget.databinding.ActivityObjectAnimatorBinding
import java.util.*

class ObjectAnimatorActivity : AppCompatActivity() {
    private lateinit var timer: Timer
    private lateinit var viewBinding: ActivityObjectAnimatorBinding
    private lateinit var scaleAnimator: ObjectAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityObjectAnimatorBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        startTimer()
        viewBinding.button.setOnClickListener {
            scaleAnimator.cancel()
            viewBinding.tvAnim.visibility = View.GONE
        }
    }

    private fun startTimer() {
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread{
                    startAnim(viewBinding.tvAnim)
                }
            }

        }, 3000)
    }

    private fun startAnim(view: View) {
        scaleAnimator = ObjectAnimator.ofFloat(view,"scaleX",1.0f, 0f)
        scaleAnimator.duration = 5000
        scaleAnimator.start()
    }
}