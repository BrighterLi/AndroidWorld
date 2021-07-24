package com.xiaoming.eventbus.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.framearouter.R;

import org.greenrobot.eventbus.EventBus;

public class EventBusSecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);


        Button publish = findViewById(R.id.publish);
        //事件的发布
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBusCarrier eventBusCarrier = new EventBusCarrier();
                eventBusCarrier.setEventType("1");
                eventBusCarrier.setObject("我是EventBusSecondActivity发布的事件");
                EventBus.getDefault().post(eventBusCarrier); //普通事件发布
//                EventBus.getDefault().postSticky(eventBusCarrier); //粘性事件
            }
        });
    }
}
