package com.xiaoming.functionvideorecordingandfacerecognition.videorecord;

import android.app.Activity;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.Calendar;
import com.xiaoming.functionvideorecordingandfacerecognition.R;
import com.xiaoming.functionvideorecordingandfacerecognition.videorecord.view.CustomSurfaceView;

//https://blog.csdn.net/woshizisezise/article/details/51878566
public class RecordActivity extends Activity implements SurfaceHolder.Callback{
    private static final String TAG = "RecordActivity";
    private CustomSurfaceView mSurfaceView;
    private Button mBtnStartStop;
    private Button mBtnPlay;
    private ImageView mImageView;
    private TextView mTextView;
    private MediaRecorder mMediaRecorder; //视频录像功能类
    private SurfaceHolder mSurfaceHolder; //作用就像一个关于Surface的监听器，提供访问和控制SurfaceView背后的Surface 相关的方法
    private Camera mCamera;
    private boolean mStartedFlg = false; //是否正在录像
    private boolean mIsPlay = false; //是否正在播放录像
    private String mPath;
    private int mText = 0;
    private MediaPlayer mMediaPlayer; //视频播放功能类

    private ImageView mIvScanLine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_record);

        initView();

    }

    private android.os.Handler handler = new android.os.Handler();
    //计时任务
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mText++;
            mTextView.setText(mText + "");
            handler.postDelayed(this, 1000);
        }
    };

    private void initView() {
        mSurfaceView = findViewById(R.id.surfaceView);
        mImageView = findViewById(R.id.imageView);
        mBtnStartStop = findViewById(R.id.btn_start_stop);
        mBtnPlay = findViewById(R.id.btn_play_video);
        mTextView = findViewById(R.id.textView);

        mIvScanLine = findViewById(R.id.iv_scan_line);


        // 视频开启和停止
        mBtnStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                //如果不是正在录制视频，则开始录制
                if(!mStartedFlg) {
                    scanLineAnim();
                    handler.postDelayed(runnable, 1000); //开始计时
                    mImageView.setVisibility(View.GONE);
                    if(mMediaRecorder == null) {
                        mMediaRecorder = new MediaRecorder();
                    }

                    //打开摄像头
                    mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT); //前置摄像头
                    if(mCamera != null) {
                        mCamera.setDisplayOrientation(90);//摄像头角度
                        mCamera.unlock();
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

                        //mMediaRecorder.setVideoSize(640, 480); //华为手机需要屏蔽掉,不然报start failed
                        mMediaRecorder.setVideoFrameRate(30);
                        //mMediaRecorder.setVideoEncodingBitRate(3 * 1024 * 1024); //华为手机需要屏蔽掉,不然报start failed
                        mMediaRecorder.setOrientationHint(90);

                        //设置记录会话的最大持续时间(毫秒)
                        //mMediaRecorder.setMaxDuration(60 * 1000); //华为手机需要屏蔽掉,不然拍出来的视频播放不了
                        mMediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());

                        //视频存储的路径
                        mPath = getSDPath();
                        if(mPath != null) {
                            //创建文件
                            File dir = new File(mPath + "/recordvideo");
                            if(!dir.exists()) {
                                dir.mkdir();
                            }
                            mPath = dir + "/" + getData() + ".mp4"; // mPath: /storage/emulated/0/recordvideo/录制时间.mp4
                            Log.d(TAG, "bright#initView#mPath: " + mPath);
                            //如何上传到服务器？
                            //把视频放到本地sd card的mPath路径的文件
                            mMediaRecorder.setOutputFile(mPath);
                            mMediaRecorder.prepare();
                            //开始录屏
                            mMediaRecorder.start();
                            mStartedFlg = true;
                            mBtnStartStop.setText("Stop");
                        }


                    } catch (Exception e) {
                        Log.d("RecordActivity", "bright9#视频开启#e：" + e);
                        e.printStackTrace();
                    }
                } else {
                    //如果正在录制视频,则停止录制
                    if(mStartedFlg) {
                        try {
                            handler.removeCallbacks(runnable); //停止计时
                            mMediaRecorder.stop();
                            mMediaRecorder.reset();
                            mMediaRecorder.release();
                            mMediaRecorder = null;
                            mBtnStartStop.setText("Start");
                            if(mCamera != null) {
                                mCamera.release();
                                mCamera = null;
                            }
                        } catch (Exception e) {
                            Log.d("RecordActivity", "bright9#停止#e：" + e);
                            e.printStackTrace();
                        }
                    }
                    mStartedFlg = false;
                }
            }
        });

        //视频播放
        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsPlay = true;
                mImageView.setVisibility(View.GONE);
                if(mMediaPlayer == null) {
                    mMediaPlayer = new MediaPlayer();
                }
                mMediaPlayer.reset();
                Uri uri = Uri.parse(mPath); //通过文件的路径String转化成Uri
                mMediaPlayer = MediaPlayer.create(RecordActivity.this, uri);
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mMediaPlayer.setDisplay(mSurfaceHolder); //视频播放？

                try {
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                } catch (Exception e) {
                    Log.d("RecordActivity", "bright9#视频播放#e：" + e);
                    e.printStackTrace();
                }
                //视频播放
            }
        });

        SurfaceHolder holder = mSurfaceView.getHolder();
        holder.addCallback(this);
        //setType必须设置，要不出错
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!mStartedFlg) {
            mImageView.setVisibility(View.VISIBLE);
        }
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
            Log.d(TAG, "bright#sdDir:" + sdDir.toString());
            return sdDir.toString();
        }
        return null;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mSurfaceHolder = holder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mSurfaceHolder = holder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mSurfaceView = null;
        mSurfaceHolder = null;
        handler.removeCallbacks(runnable);
        if(mMediaRecorder != null) {
            mMediaRecorder.release();
            mMediaRecorder = null;
            Log.d(TAG, "bright#surfaceDestroyed release mMediaRecorder");
        }
        if(mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
        if(mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void scanLineAnim() {
        Animation lineAnim =  new TranslateAnimation(10,10, -500, 50);
        lineAnim.setDuration(1500);
        lineAnim.setRepeatCount(Animation.INFINITE);
        mIvScanLine.setAnimation(lineAnim);
        lineAnim.startNow();
    }
}
