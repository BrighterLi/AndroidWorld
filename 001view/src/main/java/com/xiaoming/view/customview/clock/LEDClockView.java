package com.xiaoming.view.customview.clock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaoming.view.R;

import java.text.SimpleDateFormat;
import java.util.Date;

//https://blog.csdn.net/yangysng07/article/details/38704935
public class LEDClockView extends LinearLayout {
    private TextView mTimeView;
    private TextView mBgView;
    //private static final String FONT_DIGITAL_7 = "fonts" + File.separator + "digital-7.ttf";
    //private static final String DATE_FORMAT = "%02d:%02d:%02d";
    private static final int REFRESH_DELAY = 500;

    private final Handler mHandler = new Handler();
    private final Runnable mTimeRefresher = new Runnable() {
        @Override
        public void run() {
            /*Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
            final Date d = new Date();
            calendar.setTime(d);
            mTimeView.setText(String.format(DATE_FORMAT, calendar.get(Calendar.HOUR),
                    calendar.get(Calendar.MINUTE)));*/

            String timeNow = getNowDay("yyyy-MM-dd HH:mm:ss");
            mTimeView.setText(timeNow);

            mHandler.postDelayed(this, REFRESH_DELAY);
        }
    };

    public LEDClockView(Context context) {
        super(context);
        init(context);
    }

    public LEDClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @SuppressLint("NewApi")
    public LEDClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.layout_led_view,this);
        mTimeView = view.findViewById(R.id.tv_clock_time);
        mBgView = view.findViewById(R.id.tv_clock_bg);
        //AssetManager assetManager = context.getAssets();
        //final Typeface font = Typeface.createFromAsset(assetManager, FONT_DIGITAL_7);
        //设置字体
        //mTimeView.setTypeface(font);
        //mBgView.setTypeface(font);
    }

    public void start() {
        //刷新时间
        mHandler.post(mTimeRefresher);
    }

    public void stop() {
        mHandler.removeCallbacks(mTimeRefresher);
    }

    /**
     *获取当前时间
     * @param timeFormat 时间格式
     * @return 时间文本
     */
    public String getNowDay(String timeFormat){
        /**
         * SimpleDateFormat 是一个以与语言环境有关的方式来格式化和解析日期的具体类（java.text.SimpleDateFormat)。
         * 它允许进行格式化（日期 -> 文本）、解析（文本 -> 日期）和规范化。
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat);
        String dateString = simpleDateFormat.format(new Date()); //将给定的 Date 格式化为日期/时间字符串
        return dateString;
    }

}
