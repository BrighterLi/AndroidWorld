package com.xiaoming.a010kotlin;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getKotlinType();


    }

    //调用kotlin
    public String getKotlinType(){
        Log.i("MainActivity", "bright8#getKotlinType:type:" + new KotlinDemo().getType());
        return  new KotlinDemo().getType();
    }
}
