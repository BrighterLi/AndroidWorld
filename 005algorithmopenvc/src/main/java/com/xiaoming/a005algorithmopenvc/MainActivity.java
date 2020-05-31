package com.xiaoming.a005algorithmopenvc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

//使用直接依赖openCV的Module方式：implementation project(':openCVLibrary342')
//https://blog.csdn.net/qq_33198758/article/details/82984216
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mIvGray;
    private TextView mTvGRray;
    private Bitmap mSrcBitmap;
    private Bitmap mGrayBitmap;
    private static boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIvGray = findViewById(R.id.iv_gray);
        mTvGRray = findViewById(R.id.tv_gray);

        mTvGRray.setOnClickListener(this);
    }

    //OpenCV库加载并初始化成功后的回调函数
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            super.onManagerConnected(status);
            switch (status) {
                case BaseLoaderCallback.SUCCESS:
                    Toast toast = Toast.makeText(getApplicationContext(), "成功加载！", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    break;
                default:
                    super.onManagerConnected(status);
                    Toast toast2 = Toast.makeText(getApplicationContext(), "加载失败！", Toast.LENGTH_LONG);
                    toast2.setGravity(Gravity.CENTER, 0, 0);
                    toast2.show();
                    break;
            }
        }
    };

    //图片置灰
    public void procSrc2Gray() {
        Mat rgbMat = new Mat();
        Mat grayMat = new Mat();
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.old);
        mGrayBitmap = Bitmap.createBitmap(mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), Bitmap.Config.RGB_565);
        Utils.bitmapToMat(mSrcBitmap, rgbMat);
        Imgproc.cvtColor(rgbMat, grayMat, Imgproc.COLOR_RGB2GRAY);
        Utils.matToBitmap(grayMat, mGrayBitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_gray:
                procSrc2Gray();
                mIvGray.setImageBitmap(mGrayBitmap);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!OpenCVLoader.initDebug()) {
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }
}
