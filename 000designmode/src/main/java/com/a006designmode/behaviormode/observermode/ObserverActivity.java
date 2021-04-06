package com.a006designmode.behaviormode.observermode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.a006designmode.R;

//Android观察者模式的简单实现demo：https://www.cnblogs.com/fuyouG/p/fuyou-A-entryName.html
//ObserverListener是观察者接口，SubjectListener是被观察者接口，ObserverManager是观察者的管理类。
public class ObserverActivity extends Activity implements ObserverListener {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer);

        textView = (TextView) findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ObserverManager.getInstance().notifyObserver("观察者请刷新信息");
                Intent intent = new Intent(ObserverActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        //注册监听
        ObserverManager.getInstance().add(this);
    }

    @Override
    public void observerUpData(String content) {
        Log.d("bright8#Observer", "observerUpData#content:" + content);
        textView.setText(content);
    }
}
