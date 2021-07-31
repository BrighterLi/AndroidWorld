package com.xiaoming.a010kotlin.xiangxuedemo.modules.personal

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.xiaoming.a010kotlin.R

//个人的Fragment
class PersonalFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_personal, container, false)
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