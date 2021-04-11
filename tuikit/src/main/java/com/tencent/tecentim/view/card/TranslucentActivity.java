package com.tencent.tecentim.view.card;


import android.app.Activity;
import android.os.Bundle;


import com.tencent.tecentim.R;

//聊天入口进去后显示的卡片实现方案：
//方案1：透明的Activity；要求：不能遮挡住之前界面ActionBar和底部输入ui,而且还可以和这两部分进行操作
//结果：由于透明Activity覆盖了之前的Activity，之前Activity已经onPause,无法进行交互；可以考虑在透明模拟之前Activity
//的界面ActionBar进行操作，中间部分才真正透明，但是底部的ui比较复杂，这样实现起来比较麻烦。
//方案2：把这个View放在ChatLayout的里面，这样要改封装好的ChatLayout的布局

//Android设置Activity背景为透明style的方法:https://www.jianshu.com/p/9b38dc864354
public class TranslucentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translucent);

    }
}