package com.xiaoming.net.cache.mapcache;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.xiaoming.net.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

//https://blog.csdn.net/qq_26296197/article/details/79290277
//Map内存缓存
public class MapCacheTestActivity extends AppCompatActivity {
    Logger logger = Logger.getLogger("cacheLog");
    Button cacheTestButton;
    Button threadTestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_cache);

        cacheTestButton = findViewById(R.id.btn_cache);
        threadTestButton = findViewById(R.id.btn_thread);

        cacheTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testCacheManager();
            }
        });

        threadTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testThreadSafe();
            }
        });
    }

    //测试缓存和缓存失效
    public void testCacheManager() {
        CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
        cacheManagerImpl.putCache("test","test", 10 * 1000L);
        cacheManagerImpl.putCache("myTest", "myTest", 15 * 1000L);
        CacheListener cacheListener = new CacheListener(cacheManagerImpl);
        //监听失效数据并移除
        cacheListener.startListen();
        logger.info("test:" + cacheManagerImpl.getCacheByKey("test").getDatas());
        logger.info("myTest:" + cacheManagerImpl.getCacheByKey("myTest").getDatas());

        try {
            //睡眠20s
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //清除后此时为空
        logger.info("test:" + cacheManagerImpl.getCacheByKey("test"));
        logger.info("myTest:" + cacheManagerImpl.getCacheByKey("myTest"));
    }

    //测试线程安全
    public void testThreadSafe() {
        final String key = "thread";
        final CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
        //线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        //new 出100个线程
        for (int i = 0; i < 100; i++) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    if(!cacheManagerImpl.isContains(key)) {
                        cacheManagerImpl.putCache(key, 1, 0);
                    } else {
                        //因为+1和赋值操作不是原子性的，所以把它用synchronize块包起来
                        synchronized (cacheManagerImpl) {
                            int value = ((Integer) cacheManagerImpl.getCacheDataByKey(key)) +1;
                            cacheManagerImpl.putCache(key, value,0);
                        }
                    }
                }
            });
        }
        exec.shutdown();
        try {
            exec.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(cacheManagerImpl.getCacheDataByKey(key).toString());
    }
}
