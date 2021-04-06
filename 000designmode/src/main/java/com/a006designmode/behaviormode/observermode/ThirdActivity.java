package com.a006designmode.behaviormode.observermode;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.a006designmode.R;

public class ThirdActivity extends Activity implements ObserverListener {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        textView = (TextView) findViewById(R.id.tv3);
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通知观察者刷新信息
                ObserverManager.getInstance().notifyObserver("我是刷新的信息");
            }
        });

        //注册
        ObserverManager.getInstance().add(this);
    }

    @Override
    public void observerUpData(String content) {
        Log.d("bright8#Third", "observerUpData#content:" + content);
        textView.setText(content);
    }
}
