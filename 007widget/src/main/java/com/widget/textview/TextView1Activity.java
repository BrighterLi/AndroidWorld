package com.widget.textview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.widget.R;

public class TextView1Activity extends AppCompatActivity {
    private TextView textView3;
    private static String TAG = "TextView1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view1);

        textView3 = findViewById(R.id.textView3);
        String text = textView3.getText().toString();
        textView3.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int length = text.length();
                Character character = text.charAt(length-1);
                int lineEnd = textView3.getLayout().getLineEnd(2);
                Log.d(TAG, "onGlobalLayout text: " + text + "  lineEnd: "  + lineEnd + "  character: " + character
                + "  maxline: " + textView3.getMaxLines() + "  length: " + length);
                if (lineEnd < length && lineEnd > 0) {
                    textView3.setMaxLines(3);
                    textView3.setTextSize(14);
                }
            }
        });

    }
}
