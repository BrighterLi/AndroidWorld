package com.xiaoming.functionfacedetect2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xiaoming.functionfacedetect2.R;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends Activity implements EasyPermissions.PermissionCallbacks,SurfaceHolder.Callback, Camera.PreviewCallback{
    private static int RC_READ_EXTERNAL_STORAGE = 2;
    private static Context mContext;
    private Button mBtStart;
    private Button mBtStop;
    private SurfaceView mCameraSurface;
    private Camera mCamera;
    private int width = 480;
    private int height = 270;
    private Camera.Parameters parameters;
    private MediaRecorder mMediaRecorder; //视频录像功能类
    private String mVideoPath; //视频保存路径
    private boolean mStartedRecordFlg = false; //是否正在录像
    private SurfaceHolder mSurfaceHolder; //作用就像一个关于Surface的监听器，提供访问和控制SurfaceView背后的Surface 相关的方法

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "bright98#onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inquirePermissions(this);
        initView();

        SurfaceHolder holder = mCameraSurface.getHolder();
        Log.d("MainActivity", "bright9#onCreate#holder:" + holder);
        holder.addCallback(this);
        //setType必须设置，要不出错
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public static void inquirePermissions(Activity activity) {
        mContext = activity;
        if (!EasyPermissions.hasPermissions(
                activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
        )) {
            EasyPermissions.requestPermissions(
                    activity,
                    "申请人脸检测相关权限",
                    RC_READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO //音频录制
            );
        } else {
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "禁止", Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        mCameraSurface = (SurfaceView) findViewById(R.id.surface_view_detect);
        mBtStart = findViewById(R.id.bt_start_record);
        mBtStop = findViewById(R.id.bt_stop_record);

        mBtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startVideoRecord();
            }
        });

        mBtStop = findViewById(R.id.bt_stop_record);
        mBtStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopVideoRecord();
            }
        });
    }


    @Override
    public void onPreviewFrame(byte[] bytes, Camera camera) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d("MainActivity", "bright9#surfaceCreated#surfaceHolder:" + surfaceHolder);
        mSurfaceHolder = surfaceHolder;
        initCamera(surfaceHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.d("MainActivity", "bright9#surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.d("MainActivity", "bright9#surfaceDestroyed");
    }

    private void initCamera(SurfaceHolder surfaceHolde) {
        Log.d("MainActivity", "bright98888#initCamera");
        mCamera = Camera.open(1);
        //WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);//获取窗口的管理器
        //Display display = wm.getDefaultDisplay();
        parameters = mCamera.getParameters();
        List<Camera.Size> sizeList = parameters.getSupportedPreviewSizes();
        //Camera.Size size = getOptimalPreviewSize(sizeList, display.getWidth(), display.getHeight());
        if (sizeList.size() > 1) {
            Iterator<Camera.Size> itor = sizeList.iterator();
            while (itor.hasNext()) {
                Camera.Size cur = itor.next();
                if (cur.height <= 360) {
                    height = cur.height;
                    width = cur.width;
                    break;
                }
            }
        }
        parameters.setPreviewFpsRange(30000, 30000);
        parameters.setVideoStabilization(true);

        parameters.setPreviewSize(width, height);
        try {
            mCamera.setPreviewDisplay(mCameraSurface.getHolder());//把摄像头获得画面显示在SurfaceView控件里面
        } catch (IOException e) {
            Log.d("MainActivity", "bright98#initCamera#e：" + e);
            e.printStackTrace();
        }
        List<String> focusModes = parameters.getSupportedFocusModes();
        if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        } else if (focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        }

        mCamera.setParameters(parameters);
        mCamera.setDisplayOrientation(90);
        //mCamera.setPreviewCallback(this);
        mCamera.startPreview();
    }

    private void startVideoRecord() {
        Log.d("MainActivity", "bright98888#startVideoRecord");
        //mCamera.setPreviewCallback(DetectionActivity.this); //可以录屏前开始检测人脸，也就是onPreviewFrame接口帧回调
        if (mMediaRecorder == null) {
            mMediaRecorder = new MediaRecorder();
        }

        //如果不是正在录制视频，则开始录制
        if (!mStartedRecordFlg) {
            Log.d("MainActivity", "bright9#startVideoRecord#start");
            mStartedRecordFlg = true;
            //mCamera.setPreviewCallback(DetectionActivity.this);
            //打开摄像头
            //mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT); //前置摄像头
            if (mCamera != null) {
                Log.d("MainActivity", "bright98#startRecord#mCamera != null");
                //mCamera.setDisplayOrientation(90);//摄像头角度
                mCamera.unlock(); //必须加上，不然crash
                mMediaRecorder.setCamera(mCamera);
            }
            try {
                //这两项需要放在setOutputFormat之前
                //mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
                mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT); //?
                mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

                //Set output file format
                mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);

                //这两项需要放在setOutputFormat之后
                mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); //音频编码
                mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP); //图像编码

                //mMediaRecorder.setVideoSize(640, 480); //?
                //mMediaRecorder.setVideoFrameRate(); //帧率
                //设置码率 越高清数越大

                mMediaRecorder.setVideoEncodingBitRate(3 * 1024 * 1024);
                mMediaRecorder.setOrientationHint(270);

                //设置记录会话的最大持续时间(毫秒)
                mMediaRecorder.setMaxDuration(60 * 1000);
                mMediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());
                //视频存储的路径
                mVideoPath = getSDPath();
                if (mVideoPath != null) {
                    //创建文件
                    File dir = new File(mVideoPath + "/recordvideo");
                    if (!dir.exists()) {
                        dir.mkdir();
                    }
                    mVideoPath = dir + "/" + getData() + ".mp4"; // mPath: /storage/emulated/0/recordvideo/录制时间.mp4
                    Log.d("FaceDetectView", "bright98#initView#mPath: " + mVideoPath);
                    //如何上传到服务器？
                    //把视频放到本地sd card的mPath路径的文件
                    mMediaRecorder.setOutputFile(mVideoPath);
                    try {
                        mMediaRecorder.prepare();
                        Log.d("MainActivity", "bright98#mMediaRecorder.prepare()");
                    } catch (IOException e) {
                        Log.d("MainActivity", "bright98#startVideoRecord#mMediaRecorder.prepare()#e：" + e.getMessage());
                        e.printStackTrace();
                    }
                    //开始录屏
                    Log.d("MainActivity", "bright98#mMediaRecorder.start()1");
                    mMediaRecorder.start();
                    Log.d("MainActivity", "bright98#mMediaRecorder.start()2");
                }
            } catch (Exception e) {
                Log.d("MainActivity", "bright98#startVideoRecord111#e：" + e);
                e.printStackTrace();
            }
        }
    }

    private void stopVideoRecord() {
        Log.d("FaceDetectView", "bright97#startRecord#stop");
        //如果正在录制视频,则停止录制
        if (mStartedRecordFlg) {
            mStartedRecordFlg = false;
            try {
                mMediaRecorder.stop();
                mMediaRecorder.reset();
                mMediaRecorder.release();
                mMediaRecorder = null;
                if (mCamera != null) {
                    mCamera.release();
                    mCamera = null;
                }
            } catch (Exception e) {
                Log.d("FaceDetectView", "bright98#e：" + e);
            }
        }
    }

    /**
     * 获取SD path
     */
    public String getSDPath() {
        File sdDir = null;
        //获取sd卡是否存在
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); //sdcard
        if (sdCardExist) {
            //获取根目录
            sdDir = Environment.getExternalStorageDirectory(); //sdDir: /storage/emulated/0
            Log.d("MainActivity", "bright97#sdDir:" + sdDir.toString());
            return sdDir.toString();
        }
        return null;
    }

    /**
     * 获取系统时间
     */
    public static String getData() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        int minute = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR);
        int second = calendar.get(Calendar.SECOND);

        String date = "" + year + (month + 1) + day + hour + minute + second;
        Log.d("MainActivity", "bright97#date:" + date);
        return date;
    }




}
