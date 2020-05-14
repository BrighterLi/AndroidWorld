package com.xiaoming.function.method;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.xiaoming.function.R;

//获取手机运营商
public class PhoneOperatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_operator);

        String phoneOperator = getPhoneOperator();
        Toast.makeText(this, "phoneOperator:" + phoneOperator, Toast.LENGTH_SHORT).show();
    }

    private String getPhoneOperator() {
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        String operator = telephonyManager.getSimOperator(); //sim提供者
        String str = "";
        if (operator != null) {
            if (operator.equals("46000") || operator.equals("46002") || operator.equals("46007") || operator.equals("46020") || operator.equals("46004")) {
                str = "中国移动";
            } else if (operator.equals("46001") || operator.equals("46006") || operator.equals("46009") || operator.equals("46010")) {
                str = "中国联通";
            } else if (operator.equals("46003") || operator.equals("46005") || operator.equals("46011")) {
                str = "中国电信";
            } else if (operator.equals("")) {
                str = "无sim卡";
            } else {
                str = "未知sim卡";
            }
        } else {
            str = "SIM卡错误";
        }
        return str;
    }
}
