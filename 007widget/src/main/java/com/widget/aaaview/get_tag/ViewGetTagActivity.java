package com.widget.aaaview.get_tag;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;

import android.os.Bundle;

import com.widget.R;

public class ViewGetTagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_get_tag);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        MyListener listener = new MyListener();
        button1.setTag(1);
        button1.setOnClickListener(listener);
        button2.setTag(2);
        button2.setOnClickListener(listener);
        button3.setTag(3);
        button3.setOnClickListener(listener);
    }


    public class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int tag = (Integer) v.getTag();
            switch (tag) {
                case 1:
                    System.out.println("button1 click");
                    break;
                case 2:
                    System.out.println("button2 click");
                    break;
                case 3:
                    System.out.println("button3 click");
                    break;
            }

        }
    }
}