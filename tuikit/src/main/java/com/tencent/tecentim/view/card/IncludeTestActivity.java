package com.tencent.tecentim.view.card;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.tecentim.R;

//使用include标签
public class IncludeTestActivity extends Activity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_include_test);

        View cardView = findViewById(R.id.im_card);
        //cardView.setVisibility(View.GONE);
        textView = findViewById(R.id.im_card).findViewById(R.id.tv_card_price);
        textView.setText("500");
    }
}
