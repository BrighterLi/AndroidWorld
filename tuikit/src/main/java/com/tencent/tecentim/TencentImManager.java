package com.tencent.tecentim;


import android.content.Context;
import android.util.Log;

import com.tencent.imsdk.v2.V2TIMCallback;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMSDKConfig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;
import com.tencent.tecentim.callback.LoginCallBack;
import com.tencent.tecentim.signature.GenerateTestUserSig;

public class TencentImManager {

    public static void initSDK(Context context) {
        V2TIMSDKConfig config = new V2TIMSDKConfig();
        config.setLogLevel(V2TIMSDKConfig.V2TIM_LOG_INFO);

        TUIKitConfigs configs = TUIKit.getConfigs();
        configs.setSdkConfig(new V2TIMSDKConfig());
        configs.setCustomFaceConfig(new CustomFaceConfig());
        configs.setGeneralConfig(new GeneralConfig());
        TUIKit.init(context, TencentImConfig.APP_KEY, configs);
    }


    public static void login(String userId, final LoginCallBack callBack) {
        String userSig = GenerateTestUserSig.genTestUserSig(userId);
        V2TIMManager.getInstance().login(userId, userSig, new V2TIMCallback() {

            @Override
            public void onError(int code, String desc) {
                callBack.onError(code, desc);
               Log.i("TencentImManager", "bright8#login#onError#code:" + code + " #desc:" + desc);
            }

            @Override
            public void onSuccess() {
                callBack.onSuccess();
                Log.i("TencentImManager", "bright8#login#onSuccess");
            }
        });
    }


}
