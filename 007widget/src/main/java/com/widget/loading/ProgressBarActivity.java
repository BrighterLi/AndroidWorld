package com.widget.loading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;

import com.widget.R;

// https://www.cnblogs.com/salam/archive/2010/10/06/1844703.html
public class ProgressBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        //设置窗口特征：启用显示进度的进度条。？
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_progress_bar);
        setProgressBarVisibility(true);

        //ProgressBar有两个进度，一个是android:progress，另一个是android:secondaryProgress。
        final ProgressBar progressHorizontal = findViewById(R.id.progress_horizontal);
        setProgress(progressHorizontal.getProgress() * 100);
        setSecondaryProgress(progressHorizontal.getSecondaryProgress() * 100);

        Button increaseBtn = findViewById(R.id.increase);
        increaseBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                //增加第一进度
                progressHorizontal.incrementProgressBy(1);
                // Title progress is in range 0..10000
                setProgress(100 * progressHorizontal.getProgress());
            }
        });

        Button decreaseBtn = findViewById(R.id.decrease);
        decreaseBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                //减少第一进度
                progressHorizontal.incrementProgressBy(-1);
                // Title progress is in range 0..10000
                setProgress(100 * progressHorizontal.getProgress());
            }
        });

        Button increaseBtn2 = findViewById(R.id.increase2);
        increaseBtn2.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                //增加第二进度
                progressHorizontal.incrementSecondaryProgressBy(1);
                // Title progress is in range 0..10000
                setProgress(100 * progressHorizontal.getSecondaryProgress());
            }
        });

        Button decreaseBtn2 = findViewById(R.id.decrease2);
        decreaseBtn2.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                //减少第二进度
                progressHorizontal.incrementSecondaryProgressBy(-1);
                // Title progress is in range 0..10000
                setProgress(100 * progressHorizontal.getSecondaryProgress());
            }
        });
    }
}
