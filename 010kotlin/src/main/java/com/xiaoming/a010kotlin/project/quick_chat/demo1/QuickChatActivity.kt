package com.xiaoming.a010kotlin.project.quick_chat.demo1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.*
import androidx.recyclerview.widget.RecyclerView
import com.xiaoming.a010kotlin.R
import com.xiaoming.a010kotlin.project.quick_chat.demo1.adapter.EmojiAdapter
import com.xiaoming.a010kotlin.project.quick_chat.demo1.utils.FileUtil
import com.xiaoming.a010kotlin.project.quick_chat.demo1.utils.JsonParseUtil

class QuickChatActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_chat)

        val rvEmoji = findViewById<RecyclerView>(R.id.rv_emoji)
        rvEmoji.layoutManager = GridLayoutManager(this, 3)
        rvEmoji.adapter = EmojiAdapter(JsonParseUtil.parseEmojiList(FileUtil.readAssetsFile(this, "EmojiList.json")))
    }
}
