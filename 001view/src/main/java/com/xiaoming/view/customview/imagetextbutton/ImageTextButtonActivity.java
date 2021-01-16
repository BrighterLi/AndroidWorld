package com.xiaoming.view.customview.imagetextbutton;

import android.app.Activity;
import android.os.Bundle;

import com.xiaoming.view.R;


public class ImageTextButtonActivity extends Activity {
    private ImageTextButton mBtCoustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_text_button);

        mBtCoustom = findViewById(R.id.custom_bt);
        //mBtCoustom.setBtnText("登录");
    }
}
