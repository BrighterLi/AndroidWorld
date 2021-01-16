package com.xiaoming.androidpoints.background;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.xiaoming.androidpoints.R;


public class SetBackgroundActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_background);

        //设置背景图片方法一 activity_set_background设置了背景，则下面的设置不再生效，除非xml里的设置成透明
        //getWindow().getDecorView().setBackgroundResource(R.drawable.activity_background);

        //设置背景图片方法二，activity_set_background设置了背景，下面的设置也不生效，除非xml里的设置成透明
        View decorView = getWindow().getDecorView();
        if (decorView instanceof FrameLayout) {
            LinearLayout ll = (LinearLayout) ((FrameLayout) decorView).getChildAt(0);
            FrameLayout content = (FrameLayout) ll.getChildAt(1);
            content.getChildAt(0).setBackgroundResource(R.drawable.activity_background);
        }
    }
}
