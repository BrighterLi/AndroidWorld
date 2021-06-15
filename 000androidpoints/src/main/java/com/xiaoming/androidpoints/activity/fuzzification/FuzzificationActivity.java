package com.xiaoming.androidpoints.activity.fuzzification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.xiaoming.androidpoints.R;


//sleep 1.5s 然后模糊 再sleep2.0s 再去除模糊
public class FuzzificationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuzzification);

    }



}
