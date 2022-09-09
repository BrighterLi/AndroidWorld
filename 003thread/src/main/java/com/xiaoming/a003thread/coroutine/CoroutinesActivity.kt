package com.xiaoming.a003thread.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xiaoming.a003thread.R
import com.xiaoming.a003thread.databinding.ActivityCoroutinesBinding
import kotlinx.coroutines.*

class CoroutinesActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityCoroutinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCoroutinesBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.textView.text = "data is loading please wait"
        viewBinding.textView2.text = "data is loading please wait"
        viewBinding.textView3.text = "data is loading please wait"

        //第一种控制流：在子线程中执行耗时操作，在UI线程中更新View
        requestDataUpdateUI()
        //第二种控制流：两个耗时任务task1,task2有依赖，task2必须等待task1执行完成后才能执行
        requestTwoDependencyDataAndReturn()
        //第三种控制流：同时开两个线程执行耗时任务，两个都执行完成后，UI线程加载返回的数据
        requestDirectTwoDataAndUpdateUI()
    }

    // 通过launch在主线程中创建一个协程，Dispatchers.Main表示在主线程中启动，默认是在IO线程中启动
    // 等待子线程执行完成更新UI
    private fun requestDataUpdateUI() = GlobalScope.launch(Dispatchers.Main) {
        // 获取数据,async默认的启动模式是在IO线程中
        val deferred = async {
            delay(2000)
            "data loading finish"
        }
        // deferred.await()子线程给主线程发送中断,中断现在的事情，去做其他事情
        viewBinding.textView.text = deferred.await()
        }


    private fun requestTwoDependencyDataAndReturn() = GlobalScope.launch(Dispatchers.Main) {
        // 进入协程
        // withContext相当于async{}.await()
        // await底层原理即给Main线程发送中断不要干这件事情了，去干其他的，即在这里协程挂起
        viewBinding.textView2.text = withContext(Dispatchers.Default) {
            delay(2000)
            "task1 has finished, task2 is starting"
        }
        // 第一个任务执行返回后，继续执行第二个任务
        viewBinding.textView2.text = withContext(Dispatchers.Default) {
            delay(2000)
            "task2 has finished"
        }
    }

    /**
     * 并发请求两条数据
     */
    private fun requestDirectTwoDataAndUpdateUI() = GlobalScope.launch(Dispatchers.Main){
        // 进入协程
        val task1 = async(Dispatchers.Default) {
            delay(2000)
            "task1 has finished"
        }
        val task2 = async(Dispatchers.Default) {
            delay(2000)
            "task2 has finished"
        }
        viewBinding.textView3.text = "${task1.await()} + ${task2.await()}"
    }
}