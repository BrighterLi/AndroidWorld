package com.a006designmode.behaviormode.observermode2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.a006designmode.R;

//设计模式（五）观察者模式:https://blog.csdn.net/itachi85/article/details/50773358

//观察者模式这种发布-订阅的形式我们可以拿微信公众号来举例，假设微信用户就是观察者，
// 微信公众号是被观察者，有多个的微信用户关注了程序猿这个公众号，
// 当这个公众号更新时就会通知这些订阅的微信用户。
public class Observer2Activity extends Activity {
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer2);

        mBtn = findViewById(R.id.btn_observer2);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTest();
            }
        });
    }

    private void doTest() {
        SubscriptionSubject mSubscriptionSubject=new SubscriptionSubject();
        //创建微信用户
        WeixinUser user1=new WeixinUser("xiaohua");
        WeixinUser user2=new WeixinUser("xiaohong");
        WeixinUser user3=new WeixinUser("xiaobai");
        //订阅公众号/注册监听
        mSubscriptionSubject.attach(user1);
        mSubscriptionSubject.attach(user2);
        mSubscriptionSubject.attach(user3);
        //公众号更新发出消息给订阅的微信用户/发送通知
        mSubscriptionSubject.notify("bright专栏更新了");
    }
}
