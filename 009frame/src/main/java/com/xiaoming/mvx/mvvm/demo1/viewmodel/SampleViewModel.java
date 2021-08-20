package com.xiaoming.mvx.mvvm.demo1.viewmodel;

import com.xiaoming.framearouter.databinding.ActivityMvvmBinding;
import com.xiaoming.mvx.mvvm.demo1.model.Callback;
import com.xiaoming.mvx.mvvm.demo1.model.UserInfo;

public class SampleViewModel extends AbstractViewModel<ActivityMvvmBinding> {
    public SampleViewModel(ActivityMvvmBinding viewDataBinding) {
        super(viewDataBinding);

    }

    public  void getUserInfo(String uid, Callback<UserInfo> callback) {
        //从网络或缓存获取信息，这里模拟生成一个对象
        int age = (uid == null || uid.length() == 0) ? 100 : uid.length();
        UserInfo userInfo = new UserInfo(age, uid);
        callback.onCallBack(userInfo);
    }

}
