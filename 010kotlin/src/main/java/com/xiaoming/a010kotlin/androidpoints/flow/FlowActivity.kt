package com.xiaoming.a010kotlin.androidpoints.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xiaoming.a010kotlin.R
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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
            //flowTest2()
            //flowTest3()
            flowTest4()
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

    //背压
    //当流的收集器不堪重负时，它可以简单地挂起发射器，并在准备好接受更多元素时稍后将其恢复
    suspend fun flowTest4() {
        var start = 0L
        val time = measureTimeMillis {
            (1..5)
                .asFlow()
                .onStart { start = System.currentTimeMillis() }
                .onEach {
                    delay(100)
                    println("Emit $it (${System.currentTimeMillis() - start}ms) ")
                    Log.d(TAG, "flowTest4 Emit $it (${System.currentTimeMillis() - start}ms))")
                }
                .buffer()
                .flowOn(Dispatchers.IO)
                .collect {
                    Log.d(TAG, "flowTest4 Collect $it starts (${System.currentTimeMillis() - start}ms)")
                    delay(500)
                    Log.d(TAG, "flowTest4 Collect $it ends (${System.currentTimeMillis() - start}ms") }
        }
        Log.d(TAG, "flowTest4 Cost $time ms")
    }

}