package com.xiaoming.androidpoints;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

//判断Android设备是否拥有Root权限
//https://blog.csdn.net/xianglin2111/article/details/79046622/
public class IsRootActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_is_root);

        boolean isRoot = isRoot();
        Toast.makeText(this, "是否root:" + isRoot, Toast.LENGTH_SHORT).show();
    }

    private boolean isRoot() {
        String binPath =  "/system/bin/su";
        String xBinPath =  "/system/xbin/su";

        if(new File(binPath).exists() && isCanExecute(binPath)) {
            return true;
        }
        if(new File(xBinPath).exists() && isCanExecute(xBinPath)) {
            return true;
        }
        return false;
    }

    private boolean isCanExecute(String filePath) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("ls -l " + filePath);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String str = in.readLine();
            if(str != null && str.length() >= 4) {
                char flag = str.charAt(3);
                if(flag == 's' || flag == 'x') {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(process != null) {
                process.destroy();
            }
        }
    return false;
    }
}
