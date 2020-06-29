package com.xiaoming.functionvideorecordingandfacerecognition.vivodetection;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoming.functionvideorecordingandfacerecognition.R;
import com.xiaoming.functionvideorecordingandfacerecognition.videorecord.RecordActivity;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

//引入opnCV,通过导入openCV并在CMakeList设置即可，无需依赖openCV的Module
public class DetectionActivity extends AppCompatActivity implements SurfaceHolder.Callback, Camera.PreviewCallback {
    private static final String TAG = "DetectionActivity";
    private Button mBtnStartDetect;
    private Button mBtnStopDetect;
    private Button mBtnStartRecord;
    private Button mBtnStartVideoPlay;
    private SurfaceView mSurfaceViewDetect;
    public TextView mTvDetectTime;
    public TextView mTvActionState;
    public TextView mTvVivoState;
    public static TextView mTvActionTips;
    private Camera.Parameters mCameraParas;
    private VivoDetection mVivoDetection;

    private Camera mCamera;
    private SurfaceHolder mSurfaceHolder; //作用就像一个关于Surface的监听器，提供访问和控制SurfaceView背后的Surface 相关的方法
    private int mCameraWidth = 480;
    private int mCameraHeight = 270;
    private MediaRecorder mMediaRecorder; //视频录像功能类
    private String mVideoPath; //视频保存路径
    private boolean mStartedRecordFlg = false; //是否正在录像
    private MediaPlayer mMediaPlayer; //视频播放功能类
    private boolean mIsPlay; //是否播放中

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "bright#onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection);

        initView();
        mVivoDetection = new VivoDetection("/sdcard/AliveDetection");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        mBtnStartDetect = findViewById(R.id.btn_start_detect);

        mBtnStopDetect = findViewById(R.id.btn_stop_detect);
        mSurfaceViewDetect = findViewById(R.id.surface_view_detect);
        mBtnStartRecord = findViewById(R.id.btn_start_record);
        mBtnStartVideoPlay =  findViewById(R.id.btn_start_video_play);
        mTvDetectTime = findViewById(R.id.tv_detect_time);
        mTvActionState = findViewById(R.id.tv_action_state);
        mTvVivoState = findViewById(R.id.tv_vivo_state);
        mTvActionTips = findViewById(R.id.tv_action_tips);

