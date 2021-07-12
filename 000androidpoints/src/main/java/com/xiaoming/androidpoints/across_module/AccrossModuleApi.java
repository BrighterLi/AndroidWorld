package com.xiaoming.androidpoints.across_module;

import com.alibaba.android.arouter.launcher.ARouter;

public class AccrossModuleApi {

    public void accroosModule() {

        ISkill impl= (ISkill) ARouter.getInstance().build("/app/ISkillImpl").navigation();
        impl.eat();
    }
}
