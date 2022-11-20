package com.widget.animation.moveview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.widget.R
import com.widget.databinding.ActivityMoveViewBinding

class MoveViewActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMoveViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMoveViewBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}