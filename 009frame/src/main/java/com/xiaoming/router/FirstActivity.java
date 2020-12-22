package com.xiaoming.router;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xiaoming.framearouter.R;

//在支持路由的页面上添加注解
@Route(path = MyRouter.FirstActivity)
public class FirstActivity extends AppCompatActivity {
    private Button btArouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();
    }

    private void initView() {
        btArouter = findViewById(R.id.bt_arouter);
        btArouter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //发起路由操作,替换intent意图跳转
                //不带参数
                //ARouter.getInstance().build(MyRouter.SecondActivity).navigation();
                //带参数
                ARouter.getInstance().build(MyRouter.SecondActivity).withString("text","携带的参数").navigation();
            }
        });
    }
}
