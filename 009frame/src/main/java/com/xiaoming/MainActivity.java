package com.xiaoming;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xiaoming.framearouter.R;
import com.xiaoming.router.FirstActivity;
import com.xiaoming.router.MyRouter;

//在支持路由的页面上添加注解
public class MainActivity extends Activity implements View.OnClickListener{
    private Button mBtRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtRouter = findViewById(R.id.bt_router);
        mBtRouter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_router:
                startActivity(new Intent(MainActivity.this, FirstActivity.class));
                break;
        }
    }
}
