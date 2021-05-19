package com.xiaoming.a000keeplearning.java.rxjava.observer_mode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.xiaoming.a000keeplearning.R;

/**
 * TODO 主要是标准观察者模式
 *
 * todo# 使用场景例子
 *      有一个微信公众号服务，不定时发布一些消息，关注公众号就可以收到推送消息，取消关注就收不到推送消息。
 */
public class ObserverModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer_modle);
        test();
    }

    public static void test() {
        // 编辑部 编辑好的文案内容
        String msg = "重大消息：以后Android程序员必须要学习Kotlin，必须要学习各种新技术与跨平台的内容啦";

        // 创建一个微信公众号服务（被观察者）
        Observable server = new WechatServerObservable();

        // 创建 用户 （观察者）  多个
        Observer shizhenxiangUser = new UserPerson("史帧湘");
        Observer duzitenUser = new UserPerson("杜子腾");
        Observer daiyuejinUser = new UserPerson("戴悦京");
        Observer liumangUser = new UserPerson("刘芒");

        // 订阅  注意：这里的订阅还是 被观察者.订阅(观察者)  关注
        server.addObserver(shizhenxiangUser);
        server.addObserver(duzitenUser);
        server.addObserver(daiyuejinUser);
        server.addObserver(liumangUser);

        // 微信平台 发生了 改变
        // server.pushMessage(msg);













        // 肚子疼 取消了关注
        System.out.println("============================================");
        server.removeObserver(duzitenUser);

        // 再次微信平台 发生了 改变
        server.pushMessage(msg);
    }
}
