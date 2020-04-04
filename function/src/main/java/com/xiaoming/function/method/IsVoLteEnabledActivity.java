package com.xiaoming.function.method;

import android.content.Context;
import android.os.Build;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.xiaoming.function.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//判断Android设备VoLte是否开启
//https://blog.csdn.net/wxc880924/article/details/54630378/
public class IsVoLteEnabledActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_is_vo_lte_enabled);

        boolean isEnabled = isVolteAvailable();
        Toast.makeText(this, isEnabled + "", Toast.LENGTH_SHORT).show();

    }

    //?
    public boolean isVolteAvailable() {
        boolean isvoLte = false;
        if(Build.VERSION.SDK_INT >= 23){
            TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            Class<? extends TelephonyManager> teleclass = telephonyManager.getClass();
            Method method = null;
            try {
                method = teleclass.getDeclaredMethod("isVolteAvailable");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            method.setAccessible(true);
            try {
                isvoLte = (boolean) method.invoke(telephonyManager);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return isvoLte;
    }

    /*public void isVolteEnabled2() {
        Class<?> GlobalClass = Settings.Global.getClass();
            Field field = null;
            try {
                field = GlobalClass.getDeclaredField("ENHANCED_4G_MODE_ENABLED");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            field.setAccessible(true);
            String volteStr = (String) field.get(Settings.Global);

        try {
            int VoLteState  = Settings.Global.getInt(getContentResolver(), volteStr);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }
*/
}
