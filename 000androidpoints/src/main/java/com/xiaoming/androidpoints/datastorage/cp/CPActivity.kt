package com.xiaoming.androidpoints.datastorage.cp

import android.content.ContentResolver
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.xiaoming.androidpoints.R

class CPActivity : AppCompatActivity() {

    companion object {
        private var helperDB: OpenHelperDB? = null
        private var resolver: ContentResolver? = null
        private val NAME: String = "name"
        private val AUTHORITY: String = "com.example.iplayer.testprovider"
        private val ALL_URI: Uri = Uri.parse("content://$AUTHORITY/names")
        private val SINGLE_URI: Uri = Uri.parse("content://$AUTHORITY/name")
        private val _ID: String = "id"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cp)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        Thread() {
            resolver = contentResolver
            helperDB = OpenHelperDB(this, "test.db", 1);
//            helperDB?.readableDatabase?.execSQL("insert into user values(null, ?)", arrayOf("旧名"))
//            insert()
//            delete()
            update()
            query()
        }.start()
    }


    /**
     * 查询
     */
    private fun query():Unit{
        val cursor: Cursor? = resolver?.query(ALL_URI, null, null, null, null)
        if(cursor == null) println("cursor为空")
        if(cursor != null){
            while(cursor.moveToNext()){
                var index_name:Int = cursor.getColumnIndex(NAME)
                println(cursor.getString(index_name))
            }
        }
    }

    /**
     * 插入数据
     */
    private fun insert(){
        val values: ContentValues = ContentValues()
        values.put(NAME, "旧名")
        if(resolver == null) println("resolver为空")
        resolver?.insert(ALL_URI, values)
    }

    /**
     * 删除操作
     */
    private fun delete(){
        resolver?.delete(ALL_URI, null, null)
    }

    /**
     * 更新操作
     */
    private fun update(){
        val values:ContentValues = ContentValues()
        values.put(NAME, "新")
        var select:String = "$NAME = ?"
        resolver?.update(ALL_URI, values, select, arrayOf("旧名"))
    }
}