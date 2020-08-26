package com.xiaoming.androidknowledgepoints.ajavaknowledge;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.xiaoming.androidknowledgepoints.R;

public class JavaKnowledgeActivity extends Activity {
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_knowledge);

        str = "abcd";
        changeValue(str);
        Toast.makeText(this, "str：" + str, Toast.LENGTH_SHORT);
    }

    private  void changeValue(String str) {
        str = "efgh";
    }
}
