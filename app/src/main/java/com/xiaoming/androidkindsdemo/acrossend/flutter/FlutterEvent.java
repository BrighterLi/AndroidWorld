package com.xiaoming.androidkindsdemo.acrossend.flutter;

import android.app.Activity;
import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.util.HashMap;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class FlutterEvent implements MethodChannel.MethodCallHandler {
    private Activity mActivity;

    public FlutterEvent(Activity activity) {
        mActivity = activity;
    }

    //Flutter调用对应Native端对应的方法
    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        String method = call.method;  //flutter端调用的方法名
        String arguments = ""; //flutter端传过来的参数
        if (call.arguments != null) {
            arguments = new Gson().toJson(call.arguments); //flutter传过来的数据序列化
        }

        switch (method) {
            case "getDataFromNative":
                if (mActivity instanceof CustomFlutterActivity) {
                    HashMap<String, Object> mFlutterData = ((CustomFlutterActivity) mActivity).getDataFromNative();
                    //native回传数据给Flutter端
                    result.success(mFlutterData);
                }
                break;
            default:
                if (result != null) {
                    result.notImplemented();
                }
                break;
        }
    }
}
