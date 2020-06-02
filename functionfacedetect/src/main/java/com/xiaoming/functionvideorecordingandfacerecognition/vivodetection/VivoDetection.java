package com.xiaoming.functionvideorecordingandfacerecognition.vivodetection;

import android.os.CountDownTimer;
import android.util.Log;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class VivoDetection {
    private static final String TAG = "VivoDetection";
    public native static int detect(byte[] data, int height, int width, long handle);
    public native static long init(String folder);
    public native static void release(long handle);

    private long handle;
    public String[] strings = {"请摇头","请抬头","请低头","请闭眼","请张嘴"};
    private int expectOrder;
    private int controlState;
    int flag = 0;
    int state = -1;
    int stage = 0;
    int time = 1;
    int failCount = 0;
    int poseCode;

    static {
        System.loadLibrary("native-lib");
        Log.e(TAG,"bright#load lib");
    }

    VivoDetection(String folder) {
        controlState = 0;
        expectOrder = -1;
        handle = init(folder);
        waiting = new Timer(true);
        Timeout = new Timer(true);
        interval = new Timer(true);
    }

    CountDownTimer taskWaiting = new CountDownTimer(20000, 2000) {
        @Override
        public void onTick(long millisUntilFinished) {
            DetectionActivity.mTvActionTips.setText("请等待" + String.valueOf(time*2) + "s");
            time -= 1;
            if(time == 0) {
                poseRequest();
                state = 1;
            }
            if(time < 0) {
                if (flag == 1) {
                    if (state == 1) {
                        successInfo();
                        taskWaiting.cancel();
                    } else {
                        stage += 1;
                        poseRequest();
                    }
                } else {
                    failedInfo();
                    taskWaiting.cancel();
                }
            }
        }

        @Override
        public void onFinish() {
            time = 3;
        }
    };


    Timer waiting;
    CountDownTimer taskTnterval = new CountDownTimer(2000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            poseRequest();
            startAliveDetection();
        }
    };

    Timer interval;
    TimerTask taskTimeOut = new TimerTask() {
        @Override
        public void run() {

        }
    };

    Timer Timeout;

    void successInfo() {
        DetectionActivity.mTvActionTips.setText("检测成功");
    }

    void failedInfo() {
        DetectionActivity.mTvActionTips.setText("检测失败");
    }

    void firstFailedInfo() {
        DetectionActivity.mTvActionTips.setText("检测失败，请再次尝试");
    }

    void poseInfo(String info) {
        DetectionActivity.mTvActionTips.setText(info);
    }

    void poseRequest() {
        Random random = new Random();
        poseCode = random.nextInt(5) + 1;
        poseInfo(strings[poseCode-1]);
        state = 1;
        flag = 0;
    }

    void startAliveDetection() {
        taskTnterval.start();
    }

    protected void finalize() throws java.lang.Throwable {
        super.finalize();
        release(handle);
    }

    public int stateDetection(byte[] data, int height, int width) {
        return detect(data, height, width, handle);
    }

    public void startDetection() {
        taskWaiting.start();
    }

    public int aliveDetection(byte[] data, int height, int width) {
        int res1 = detect(data, height, width, handle);
        int pos_res = res1 / 10;
        if(controlState == 0) {
            //启动计时器
            startDetection();
            controlState = 1;
        }
        if(poseCode == pos_res && state == 1) {
            flag = 1;
            state = -1;
        }

        Log.i(TAG,"bright#contorlState:"+String.valueOf(controlState));
        Log.i(TAG,"bright#expectOrder:"+String.valueOf(poseCode));
        Log.i(TAG,"bright#res:"+String.valueOf(res1));

        return res1;
    }
}
