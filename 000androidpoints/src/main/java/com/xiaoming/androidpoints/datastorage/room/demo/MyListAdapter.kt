package com.xiaoming.androidpoints.datastorage.room.demo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xiaoming.androidpoints.R
import com.xiaoming.androidpoints.datastorage.room.demo.db.Student
import kotlinx.android.synthetic.main.adapter_room_item.view.*

class MyListAdapter(private var context: Context)
    :ListAdapter<Student, MyListAdapter.MyHolder>(MyItemCallback) {

    object MyItemCallback:DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return (oldItem.age == newItem.age) && (oldItem.name.equals(newItem.name))
        }

    }

    class MyHolder(itemView: View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflate = LayoutInflater
            .from(context)
            .inflate(R.layout.adapter_room_item, parent, false)
        return MyHolder(inflate)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.itemView.apply {
            tv_id.text = currentList[position].id.toString()
            tv_name.text = currentList[position].name
            tv_age.text = currentList[position].age.toString()
        }
    }

}
