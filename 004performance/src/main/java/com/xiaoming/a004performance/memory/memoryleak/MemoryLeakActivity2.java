package com.xiaoming.a004performance.memory.memoryleak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.xiaoming.a004performance.R;

import java.lang.ref.WeakReference;

//非静态内部类导致内存泄漏, 非静态Handler
//Android 非静态内部类导致的内存泄露（非static内部类）：https://blog.csdn.net/lsyz0021/article/details/51473819
//手把手教你在Android Studio 3.0上分析内存泄漏： https://www.jianshu.com/p/bdfd2a6b2681
public class MemoryLeakActivity2 extends AppCompatActivity {
    private final MyHandler myHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak2);

        //反复打开页面5次，手动GC，看下当前的堆栈情况。gc后内存没减少则内存泄漏
        //memoryLeakDemo();
        memoryNoLeakDemo();

    }

   /* private void memoryLeakDemo() {
        mLeaHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 10*60*1000);

        //finish();
    }*/

    //非静态内部类,会导致内存泄漏，mleaHandler持有MemoryLeakActivity2实例
   /* private final Handler mLeaHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

        }
    };*/




    //静态内部类，不会导致内存泄漏
    private void memoryNoLeakDemo() {
        myHandler.postDelayed(sRunnable,10*60*1000);
        //Runnable必须也是静态的，不然也会导致内存泄漏
        /*myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 10*60*1000);*/
    }

    private static final Runnable sRunnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    private static class MyHandler extends Handler {
        //private final WeakReference<MemoryLeakActivity2> mActivity;

        private MyHandler(MemoryLeakActivity2 activity) {
            //mActivity = new WeakReference<MemoryLeakActivity2>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            //MemoryLeakActivity2 activity = mActivity.get();
        }
    }
}
