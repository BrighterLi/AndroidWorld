package com.xiaoming.androidkindsdemo.acrossend.flutter;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;

import com.xiaoming.androidkindsdemo.R;
import com.xiaoming.androidkindsdemo.base.BaseActivity;

import java.util.HashMap;

/*
import io.flutter.embedding.android.FlutterView;

public class CustomFlutterActivity extends BaseActivity {
    private static final String TAG = "CustomFlutterActivity";
    private String mRouterKey;
    private CustomFlutterFragment mEngineFlutterFragment;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flutter);

        //以FlutterFragment的方式加载Flutter页面
        mEngineFlutterFragment = new CustomFlutterFragment.NewEngineFragmentBuilder(CustomFlutterFragment.class)
                .renderMode(FlutterView.RenderMode.texture)  //渲染方式
                .build();

        //MainActivity跳转过来Intent携带的参数
        mRouterKey = getIntent().getStringExtra("key");
        Log.d(TAG, "bright#onCreate#mRouterKey:" + mRouterKey);

        //将数据放在mEngineFlutterFragment里
        Bundle bundle = mEngineFlutterFragment.getArguments();
        bundle.putString("key", mRouterKey);

        FragmentManager fm = getSupportFragmentManager();
        mFragmentTransaction = fm.beginTransaction();
        //替换为mEngineFlutterFragment
        mFragmentTransaction.replace(R.id.container, mEngineFlutterFragment);
        mFragmentTransaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public HashMap<String, Object> getDataFromNative() {
        if(mEngineFlutterFragment == null) {
            return null;
        }
        return mEngineFlutterFragment.getDataFromNative();
    }

}
*/
