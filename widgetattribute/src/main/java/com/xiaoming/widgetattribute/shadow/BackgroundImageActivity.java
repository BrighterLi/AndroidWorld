package com.xiaoming.widgetattribute.shadow;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaoming.widgetattribute.R;

//.9图作为背景(怎么设置边框？图片自己决定？)
public class BackgroundImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backgrourd_image);
    }
}
