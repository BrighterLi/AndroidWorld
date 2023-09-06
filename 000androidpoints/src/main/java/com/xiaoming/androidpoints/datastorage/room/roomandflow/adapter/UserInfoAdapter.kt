package com.xiaoming.androidpoints.datastorage.room.roomandflow.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.xiaoming.androidpoints.R
import com.xiaoming.androidpoints.databinding.ItemUserinfoBinding
import com.xiaoming.androidpoints.datastorage.room.roomandflow.RoomFragment
import com.xiaoming.androidpoints.datastorage.room.roomandflow.entity.UserInfo
/**
 * User: BrightLi
 * Date: 2023-09-04-00:08
 * DESC:
 */
class UserInfoAdapter() : RecyclerView.Adapter<UserInfoAdapter.MyViewHolder>() {
    companion object {
        private const val TAG = "UserInfoAdapter"
    }
    private var data = ArrayList<UserInfo>()

    class MyViewHolder(val binding: ItemUserinfoBinding) : RecyclerView.ViewHolder(binding.root)

    fun setData(data: List<UserInfo>) {
        Log.d(TAG, "setData data: $data")
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemUserinfoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_userinfo,
            parent, false
        )

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder position: $position  userInfo: ${data[position]}")
        holder.binding.userInfo = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }
}