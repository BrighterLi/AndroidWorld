package com.xiaoming.a010kotlin.xiangxuedemo.modules.collect

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.xiaoming.a010kotlin.R
import com.xiaoming.a010kotlin.xiangxuedemo.base.BaseFragment
import com.xiaoming.a010kotlin.xiangxuedemo.config.Flag
import com.xiaoming.a010kotlin.xiangxuedemo.database.Student
import com.xiaoming.a010kotlin.xiangxuedemo.modules.collect.adapter.CollectAdapter
import com.xiaoming.a010kotlin.xiangxuedemo.modules.collect.interfaces.CollectPresenter
import com.xiaoming.a010kotlin.xiangxuedemo.modules.collect.interfaces.CollectView
import kotlinx.android.synthetic.main.fragment_collect.*


//收藏的Fragment View 的 实现层
class CollectFragment : BaseFragment<CollectPresenter>(), CollectView {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Toast.makeText(activity, "收藏", Toast.LENGTH_SHORT).show()
        val root: View? = inflater.inflate(R.layout.fragment_collect, container, false)
        return root ?:   /* root null  就用后面的 */    super.onCreateView(inflater, container, savedInstanceState)
    }

    // 类似于  Java的构造代码块
    init {
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //　TODO Insert　动作
        addData.setOnClickListener {
            val names = arrayOf<String> (
                    "乔峰",
                    "段誉",
                    "虚竹",
                    "慕容复",
                    "张三",
                    "李四",
                    "王五",
                    "赵六",
                    "初七",
                    "杜子腾",
                    "王小二",
                    "李大奇"
            )
            val ages = arrayOf<Int> (
                    43,
                    24,
                    19,
                    83,
                    64,
                    21,
                    56,
                    32,
                    17,
                    32,
                    45,
                    14
            )
            for (index in names.indices) {
                val stu = Student(names[index], ages[index])
                p.requestInsert(stu)
            }
        }

        // TODO 全部删除动作
        clearData.setOnClickListener {
            p.requestDeleteAll()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.personal_menu, menu)
    }

    override fun getPresenter(): CollectPresenter = CollectPresenterImpl(this)

    override fun createOK() {
        p.requestQueryAll()
    }

    override fun recycle() {
        p.unAttachView()
    }


    // 响应的结果

    override fun showResultSuccess(result: List<Student>?) {
        Log.d(Flag.TAG, "showResultSuccess result: ${result.toString()} , LV:${recyclerView} ")

        // 数据 给  recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = CollectAdapter()
        if (null != result) {
            adapter.allStudents = result
        }
        recyclerView.adapter = adapter
    }

    // 增删改的回馈   你已经做完 增删改了
    override fun showResult(result: Boolean) {
        if (result) p.requestQueryAll()
    }
}
