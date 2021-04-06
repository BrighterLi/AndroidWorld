package com.widget.banner.Banner;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.widget.R;

import java.util.ArrayList;
import java.util.List;

//Android使用ViewPager实现轮播图（自动和手动）:https://www.jianshu.com/p/4e9daf1bf3dd
public class BannerActivity extends AppCompatActivity {
    private ImageSlideshow imageSlideshow;
    private List<String> imageUrlList;
    private List<String> titleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_banner);

        imageSlideshow = (ImageSlideshow) findViewById(R.id.is_gallery);
        imageUrlList = new ArrayList<>();
        titleList = new ArrayList<>();

        // 初始化数据
        initData();

        // 为ImageSlideshow设置数据
        imageSlideshow.setDotSpace(12);
        imageSlideshow.setDotSize(12);
        imageSlideshow.setDelay(3000); //设置轮播时间
        imageSlideshow.setOnItemClickListener(new ImageSlideshow.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(BannerActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        imageSlideshow.commit();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        int[] imageIds = new int[]{
                R.drawable.dian1,
                R.drawable.dian2,
                R.drawable.dian3,
                R.drawable.dian4,
                R.drawable.dian3

        };
        String[] imageUrls = {"http://pic3.zhimg.com/b5c5fc8e9141cb785ca3b0a1d037a9a2.jpg",
                "http://pic2.zhimg.com/551fac8833ec0f9e0a142aa2031b9b09.jpg",
                "http://pic2.zhimg.com/be6f444c9c8bc03baa8d79cecae40961.jpg",
                "http://pic1.zhimg.com/b6f59c017b43937bb85a81f9269b1ae8.jpg",
                "http://pic2.zhimg.com/a62f9985cae17fe535a99901db18eba9.jpg"};
        String[] titles = {" ",
                "  ",
                "  ",
                "  ",
                "  "};
        for (int i = 0; i < 5; i++) {
            imageSlideshow.addImageTitle(imageUrls[i], titles[i],imageIds[i]);
        }
    }

    @Override
    protected void onDestroy() {
        // 释放资源
        imageSlideshow.releaseResource();
        super.onDestroy();
    }
}
