package com.xiaoming.widgetaddressselector;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//https://blog.csdn.net/chenyingjie_Android/article/details/88765773
//https://github.com/YingJie0712/AddressSelector
//仿京东地址选择器
public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private Button btnCheck;
    private TextView tvResult;

    private int mType;// 可能是新增地址，可能是编辑地址。
    private String selectedProvince;
    private String selectedCity;
    private String selectedArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
        btnCheck = findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkType();

                PopupUtil.showRegionView(MainActivity.this, mType, selectedProvince, selectedCity, selectedArea, new PopupUtil.OnRegionListener() {
                    @Override
                    public void onRegionListener(String province, String city, String area) {
                        // 选择完回调结果赋值给当前
                        selectedProvince = province;
                        selectedCity = city;
                        selectedArea = area;

                        tvResult.setText(selectedProvince + " " + selectedCity + " " + selectedArea);

                    }
                });
            }
        });
    }

    private void checkType() {
        Log.d(TAG, "mType: " + mType);
        if (TextUtils.isEmpty(selectedProvince) && TextUtils.isEmpty(selectedCity) && TextUtils.isEmpty(selectedArea)) {
            mType = AddressConfig.TYPE_ADD;
        } else {
            mType = AddressConfig.TYPE_EDIT;
        }
    }
}
