package com.xiaoming.widgetpopupwindow;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//https://blog.csdn.net/cswhale/article/details/86596943
public class DialogActivity extends Activity {
    public static DialogActivity instance = null;
    private TextView mTvConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        this.setFinishOnTouchOutside(false); //设置窗口周围触摸不消失
        //getWindow().setDimAmount(0); //设置窗口周围透明
        instance = this;
        initView();
    }

    private void initView() {
        mTvConfirm = findViewById(R.id.tv_confirm);
        mTvConfirm.setClickable(true);
        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
                finish();
            }
        });
    }
}
