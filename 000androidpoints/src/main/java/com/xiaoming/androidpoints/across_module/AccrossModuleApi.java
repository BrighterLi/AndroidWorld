package com.xiaoming.androidpoints.across_module;

import com.alibaba.android.arouter.launcher.ARouter;

//application之间是无法相互依赖的，只能依赖library
public class AccrossModuleApi {

    public void accroosModule() {

        ISkill impl= (ISkill) ARouter.getInstance().build("/app/ISkillImpl").navigation();
        impl.eat();
    }
}
