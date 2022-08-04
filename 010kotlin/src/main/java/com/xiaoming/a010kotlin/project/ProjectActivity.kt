package com.xiaoming.a010kotlin.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.xiaoming.a010kotlin.R
import com.xiaoming.a010kotlin.project.quick_chat.demo1.QuickChatActivity

class ProjectActivity : AppCompatActivity() {
    private lateinit var btQuickChat: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)

        initView()
    }

    private fun initView() {
        btQuickChat = findViewById(R.id.bt_quick_chat)
        btQuickChat.setOnClickListener{
            startActivity(Intent(this, QuickChatActivity::class.java))
        }
    }
}
