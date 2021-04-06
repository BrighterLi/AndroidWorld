package com.widget.image;

import android.app.Activity;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Bundle;

import com.widget.R;

//置灰方法1：Android 图片黑白显示 自定义饱和度:https://www.cnblogs.com/bokezhilu/p/8413119.html
public class ImageTestActivity extends Activity {
    private Button mBtSetImageGray;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_test);

        initView();
    }

    private void initView() {
        mBtSetImageGray = findViewById(R.id.bt_set_image_gray);
        mImageView = findViewById(R.id.image);

        mBtSetImageGray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageView.setImageResource(R.drawable.red_leaf);
                //置灰方法2
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                //matrix.setSaturation(1); //将控件还原为正常显示的状态
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                mImageView.setColorFilter(filter);
            }
        });
    }
}
