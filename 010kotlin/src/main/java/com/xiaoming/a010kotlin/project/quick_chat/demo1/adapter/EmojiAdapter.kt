package com.xiaoming.a010kotlin.project.quick_chat.demo1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xiaoming.a010kotlin.R
import com.xiaoming.a010kotlin.project.quick_chat.demo1.bean.EmojiEntity

class EmojiAdapter(private val emojiEntityList: List<EmojiEntity>) : RecyclerView.Adapter<EmojiAdapter.MyViewHolder>() {
    override fun getItemCount(): Int {
        return emojiEntityList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_emoji, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName.text = emojiEntityList[position].name
        holder.tvEmoji.text = String(Character.toChars(emojiEntityList[position].unicode))
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_name)
        var tvEmoji: TextView = itemView.findViewById(R.id.tv_emoji)
    }
}
