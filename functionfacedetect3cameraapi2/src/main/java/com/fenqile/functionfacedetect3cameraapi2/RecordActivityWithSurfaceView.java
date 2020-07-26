package com.fenqile.functionfacedetect3cameraapi2;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.PixelFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Camera2 API 采集视频并SurfaceView、TextureView 预览:https://www.jianshu.com/p/e01c11b96829
//Android Camera2 获取预览帧的回调数据（带demo）:https://blog.csdn.net/afei__/article/details/92102775

//Camera2录制视频:https://www.jianshu.com/p/645e048cb491
public class RecordActivityWithSurfaceView extends Activity {
    private static final String TAG = "RecordActivity";
    private Button mBtStartRecord;
    private Button mBtStopRecord;
    private SurfaceView mSurfaceView;

    private String mCameraId;
    private Size mPreviewSize;
    private HandlerThread mCameraThread;
    private Handler mCameraHandler;
    private CameraDevice mCameraDevice;
    private CaptureRequest.Builder mCaptureRequestBuilder;
    private CaptureRequest mCaptureRequest;
    private CameraCaptureSession mCameraCaptureSession;
    private SurfaceHolder mSurfaceHolder;

    private ImageReader mPreviewImageReader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"bright9#onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_with_surface_view);

        initView();
    }

    @Override
    protected void onResume() {
        Log.d(TAG,"bright9#onResume");
        super.onResume();
        intiCameraThread();
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceView.setZOrderMediaOverlay(true);
        mSurfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
        mSurfaceHolder.addCallback(mSurfaceHolderCallback);
    }

    private void initView() {
        mBtStartRecord = findViewById(R.id.bt_start_record);
        mBtStopRecord = findViewById(R.id.bt_stop_record);
        mSurfaceView = findViewById(R.id.surface_view);

        mBtStartRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        mBtStopRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void intiCameraThread(){
        Log.d(TAG,"bright9#intiCameraThread");
        mCameraThread = new HandlerThread("CameraSurfaceViewThread");
        mCameraThread.start();
        mCameraHandler = new Handler(mCameraThread.getLooper());
    }

    private SurfaceHolder.Callback mSurfaceHolderCallback = new SurfaceHolder.Callback() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Log.d(TAG,"bright9#surfaceCreated");
            setupCamera(holder.getSurfaceFrame().width(),holder.getSurfaceFrame().height());
            openCamera();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Log.d(TAG,"bright9#surfaceChanged");
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            Log.d(TAG,"bright9#surfaceDestroyed");
        }
    };


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupCamera(int width, int height){
        Log.d(TAG,"bright9#setupCamera");
        //获取摄像头的管理者CameraManager
        CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);

        try {
            //遍历所有摄像头
            for (String cameraId : cameraManager.getCameraIdList()){
                CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraId);
                Integer facing  = cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                //此处默认打开后置摄像头
                if (facing != null && facing == CameraCharacteristics.LENS_FACING_FRONT)
                    continue;
                //获取StreamConfigurationMap，他是管理摄像头支持的所有输出格式和尺寸
                StreamConfigurationMap map = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                assert map != null;
                mPreviewSize = getOptimalSize(map.getOutputSizes(SurfaceTexture.class),width,height);
                //mPreviewSize = map.getOutputSizes(SurfaceTexture.class)[0];
                initImageReader();
                setImageAvailableListener();
                mCameraId = cameraId;
            }
        } catch (CameraAccessException e) {
            Log.d(TAG,"bright9#setupCamera#setupCamera#CameraAccessException:"+ e);
            e.printStackTrace();
        }
    }

    //选择sizeMap中大于并且最接近width和height的size
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Size getOptimalSize(Size[] sizeMap, int width, int height) {
        List<Size> sizeList = new ArrayList<>();
        for (Size option : sizeMap) {
            if (width > height) {
                if (option.getWidth() > width && option.getHeight() > height) {
                    sizeList.add(option);
                }
            } else {
                if (option.getWidth() > height && option.getHeight() > width) {
                    sizeList.add(option);
                }
            }
        }
        if (sizeList.size() > 0) {
            return Collections.min(sizeList, new Comparator<Size>() {
                @Override
                public int compare(Size lhs, Size rhs) {
                    return Long.signum(lhs.getWidth() * lhs.getHeight() - rhs.getWidth() * rhs.getHeight());
                }
            });
        }
        return sizeMap[0];
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void openCamera(){
        Log.d(TAG,"bright9#openCamera");
        CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        try {
            Log.d(TAG,"mCameraId === " + mCameraId);
            cameraManager.openCamera(mCameraId,mCameraDeviceStateCallback,mCameraHandler);
        } catch (CameraAccessException e) {
            Log.d(TAG,"bright9#openCamera#CameraAccessException:" + e);
            e.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private CameraDevice.StateCallback mCameraDeviceStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            Log.d(TAG,"bright9#CameraDevice.StateCallback#onOpened");
            mCameraDevice = camera;
            startPreView();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            Log.d(TAG,"bright9#CameraDevice.StateCall#onDisconnected");
            if (mCameraDevice != null){
                mCameraDevice.close();
                camera.close();
                mCameraDevice = null;
            }
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            Log.d(TAG,"bright9#CameraDevice.StateCallback#onError");
            if (mCameraDevice != null){
                mCameraDevice.close();
                camera.close();
                mCameraDevice = null;
            }
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startPreView(){
        Log.d(TAG,"bright9#startPreView");
        try {
            Surface surface = mSurfaceHolder.getSurface();

            mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            mCaptureRequestBuilder.addTarget(surface); // 设置预览输出的 Surface
            mCaptureRequestBuilder.addTarget(mPreviewImageReader.getSurface()); // 设置预览回调的 Surface
            if (surface!=null){
                Log.d(TAG,"bright9#startPreView#SURFACE不为空");
                mCaptureRequestBuilder.addTarget(surface);
            }else {
                Log.d(TAG,"bright9#startPreView#SURFACE为空");
            }

            mCameraDevice.createCaptureSession(Arrays.asList(surface, mPreviewImageReader.getSurface()), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession session) {
                    Log.d(TAG,"bright9#startPreView#onConfigured");
                    mCaptureRequest = mCaptureRequestBuilder.build();
                    mCameraCaptureSession = session;
                    try {
                        mCameraCaptureSession.setRepeatingRequest(mCaptureRequest,null,mCameraHandler);
                    } catch (CameraAccessException e) {
                        Log.d(TAG,"bright9#startPreView#CameraAccessException:" + e);
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                    Log.d(TAG,"bright9#startPreView#onConfigureFailed");

                }
            },mCameraHandler);
        } catch (CameraAccessException e) {
            Log.d(TAG,"bright9#startPreView#mCameraDevice.createCaptureSession#CameraAccessException:" + e);
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onPause() {
        Log.d(TAG,"bright9#onPause");
        super.onPause();
        if (mCameraCaptureSession != null){
            mCameraCaptureSession.close();
            mCameraCaptureSession = null;
        }

        if (mCameraDevice != null){
            mCameraDevice.close();
            mCameraDevice = null;
        }

        if (mPreviewImageReader != null) {
            mPreviewImageReader.close();
            mPreviewImageReader = null;
        }
    }

   private void initImageReader() {
       mPreviewImageReader = ImageReader.newInstance(
               mPreviewSize.getWidth(), // 宽度
               mPreviewSize.getHeight(), // 高度
               ImageFormat.YUV_420_888, // 图像格式
               2); // 用户能同时得到的最大图
   }

    private void setImageAvailableListener() {
        Log.d(TAG,"bright9#setImageAvailableListener");
        mPreviewImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {
                // 回调得到并非我们之前所熟悉的 byte[] 数组，而是一个 ImageReader 对象，我们可以通过这个 ImageReader 对象来得到以往所熟悉的 byte[] 数组
                Log.d(TAG,"bright9#setImageAvailableListener#onImageAvailable");
            }
        }, null);
    }
}
