package com.xiaoming.a005algorithmopencv2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

//引入OpenCV：使用OpenCV sdk，不依赖OpenCV的Module
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private Button mBt1;
    private Button mBt2;
    private ImageView mIv;
    private Bitmap mBitmap;

    public native int[] bitmap2Gray(int[] pixels, int w, int h);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBt1 = findViewById(R.id.bt1);
        mBt2 = findViewById(R.id.bt2);
        mIv = findViewById(R.id.iv);

        //将图片转成Bitmap
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.old);
        mIv.setImageBitmap(mBitmap);

        mBt1.setOnClickListener(this);
        mBt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                showImage();
                break;
            case R.id.bt2:
                gray();
                break;
        }
    }

    private void showImage() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.old);
        mIv.setImageBitmap(mBitmap);
    }

    private void gray() {
        int w = mBitmap.getWidth();
        int h = mBitmap.getHeight();
        int[] piexls = new int[w*h];
        mBitmap.getPixels(piexls, 0, w, 0, 0, w, h);
        int[] resultData = bitmap2Gray(piexls, w, h);
        Bitmap resultImage = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        resultImage.setPixels(resultData, 0 ,w, 0, 0, w, h);
        mIv.setImageBitmap(resultImage);
    }
}
