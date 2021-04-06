package com.xiaoming.router;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiaoming.framearouter.R;

//在支持路由的页面上添加注解
@Route(path = MyRouter.SecondActivity)
public class SecondActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //获取携带的参数
        String text = getIntent().getStringExtra("text");
        Toast.makeText(SecondActivity.this, text, Toast.LENGTH_LONG).show();
    }
}
