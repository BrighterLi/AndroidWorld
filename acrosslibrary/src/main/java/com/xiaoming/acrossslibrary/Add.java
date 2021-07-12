package com.xiaoming.acrossslibrary;


import com.alibaba.android.arouter.launcher.ARouter;

public class Add {
    public int add(int a, int b) {

        ISkill impl= (ISkill) ARouter.getInstance().build("/app/ISkillImpl").navigation();
        impl.eat();

        return a + b;
    }
}
