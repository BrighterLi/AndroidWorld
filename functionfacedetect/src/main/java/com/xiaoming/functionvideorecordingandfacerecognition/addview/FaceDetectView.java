package com.xiaoming.functionvideorecordingandfacerecognition.addview;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.xiaoming.functionvideorecordingandfacerecognition.R;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class FaceDetectView extends RelativeLayout implements SurfaceHolder.Callback, Camera.PreviewCallback {

    private FaceMaskView2 mFaceMaskView;
    private ImageView mIvScanLine;
    private ImageView mIvGoundGrass;
    private TextView mTvDetectResult;
    private SurfaceView mCameraSurface = null;
    private TextView mFaceDetectTipText;
    private ImageView mIvUpBackground;
    private ImageView mIvBelowBackground;
    private ImageView mIvLeftBackground;
    private ImageView mIvRightBackground;
    private Button play;
    private Button start;

    private int mDetectFaceX = 88;
    private int mDetectFaceY = 144;
    private int mDetectFaceBoxWeight = 200;
    private int mDetectFaceBoxHeight = 200;

    private Animation lineAnim;
    private Camera mCamera = null;
    //public AliveDetection aliveDetection;
    private Context mContext;
    private Camera.Parameters parameters;
    private int width = 480;
    private int height = 270; //?

    //private FaceDetectListener mFaceDetectListener;
    //private FaceDetectParams mFaceDetectParams;
    private boolean isReachDetectTime = false;
    Handler handler = new Handler();

    private MediaRecorder mMediaRecorder; //视频录像功能类
    private String mVideoPath; //视频保存路径
    private boolean mStartedRecordFlg = false; //是否正在录像
    private MediaPlayer mMediaPlayer; //视频播放功能类
    private SurfaceHolder mSurfaceHolder; //作用就像一个关于Surface的监听器，提供访问和控制SurfaceView背后的Surface 相关的方法


    public FaceDetectView(Context context) {
        this(context, null);
        mContext = context;
    }

    public FaceDetectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.face_detect, this);

        //aliveDetection = new AliveDetection("/sdcard/AliveDetection");
        initView();
    }

    /*public void setFaceDetectListener(FaceDetectListener listener) {
        this.mFaceDetectListener = listener;
    }*/

    /*public void setmFaceDetectParams(FaceDetectParams params) {
        this.mFaceDetectParams = params;
        if (mFaceDetectTipText != null) {
            mFaceDetectTipText.setText(mFaceDetectParams.faceDetectTipText);
        }
    }*/


    private void initView() {
        Log.d("FaceDetectView", "bright9#initView");
        mCameraSurface = (SurfaceView) findViewById(R.id.surface_view_detect);
        mCameraSurface.getHolder().addCallback(this);
        mIvScanLine = findViewById(R.id.iv_scan_line);
        mIvGoundGrass = findViewById(R.id.iv_ground_grass);
        mTvDetectResult = findViewById(R.id.tv_detect_result);
        mFaceMaskView = findViewById(R.id.face_mask_view);
        mFaceMaskView.setPaintColor(Color.BLUE);
        mFaceDetectTipText = findViewById(R.id.tv_face_detect_tip_text);
        mIvUpBackground = findViewById(R.id.iv_up_background);
        mIvBelowBackground = findViewById(R.id.iv_below_background);
        mIvLeftBackground = findViewById(R.id.iv_left_background);
        mIvRightBackground = findViewById(R.id.iv_right_background);
        mIvLeftBackground = findViewById(R.id.iv_left_background);

        SurfaceHolder holder = mCameraSurface.getHolder(); // 绑定SurfaceView，取得SurfaceHolder对象
        holder.addCallback(this); // SurfaceHolder加入回调接口
        //设置显示器类型，setType必须设置，要不出错
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        start = findViewById(R.id.start);
        play = findViewById(R.id.play);
        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stopVideoRecord();
            }
        });

        play.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startVideoPlay();
            }
        });
        startScanLineAnim();
    }

    private void startScanLineAnim() {
        Log.d("FaceDetectView", "bright9#startScanLineAnim");
        mIvGoundGrass.setVisibility(View.INVISIBLE);
        mTvDetectResult.setVisibility(View.INVISIBLE);
        lineAnim = new TranslateAnimation(0, 0, -100, 400);
        lineAnim.setDuration(1000);
        lineAnim.setRepeatCount(Animation.INFINITE);
        mIvScanLine.setAnimation(lineAnim);
        lineAnim.startNow();
    }

    /*private void startFaceDetect() {
        Log.d("FaceDetectView", "bright9#startFaceDetect");
        mCamera.setPreviewCallback(this);
        startScanLineAnim();
        BaseApp.getMainHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isReachDetectTime = true;
            }
        }, 5 * 60 * 1000);
    }*/

    public void stopFaceDetect() {
        mCamera.setPreviewCallback(null);
    }

    private void stopScanLineAnim() {
        mIvGoundGrass.setVisibility(View.VISIBLE);
        mTvDetectResult.setVisibility(View.VISIBLE);
        mFaceMaskView.setPaintColor(Color.RED);
        mFaceMaskView.invalidate();
        mIvScanLine.clearAnimation();
        mIvScanLine.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        Log.d("FaceDetectView", "bright99#onPreviewFrame");
        final Camera.Size previewSize = camera.getParameters().getPreviewSize();

        /*//检测未成功之前
        int state = aliveDetection.AliveDetection(data, previewSize.height, previewSize.width, mDetectFaceX, mDetectFaceY, mDetectFaceBoxWeight, mDetectFaceBoxHeight);

        //每隔faceDetectInterval秒进行检测一次
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                returnDetectFaceState(state);
            }
        }, mFaceDetectParams.faceDetectInterval * 1000);*/

    }

   /* private void returnDetectFaceState(int state) {
        //真脸
        if (state % 10 != 0) {
            Log.d("FaceDetectActivity", "bright9#onPreviewFrame#state:" + state + "   活体状态：真脸");
            //在框内
            if (state / 10 == 1) {
                Log.d("FaceDetectActivity", "bright9#onPreviewFrame#state:" + state + "   状态：在限定区域中");
                Log.d("FaceDetectActivity", "bright99#onPreviewFrame#state:" + state + "   状态：成功");
                if (mFaceDetectListener != null) {
                    mFaceDetectListener.onFaceDetectResult(1, "成功");
                }
            }
        } else {
            Log.d("FaceDetectActivity", "bright99#onPreviewFrame#state:" + state + "   状态：失败");
            if (mFaceDetectListener != null) {
                mFaceDetectListener.onFaceDetectResult(0, "失败");
            }

            if (isReachDetectTime) {
                BaseApp.getMainHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        stopScanLineAnim();
                    }
                });
            }
        }
    }*/

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("FaceDetectView", "bright9#surfaceCreated");
        mSurfaceHolder = holder;
        initCamera(mCameraSurface.getHolder());
        //startVideoRecord();

        /*BaseApp.getMainHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startFaceDetect();
                startVideoRecord();
            }
        }, 1000);
*/
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("FaceDetectView", "bright9#surfaceChanged");
        if (holder.getSurface() == null) {
            return;
        }
        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }
        try {
            mCamera.setPreviewCallback(this);
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (Exception e) {
            Log.d("TAG", "Error starting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("FaceDetectView", "bright9#surfaceDestroyed");
    }

    private void initCamera(SurfaceHolder surfaceHolde) {
        Log.d("FaceDetectView", "bright9#initCamera");
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
        mCamera.startPreview();
    }

    public void onPause() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
        }
    }

    public void hideDetectView() {
        mFaceMaskView.setVisibility(View.INVISIBLE);
        mIvGoundGrass.setVisibility(View.INVISIBLE);
        mTvDetectResult.setVisibility(View.INVISIBLE);
        mFaceDetectTipText.setVisibility(View.INVISIBLE);
        mIvUpBackground.setVisibility(View.INVISIBLE);
        mIvBelowBackground.setVisibility(View.INVISIBLE);
        mIvLeftBackground.setVisibility(View.INVISIBLE);
        mIvRightBackground.setVisibility(View.INVISIBLE);
        mIvScanLine.setVisibility(View.INVISIBLE);

        updateFaceDetectBox();
    }

    private void updateFaceDetectBox() {
        mDetectFaceX = 0;
        mDetectFaceY = 80;
        mDetectFaceBoxHeight = 540;
        mDetectFaceBoxWeight = 375;
    }

    //开始录屏或结束录屏
    private void startVideoRecord() {
        //mCamera.setPreviewCallback(DetectionActivity.this); //可以录屏前开始检测人脸，也就是onPreviewFrame接口帧回调
        if (mMediaRecorder == null) {
            mMediaRecorder = new MediaRecorder();
        }

        //如果不是正在录制视频，则开始录制
        if (!mStartedRecordFlg) {
            Log.d("FaceDetectView", "bright97#startVideoRecord#start");
            mStartedRecordFlg = true;
            Log.d("FaceDetectView", "bright97#startRecord#!mStartedRecordFlg");
            //mCamera.setPreviewCallback(DetectionActivity.this);
            //打开摄像头
            //mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT); //前置摄像头
            if (mCamera != null) {
                Log.d("FaceDetectView", "bright97#startRecord#mCamera != null");
                //mCamera.setDisplayOrientation(90);//摄像头角度
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
                mMediaRecorder.setOrientationHint(270);

                //设置记录会话的最大持续时间(毫秒)
                //mMediaRecorder.setMaxDuration(60 * 1000);
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
                    Log.d("FaceDetectView", "bright97#initView#mPath: " + mVideoPath);
                    //如何上传到服务器？
                    //把视频放到本地sd card的mPath路径的文件
                    mMediaRecorder.setOutputFile(mVideoPath);
                    mMediaRecorder.prepare();
                    //开始录屏
                    mMediaRecorder.start();
                    Log.d("FaceDetectView", "bright97mMediaRecorder.start()");
                    //mStartedRecordFlg = true;
                }
            } catch (Exception e) {
                Log.d("FaceDetectView", "bright97#e：" + e);
                e.printStackTrace();
            }
        }

    }

    private void stopVideoRecord() {
        Log.d("FaceDetectView", "bright97#startRecord#stop");
        //如果正在录制视频,则停止录制
        if (mStartedRecordFlg) {
            mStartedRecordFlg = false;
            mCamera.setPreviewCallback(null); //可以停止录屏前先停止检测人脸，也就是onPreviewFrame接口帧回调
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
                Log.d("FaceDetectView", "bright97#e：" + e);
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
            Log.d("FaceDetectView", "bright97#sdDir:" + sdDir.toString());
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
        Log.d("FaceDetectView", "bright97#date:" + date);
        return date;
    }

    //视频播放
    private void startVideoPlay() {
        if(mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
        }
        mMediaPlayer.reset();
        Uri uri = Uri.parse(mVideoPath); //通过文件的路径String转化成Uri
        mMediaPlayer = MediaPlayer.create(getContext(), uri);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setDisplay(mSurfaceHolder); //视频播放？
        try {
            mMediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //视频播放
        mMediaPlayer.start();
    }

}
