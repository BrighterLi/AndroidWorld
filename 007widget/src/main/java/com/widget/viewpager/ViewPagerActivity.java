package com.widget.viewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.widget.R;

import java.util.ArrayList;
import java.util.List;

//ViewPager 全面总结:https://blog.csdn.net/weixin_39251617/article/details/79399592
public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        setVp();
    }

    private void setVp() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("第"+i+"个View");
        }

        ViewPager vp = (ViewPager) findViewById(R.id.view_pager);
        vp.setAdapter(new MyPagerAdapter(this,list));
    }
}
