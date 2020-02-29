package com.xiaoming.widgetattribute.image;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.FrameLayout;

import com.xiaoming.widgetattribute.R;

public class ImageAttributeActivity extends AppCompatActivity {
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_attribute);

        image = findViewById(R.id.image);
        setMargin();
    }

    //代码中设置View的各种属性。如布局的width、height;margin
    private void setMargin() {
        //设置width、height
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
       //父布局是FrameLayout
        //FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //父布局是RelativeLayout
        ///RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
         //设置Margin
        layoutParams.setMargins(100,0,0,0);
        image.setLayoutParams(layoutParams);
        //Toast.makeText(this, "layoutParams#width:" + layoutParams.width + "layoutParams#height:" + layoutParams.height, Toast.LENGTH_SHORT).show();
    }
}
