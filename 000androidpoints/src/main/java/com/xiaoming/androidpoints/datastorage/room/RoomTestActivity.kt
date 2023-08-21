package com.xiaoming.androidpoints.datastorage.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xiaoming.androidpoints.R
import com.xiaoming.androidpoints.datastorage.room.demo.MyDateBaseHelper
import com.xiaoming.androidpoints.datastorage.room.demo.MyListAdapter
import com.xiaoming.androidpoints.datastorage.room.demo.dao.StudentDao
import com.xiaoming.androidpoints.datastorage.room.demo.db.Student
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//https://www.codeleading.com/article/34856435475/#google_vignette
class RoomTestActivity : AppCompatActivity() {
    private val TAG = "RoomTestActivity"
    private lateinit var studentDao: StudentDao
    private lateinit var adapter: MyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        button.setOnClickListener(this::btnClick)
        btnDelete.setOnClickListener(this::btnClick)
        btnUpdate.setOnClickListener(this::btnClick)

        studentDao = MyDateBaseHelper.getInstance(applicationContext)!!.getStudentDao()

        GlobalScope.launch(Dispatchers.Main) {
            adapter = MyListAdapter(this@RoomTestActivity)
            recycler.layoutManager = LinearLayoutManager(this@RoomTestActivity)
            recycler.addItemDecoration(DividerItemDecoration(this@RoomTestActivity, RecyclerView.VERTICAL))
            recycler.adapter = adapter

            updateList()
            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    textView3.text = adapter.currentList[progress].id.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })
        }
    }

    private fun btnClick(view: View) {
        when (view.id){
            R.id.button ->{
                //插入一个学生对象
                if(edit.text.isEmpty()){
                    Toast.makeText(this,"no input",Toast.LENGTH_SHORT).show()
                    return
                }
                GlobalScope.launch(Dispatchers.Main){
                    withContext(Dispatchers.IO){
                        studentDao.insertStudent(Student(edit.text.toString(),20))
                    }
                    updateList()
                }
            }
            R.id.btnDelete ->{
                //删除seekBar选中的id
                GlobalScope.launch(Dispatchers.Main){
                    val student = getStudent(seekBar.progress)
                    Log.d(TAG, "btnDelete student: $student  progress: ${seekBar.progress}")
                    if(student == null){
                        Toast.makeText(this@RoomTestActivity,"student == null",Toast.LENGTH_SHORT).show()
                        return@launch
                    }
                    studentDao.deleteStudent(student)
                    updateList()
                }
            }
            R.id.btnUpdate ->{
                //更新，把seekBar选中的id的那个对象修改名字和年龄
                GlobalScope.launch(Dispatchers.Main){
                    val student = getStudent(seekBar.progress)
                    Log.d(TAG, "btnUpdate student: $student progress: ${seekBar.progress}")
                    if(student == null){
                        Toast.makeText(this@RoomTestActivity,"student == null",Toast.LENGTH_SHORT).show()
                        return@launch
                    }
                    student.age = 25
                    student.name = "updateTest"
                    studentDao.updateStudent(student)
                    updateList()
                }
            }
        }
    }

    private suspend fun getStudent(progress:Int):Student?{
        val id = adapter.currentList[progress].id
        Log.d(TAG, "getStudent id: $id")
        return withContext(Dispatchers.IO){
            studentDao.getStudent(id)
        }
    }

    private suspend fun updateList(){
        val list = withContext(Dispatchers.IO){
            Log.d(TAG, "updateList")
            studentDao.getAllStudents()
        }
        Log.d(TAG, "updateList list: $list")
        adapter.submitList(list)
        seekBar.max = list.size - 1
        seekBar.progress = 0
    }
}