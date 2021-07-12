package com.xiaoming.androidkindsdemo.across_library;


import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiaoming.acrossslibrary.ISkill;

//利用ARouter实现组件间通信，解决子模块调用主模块问题: https://www.cnblogs.com/Im-Victor/p/11440023.html
@Route(path = "/app/ISkillImpl")
public class ISkillImpl implements ISkill {

    @Override
    public void eat() {
        Log.i("TAG", "bright8#大王叫我来巡山!");
    }

    @Override
    public void init(Context context) {

    }
}
