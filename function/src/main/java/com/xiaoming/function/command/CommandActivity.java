package com.xiaoming.function.command;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.xiaoming.function.R;

public class CommandActivity extends Activity {
    private String mClipboardText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        mClipboardText = ClipBoardUtil.getCurrentClipboardText(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "剪切板内容：" + mClipboardText, Toast.LENGTH_SHORT).show();
    }
}
