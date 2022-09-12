package com.xiaoming.a010kotlin.androidpoints.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xiaoming.a010kotlin.R
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

class FlowActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "FlowActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)

        runBlocking { //创建协程
            //flowTest()
            flowTest2()
            //flowTest3()
        }
    }

    private suspend fun flowTest() {
        flow<Int> {
            (0..4).forEach {
                emit(it)//生产者发送数据
            }
        }.collect {
            Log.d(TAG, "flowTest it: $it")
            println(it)//消费者处理数据
        }
    }


    suspend fun flowTest2() {
        flow {
            emit(1)
            emit(2)
        }.collect {
            Log.d(TAG, "flowTest2 it: $it")
            println(it)
        }
    }

    suspend fun flowTest3() {
        val time = measureTimeMillis{
            channelFlow {
                for (i in 1..5) {
                    delay(100)
                    send(i)
                }
            }.collect{
                delay(100)
                println(it)
                Log.d(TAG, "flowTest3 it: $it")
            }
        }
        print("cost $time")
    }

}