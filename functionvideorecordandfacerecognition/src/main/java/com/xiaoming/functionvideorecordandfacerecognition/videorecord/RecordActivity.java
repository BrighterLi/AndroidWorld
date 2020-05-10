package com.xiaoming.functionvideorecordandfacerecognition.videorecord;

import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoming.functionvideorecordandfacerecognition.R;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

//https://blog.csdn.net/woshizisezise/article/details/51878566
public class RecordActivity extends AppCompatActivity implements SurfaceHolder.Callback{
    private static final String TAG = "RecordActivity";
    private SurfaceView mSurfaceView;
    private Button mBtnStartStop;
    private Button mBtnPlay;
    private ImageView mImageView;
    private TextView mTextView;
    private MediaRecorder mMediaRecorder;
    private SurfaceHolder mSurfaceHolder;
    private Camera mCamera;
    private boolean mStartedFlg = false; //是否正在录像
    private boolean mIsPlay = false; //是否正在播放录像
    private String mPath;
    private int mText = 0;
    private MediaPlayer mMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_record);

        initView();

    }

    private android.os.Handler handler = new android.os.Handler();
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

        // 视频开启和停止
        mBtnStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsPlay) {
                    if(mMediaPlayer != null) {
                        mIsPlay = false;
                        mMediaPlayer.stop();
                        mMediaPlayer.reset();
                        mMediaPlayer.release();
                        mMediaPlayer = null;
                    }
                }
                if(!mStartedFlg) {
                    handler.postDelayed(runnable, 1000);
                    mImageView.setVisibility(View.GONE);
                    if(mMediaRecorder == null) {
                        mMediaRecorder = new MediaRecorder();
                    }

                    mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
                    if(mCamera != null) {
                        mCamera.setDisplayOrientation(90);
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

                        mMediaRecorder.setVideoSize(640, 480);
                        mMediaRecorder.setVideoFrameRate(30);
                        mMediaRecorder.setVideoEncodingBitRate(3 * 1024 * 1024);
                        mMediaRecorder.setOrientationHint(90);

                        //设置记录会话的最大持续时间(毫秒)
                        mMediaRecorder.setMaxDuration(30 * 1000);
                        mMediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());

                        mPath = getSDPath();
                        if(mPath != null) {
                            File dir = new File(mPath + "/recordvideo");
                            if(!dir.exists()) {
                                dir.mkdir();
                            }
                            mPath = dir + "/" + getData() + ".mp4";
                            mMediaRecorder.setOutputFile(mPath);
                            mMediaRecorder.prepare();
                            mMediaRecorder.start();
                            mStartedFlg = true;
                            mBtnStartStop.setText("Stop");
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    //stop
                    if(mStartedFlg) {
                        try {
                            handler.removeCallbacks(runnable);
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
                            e.printStackTrace();
                        }
                    }
                    mStartedFlg = false;
                }
            }
        });

        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsPlay = true;
                mImageView.setVisibility(View.GONE);
                if(mMediaPlayer == null) {
                    mMediaPlayer = new MediaPlayer();
                }
                mMediaPlayer.reset();
                Uri uri = Uri.parse(mPath);
                mMediaPlayer = MediaPlayer.create(RecordActivity.this, uri);
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mMediaPlayer.setDisplay(mSurfaceHolder);

                try {
                    mMediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mMediaPlayer.start();
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
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if(sdCardExist) {
            //获取根目录
            sdDir = Environment.getExternalStorageDirectory();
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
}
