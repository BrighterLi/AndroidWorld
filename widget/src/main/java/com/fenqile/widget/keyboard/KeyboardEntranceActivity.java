package com.fenqile.widget.keyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.fenqile.widget.R;

public class KeyboardEntranceActivity extends AppCompatActivity {
    private EditText mEtInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        initView();
    }

    private void initView() {
        mEtInput = findViewById(R.id.et_keyboard_input);
    }
}
