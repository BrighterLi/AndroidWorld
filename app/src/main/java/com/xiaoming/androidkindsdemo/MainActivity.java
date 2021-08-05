package com.xiaoming.androidkindsdemo;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xiaoming.acrossslibrary.Add;
/*import com.xiaoming.androidkindsdemo.acrossend.flutter.CustomFlutterActivity;*/

public class MainActivity extends AppCompatActivity implements View.
        OnClickListener{
    private Button mBtnFlutter;
    private Button mBtnAcrossLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mBtnFlutter = findViewById(R.id.btn_flutter);
        mBtnAcrossLibrary = findViewById(R.id.btn_across_library);

        mBtnFlutter.setOnClickListener(this);
        mBtnAcrossLibrary.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
           /* case R.id.btn_flutter:
                Intent intent = new Intent(this, CustomFlutterActivity.class);
                intent.putExtra("key", "home");
                startActivity(intent);
                break;*/
            case R.id.btn_across_library:
                doAcrossLibrary();
        }
    }

    //跨Module，调用其它Library的接口/组件化
    private void doAcrossLibrary() {
        Add add = new Add();
        int c = add.add(500, 20);
        Toast.makeText(this, " " + c, Toast.LENGTH_SHORT).show();
    }
}
