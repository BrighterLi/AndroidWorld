package com.xiaoming.widgetreddot.customreddot;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.EditText;

import com.xiaoming.widgetreddot.R;

public class CustomRedDotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_red_dot);

        TextView textView = (TextView)findViewById(R.id.tv);
        View view = (View)findViewById(R.id.v);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);
        EditText editText = (EditText) findViewById(R.id.et);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl);
        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.rl2);

        //设置圆圈半径
        RedDot redDot1 = new RedDot(this,20);
        //设置圆圈内容
        redDot1.setNum(100);
        redDot1.setTargetView(relativeLayout2);

        RedDot redDot2 = new RedDot(this, 30);
        redDot2.setNum(20);
        redDot2.setTargetView(relativeLayout);

        RedDot redDot3 = new RedDot(this, 30);
        redDot3.setNum(30);
        redDot3.setTargetView(linearLayout);

        RedDot redDot4 = new RedDot(this, 50);
        redDot4.setNum(100);
        redDot4.setBgColor(Color.parseColor("#ff00ff"));
        redDot4.setTextColor(Color.parseColor("#ffff00"));
        redDot4.setTargetView(view);

        RedDot redDot5 = new RedDot(this, 20);
        redDot5.setNum(50);
        redDot5.setTargetView(editText);
    }
}
