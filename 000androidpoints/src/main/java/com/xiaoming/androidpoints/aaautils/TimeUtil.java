package com.xiaoming.androidpoints.aaautils;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

//工具：时间戳转换：http://tool.chinaz.com/Tools/unixtime.aspx

//1、每隔5s做一次
//方法一：Timer; 方法二：Handler
//2、保存48小时
public class TimeUtil {
    private Timer myTimer;
    Handler handler = new Handler();

    //隔5s做一次
    private void doByTime() {
        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //在主线程隔5s做一次
                    }
                });*/
            }
        }, 5000);
    }

    //隔5s做一次
    public void doByTime2() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //隔5s做一次
            }
        }, 5000);
    }




    //判断两个时间点是不是当天同一天
    public static boolean isToday(long dateLong){
        Calendar pre = Calendar.getInstance();
        Date preDate = new Date(dateLong);
        pre.setTime(preDate);
        Calendar now = Calendar.getInstance();
        Date nowDate = new Date(System.currentTimeMillis());
        now.setTime(nowDate);

        if(now.get(Calendar.YEAR) == pre.get(Calendar.YEAR)) {
            int diffDay  = now.get(Calendar.DAY_OF_YEAR) - pre.get(Calendar.DAY_OF_YEAR);
            if(diffDay == 0) {
                return true;
            }
        }
        return false;
    }


}
