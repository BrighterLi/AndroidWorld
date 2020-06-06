package com.xiaoming.widgetpopupwindow.multidialog;

import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaoming.widgetpopupwindow.R;

import java.lang.ref.WeakReference;

public class MultiDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_dialog);
    }
}