        //开启检测
        mBtnStartDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCamera.setPreviewCallback(DetectionActivity.this);
            }
        });

        //停止检测
        mBtnStopDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCamera.setPreviewCallback(null);
            }
        });

        //开始录屏
        mBtnStartRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRecord();
            }
        });

        //开始视频播放
        mBtnStartVideoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startVideoPlay();
            }
        });

        //SurfaceView添加回调
        //mSurfaceViewDetect.getHolder().addCallback(this);
        SurfaceHolder holder = mSurfaceViewDetect.getHolder();
        holder.addCallback(this);
        //setType必须设置，要不出错
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    //视频播放
    private void startVideoPlay() {
        mIsPlay = true;
        Log.d(TAG, "bright9#startVideoPlay");
        if(mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
        }
        mMediaPlayer.reset();
        Uri uri = Uri.parse(mVideoPath); //通过文件的路径String转化成Uri
        mMediaPlayer = MediaPlayer.create(DetectionActivity.this, uri);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setDisplay(mSurfaceHolder); //视频播放？
        try {
            mMediaPlayer.prepare();
        } catch (Exception e) {
            Log.d(TAG, "bright9#startVideoPlay#e：" + e);
            e.printStackTrace();
        }
        //视频播放
        mMediaPlayer.start();
    }

    //开始录屏或结束录屏
    private void startRecord() {
        //如果正在播放视频
        if(mIsPlay) {
            if(mMediaPlayer != null) {
                mIsPlay = false;
                //停止录制视频
                mMediaPlayer.stop();
                mMediaPlayer.reset();
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
        }

        if(mMediaRecorder == null) {
            mMediaRecorder = new MediaRecorder();
        }

        //如果不是正在录制视频，则开始录制
        if(!mStartedRecordFlg) {
            Log.d(TAG, "bright9#startRecord#start");
            //mCamera.setPreviewCallback(DetectionActivity.this);
            //打开摄像头
            mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT); //前置摄像头
            if(mCamera != null) {
                mCamera.setDisplayOrientation(90);//摄像头角度
                mCamera.unlock(); //必须加上，不然crash
                mMediaRecorder.setCamera(mCamera);
            }
                try {
                    //这两项需要放在setOutputFormat之前
                    mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
                    mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

                    //Set output file format
                    mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);

                    //这两项需要放在setOutputFormat之后
                    mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);

                    //mMediaRecorder.setVideoSize(640, 480); //?
                    //mMediaRecorder.setVideoFrameRate(30);
                    mMediaRecorder.setVideoEncodingBitRate(3 * 1024 * 1024);
                    //mMediaRecorder.setOrientationHint(90);

                    //设置记录会话的最大持续时间(毫秒)
                    //mMediaRecorder.setMaxDuration(60 * 1000);
                    mMediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());
                    //视频存储的路径
                    mVideoPath = getSDPath();
                    if(mVideoPath != null) {
                        //创建文件
                        File dir = new File(mVideoPath + "/recordvideo");
                        if(!dir.exists()) {
                            dir.mkdir();
                        }
                        mVideoPath = dir + "/" + getData() + ".mp4"; // mPath: /storage/emulated/0/recordvideo/录制时间.mp4
                        Log.d(TAG, "bright#initView#mPath: " + mVideoPath);
                    //如何上传到服务器？
                    //把视频放到本地sd card的mPath路径的文件
                    mMediaRecorder.setOutputFile(mVideoPath);
                    mMediaRecorder.prepare();
                    //开始录屏
                    mMediaRecorder.start();
                    mStartedRecordFlg = true;
                    mBtnStartRecord.setText("Stop");
                }
                } catch (Exception e) {
                    Log.d(TAG, "bright9#e：" + e);
                    e.printStackTrace();
                }
        } else {
            Log.d(TAG, "bright9#startRecord#stop");
            //如果正在录制视频,则停止录制
            if(mStartedRecordFlg) {
                try {
                    mMediaRecorder.stop();
                    mMediaRecorder.reset();
                    mMediaRecorder.release();
                    mMediaRecorder = null;
                    mBtnStartRecord.setText("Start");
                    if(mCamera != null) {
                        mCamera.release();
                        mCamera = null;
                    }
                } catch (Exception e) {
                    Log.d(TAG, "bright9#e：" + e);
                }
            }
            mStartedRecordFlg = false;
        }

    }

    /**
     * 获取SD path
     *
     * */
    public String getSDPath() {
        File sdDir = null;
        //获取sd卡是否存在
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); //sdcard
        if(sdCardExist) {
            //获取根目录
            sdDir = Environment.getExternalStorageDirectory(); //sdDir: /storage/emulated/0
            Log.d(TAG, "bright9#sdDir:" + sdDir.toString());
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
        Log.d(TAG, "bright#date:" + date);
        return date;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mSurfaceHolder = surfaceHolder;
        Log.d(TAG, "bright9#surfaceCreated");
        callCamera(mSurfaceViewDetect.getHolder());
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        if(surfaceHolder.getSurface() == null) {
            return;
        }

        mCamera.stopPreview();

        try {
            mCamera.setPreviewCallback(this);
            mCamera.setPreviewDisplay(surfaceHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "bright9#Error starting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    //Camera.PreviewCallback回调
    @Override
    public void onPreviewFrame(byte[] bytes, Camera camera) {
        long currentTime = System.currentTimeMillis();
        final Camera.Size previewSize = mCamera.getParameters().getPreviewSize();

        //获取活体检测的结果
        int state = mVivoDetection.aliveDetection(bytes, previewSize.height, previewSize.width);
        long diff = System.currentTimeMillis() - currentTime;
        mTvDetectTime.setText("检测时间:" + String.valueOf(diff));
        if(state % 10 == 0) {
            mTvVivoState.setText("活体状态：假脸");
        } else {
            mTvVivoState.setText("活体状态：真脸");
        }

        if(state / 10 == 6) {
            mTvActionState.setText("动作状态：不能检测到人脸");
        } else if (state / 10 == 0) {
            mTvActionState.setText("动作状态：正常");
        } else if (state / 10 == 1) {
            mTvActionState.setText("动作状态：摇头");
        } else if (state / 10 == 2) {
            mTvActionState.setText("动作状态：抬头");
        } else if (state / 10 == 3) {
            mTvActionState.setText("动作状态：低头");
        } else if (state / 10 == 4) {
            mTvActionState.setText("动作状态：闭眼");
        } else if (state / 10 == 5) {
            mTvActionState.setText("动作状态：张嘴");
        }
        Log.d(TAG, "bright#state:" + state);
    }

    //判断权限是否开启
    public void callCamera(SurfaceHolder surfaceHolder) {
        if(Build.VERSION.SDK_INT > 21) {
            String callCamera = Manifest.permission.CAMERA;
            String callRecord = Manifest.permission.RECORD_AUDIO;
            String[] permissions = new String[]{callCamera, callRecord};
            int selfSound = ActivityCompat.checkSelfPermission(this, callRecord);
            int selfPermission = ActivityCompat.checkSelfPermission(this, callCamera);

            if(selfPermission != PackageManager.PERMISSION_GRANTED || selfSound != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, permissions, 1);
            } else {
                initCamera(surfaceHolder);
            }
        } else {
            initCamera(surfaceHolder);
        }
    }

    //处理申请权限的结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initCamera(mSurfaceViewDetect.getHolder());
                } else {
                    Toast.makeText(this, "you refused the camera function", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //初始化Camera
    private void initCamera(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "bright9#initCamera");
        mCamera = Camera.open(1);
        //获取窗口的管理器
        WindowManager windowManager = ((WindowManager) getSystemService(Context.WINDOW_SERVICE));
        Display display = windowManager.getDefaultDisplay();
        mCameraParas = mCamera.getParameters();
        List<Camera.Size> sizeList = mCameraParas.getSupportedPreviewSizes();
        Camera.Size size = getOptimalPreviewSize(sizeList, display.getWidth(), display.getHeight());
        if(sizeList.size() > 1) {
            Iterator<Camera.Size> iterator = sizeList.iterator();
            while (iterator.hasNext()) {
                Camera.Size cur = iterator.next();
                if(cur.height <= 360) {
                    mCameraHeight = cur.height;
                    mCameraWidth = cur.width;
                    break;
                }
            }
        }

        mCameraParas.setPreviewFpsRange(30000, 30000);
        mCameraParas.setVideoStabilization(true);

        mCameraParas.setPreviewSize(mCameraWidth, mCameraHeight);
        try {
            //把摄像头获得画面显示在SurfaceView控件里面
            mCamera.setPreviewDisplay(mSurfaceViewDetect.getHolder());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> focusModes = mCameraParas.getSupportedFocusModes();
        if(focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            mCameraParas.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        } else if(focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
            mCameraParas.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        }

        mCamera.setParameters(mCameraParas);
        mCamera.setDisplayOrientation(90);
        mCamera.startPreview();
    }

    //?
    private static Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio = (double) w / h;
        if (sizes == null)
            return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        // Try to find an size match aspect ratio and size
        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
                continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        // Cannot find the one match the aspect ratio, ignore the requirement
        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }
}
