package com.xiaoming.a010kotlin.xiangxuedemo.modules.home

import android.os.Bundle
import android.view.*
import com.xiaoming.a010kotlin.R
import androidx.fragment.app.Fragment
import android.widget.Toast

//首页的Fragment
class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(activity, "首页", Toast.LENGTH_SHORT).show()
        val root: View? = /*inflater.inflate(R.layout.fragment_home, container, false)*/ null
        return root ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    init {
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.personal_menu, menu)
    }
}