package com.xiaoming.eventbus.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoming.framearouter.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

//android EventBus的简单使用: https://blog.csdn.net/marke1207/article/details/80382313
public class EventBusFirstActivity extends AppCompatActivity {
    private Button eb;
    private TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first2);

        EventBus.getDefault().register(this);  //事件的注册
        show = findViewById(R.id.show);

        eb = findViewById(R.id.eb);
        eb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventBusFirstActivity.this, EventBusSecondActivity.class);
                startActivity(intent);
            }
        });
    }

    // 普通事件的处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(EventBusCarrier carrier) {
        String content = (String) carrier.getObject();
        show.setText(content);
    }

 /*   // 粘性事件的处理
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void handleEvent(EventBusCarrier carrier) {
        String content = (String) carrier.getObject();
        show.setText(content);
    }*/

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this); //解除注册
        super.onDestroy();
    }
}
