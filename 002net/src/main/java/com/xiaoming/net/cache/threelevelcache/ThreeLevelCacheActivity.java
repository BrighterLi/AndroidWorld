package com.xiaoming.net.cache.threelevelcache;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xiaoming.net.R;


//三级缓存
//Android图片的三级缓存demo：https://blog.csdn.net/Song_74110/article/details/80876435
public class ThreeLevelCacheActivity extends AppCompatActivity {
    private Button mBtJump;
    private ImageView mIv;
    private String url = "http://e.hiphotos.baidu.com/image/pic/item/2fdda3cc7cd98d10b510fdea233fb80e7aec9021.jpg";
    //https://img.alicdn.com/tps/TB15vyaLpXXXXXXXFXXXXXXXXXX-198-46.png

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_level_cache);

        mIv = findViewById(R.id.iv);
        mBtJump = findViewById(R.id.bt_jump);
        mBtJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThreeLevelCacheActivity.this, SecondActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取图片并显示
        ThreeLevelCache.instance(this).display(mIv,url);
    }
}
