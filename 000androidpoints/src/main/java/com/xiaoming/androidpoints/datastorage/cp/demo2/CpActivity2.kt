package com.xiaoming.androidpoints.datastorage.cp.demo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xiaoming.androidpoints.R
import android.content.ContentValues
import android.net.Uri
import android.util.Log
import kotlinx.android.synthetic.main.activity_cp2.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//https://www.jianshu.com/p/a90e9371f298
class CpActivity2 : AppCompatActivity() {
    companion object {
        private const val TAG = "CpActivity2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cp2)
        addDataBtn.setOnClickListener { addUser() }
        updateDataBtn.setOnClickListener { updateUser() }
        delDataBtn.setOnClickListener { delUser() }
        queryDataBtn.setOnClickListener { queryUser() }
    }

    /**
     * 新增数据
     */
    private fun addUser() {
        GlobalScope.launch(Dispatchers.IO) {
            val uri: Uri = Uri.parse("content://cn.cxy.contentproviderdemo.provider/user")
            val values = ContentValues().apply { put("user_name", "张三") }
            Log.d(TAG, "addUser values: $values")
            contentResolver.insert(uri, values)
        }
    }

    /**
     * 更新数据
     */
    private fun updateUser() {
        GlobalScope.launch(Dispatchers.IO) {
            val uri: Uri = Uri.parse("content://cn.cxy.contentproviderdemo.provider/user")
            val values = ContentValues().apply { put("user_name", "李四") }
            Log.d(TAG, "updateUser values: $values")
            contentResolver.update(uri, values, null, null)
        }
    }

    /**
     * 删除数据
     */
    private fun delUser() {
        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "delUser")
            val uri: Uri = Uri.parse("content://cn.cxy.contentproviderdemo.provider/user")
            contentResolver.delete(uri, null, null)
        }
    }

    /**
     * 查询数据
     */
    private fun queryUser() {
        GlobalScope.launch(Dispatchers.IO) {
            val uri = Uri.parse("content://cn.cxy.contentproviderdemo.provider/user")
            val cursor = contentResolver.query(uri, null, null, null, null)
            if (cursor != null) {
                var count = 0
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow("user_name"))
                    Log.d(TAG, "用户：${id}_$name")
                    count++
                }
                cursor.close()
                if (count == 0) {
                    Log.d(TAG, "queryUser 数据库是空的！")
                }
            }
        }
    }
}