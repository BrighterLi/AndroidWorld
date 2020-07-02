package com.xiaoming.androidkindsdemo.acrossend.flutter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

public class CustomFlutterFragment extends BaseFlutterFragment {
    private static final String TAG = "CustomFlutterFragment";
    private static final String CHANNEL = "com.example.mix_flutter/bridge";
    private MethodChannel mMethodChannel;
    private FlutterEvent mFlutterEvent;
    private String mRouterKey;
    private HashMap<String, Object> mFlutterData;


    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);

        //初始化FlutterEvent
        mFlutterEvent = new FlutterEvent(getActivity());
        mMethodChannel = new MethodChannel(flutterEngine.getDartExecutor(), CHANNEL);
        mMethodChannel.setMethodCallHandler(mFlutterEvent);
    }

    //Fragment是否隐藏
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mMethodChannel.invokeMethod("onFlutterFragment", hidden);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取CustomFlutterActivity传过来的参数
        mRouterKey = getArguments().getString("key");
        mFlutterData = new HashMap<>();
        mFlutterData.put("key",mRouterKey);
        Log.d(TAG, "bright#onCreate#mRouterKey:" + mRouterKey);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //传送给Flutter端的数据
    public HashMap<String, Object> getDataFromNative(){
        return mFlutterData;
    }
}
