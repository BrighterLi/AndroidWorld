package com.xiaoming.a004performance.tool.strictmode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;

import com.xiaoming.a004performance.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

//Android性能优化-StrictMode(严苛模式): https://blog.csdn.net/qq_21430549/article/details/51183103
public class StrictModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strict_mode);
        setStrictMode();
        writeToExternalStorage();
    }

    public void setStrictMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()
                    //.penaltyDialog()
                    .build());
        }
    }

    public void writeToExternalStorage() {
        File path = Environment.getExternalStorageDirectory();
        File destFile = new File(path, "strictmode.txt");
        try {
            OutputStream output = new FileOutputStream(destFile, true);
            output.write("测试strictmode".getBytes());
            output.flush();
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
