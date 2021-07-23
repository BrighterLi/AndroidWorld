package com.widget.aaaview.measure.onMeasure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.widget.R;

//android中对View的onMeasure()方法的理解: https://blog.csdn.net/lovexieyuan520/article/details/50614670
public class OnMeasureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_measure);
    }
}
