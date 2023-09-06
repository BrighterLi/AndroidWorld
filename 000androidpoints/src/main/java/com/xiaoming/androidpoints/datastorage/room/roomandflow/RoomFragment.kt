package com.xiaoming.androidpoints.datastorage.room.roomandflow

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.xiaoming.androidpoints.R
import com.xiaoming.androidpoints.databinding.FragmentRoomBinding
import com.xiaoming.androidpoints.datastorage.room.roomandflow.adapter.UserInfoAdapter
import com.xiaoming.androidpoints.datastorage.room.roomandflow.db.MyDatabase
import com.xiaoming.androidpoints.datastorage.room.roomandflow.viewmodel.UserInfoViewModel
import kotlinx.coroutines.flow.collect

class RoomFragment : Fragment() {
    companion object {
        private const val TAG = "RoomFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //初始化数据库
        MyDatabase.init(requireContext())

        val inflate = DataBindingUtil.inflate<FragmentRoomBinding>(
            layoutInflater,
            R.layout.fragment_room,
            container,
            false
        )
        //实例化ViewModel
        val userInfoViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        ).get(UserInfoViewModel::class.java)
        inflate.userInfoViewModel = userInfoViewModel
        inflate.recyclerview.adapter = UserInfoAdapter()
        inflate.recyclerview.layoutManager = LinearLayoutManager(context)
        inflate.lifecycleOwner = viewLifecycleOwner
        //开启协程对数据库的表进行监听
        lifecycleScope.launchWhenCreated {
            //每当UserInfo表发生变化，Flow都会把UserInfo列表发射出去，那么我们
            //在collect中就可以获取到
            userInfoViewModel.getUserInfo().collect {
                Log.d(TAG, "launchWhenCreated getUserInfo list: $it")
                (inflate.recyclerview.adapter as UserInfoAdapter).setData(it)
            }
        }
        return inflate.root
    }
}