package com.xiaoming.functionvideorecordingandfacerecognition.videorecordservice;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import androidx.annotation.Nullable;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.util.Date;

public class BackgroundVideoRecorder extends Service implements SurfaceHolder.Callback {
    private static String TAG = "BackgroundVideoRecorder";
    private WindowManager windowManager;
    private SurfaceView surfaceView;
    private Camera camera = null;
    private MediaRecorder mediaRecorder = null;

    @Override
    public void onCreate() {
        Log.d(TAG, "bright9#onCreate");

        // Start foreground service to avoid unexpected kill
        Notification notification = new Notification.Builder(this)
                .setContentTitle("Background Video Recorder")
                .setContentText("后台录屏")
                //.setSmallIcon(R.drawable.timg)
                .build();
        startForeground(1234, notification);

        // Create new SurfaceView, set its size to 1x1, move it to the top left corner and set this service as a callback
        windowManager = (WindowManager) BackgroundVideoRecorder.this.getSystemService(Context.WINDOW_SERVICE);
        surfaceView = new SurfaceView(this);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                1, 1,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT
        );

//全屏显示在最上图层上
//        final WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
//                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
//                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
//                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
//                PixelFormat.TRANSLUCENT
//        );


        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        windowManager.addView(surfaceView, layoutParams);
        surfaceView.getHolder().addCallback(this);

    }

    // Method called right after Surface created (initializing and starting MediaRecorder)
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "bright9#surfaceCreated");
        mediaRecorder = new MediaRecorder();
        camera = Camera.open(1);
        if (camera != null) {
            camera.unlock();
            mediaRecorder.setCamera(camera);
        }
        // 这两项需要放在setOutputFormat之后
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        // Set output file format
        mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));

        mediaRecorder.setVideoSize(640, 480);
        mediaRecorder.setVideoFrameRate(30);
        mediaRecorder.setVideoEncodingBitRate(1024 * 1024);
        mediaRecorder.setOrientationHint(90);
        mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());


        mediaRecorder.setOutputFile(
                Environment.getExternalStorageDirectory() + "/" +
                        DateFormat.format("yyyy-MM-dd_kk-mm-ss", new Date().getTime()) +
                        ".mp4"
        );

        try {

            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (Exception e) {
            Log.e("mediaRecorder", e.toString());
            camera.release();
        }


    }

    // Stop recording and remove SurfaceView
    @Override
    public void onDestroy() {
        Log.d(TAG, "bright9#onDestroy");
        mediaRecorder.stop();
        mediaRecorder.reset();
        mediaRecorder.release();

        camera.lock();
        camera.release();

        windowManager.removeView(surfaceView);

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        Log.d(TAG, "bright9#surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "bright9#surfaceDestroyed");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "bright9#onBind");
        return null;
    }

}
