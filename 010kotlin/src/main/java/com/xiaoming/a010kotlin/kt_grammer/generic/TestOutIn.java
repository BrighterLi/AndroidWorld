package com.xiaoming.a010kotlin.kt_grammer.generic;

import com.xiaoming.a010kotlin.kt_grammer.generic.obj.FuClass;
import com.xiaoming.a010kotlin.kt_grammer.generic.obj.ZiClass;

import java.util.ArrayList;
import java.util.List;

// 学Java基础的时候，老师重点讲的东西，  限制  super String    extends String
public class TestOutIn<T extends String> {

    FuClass fuClass = new FuClass();
    ZiClass ziClass = new ZiClass();

    // 学Java基础的时候，老师不讲这个东西
    // 泛型读取模式
    void test01() {

        // ? extends FuClass  == 只能获取，不能修改
        List<? extends FuClass> list = new ArrayList<ZiClass>();

        // 不能修改
        /*list.add(fuClass);
        list.add(ziClass);*/

        // 能获取
        FuClass fuClass = list.get(0);

        // TODO  =============================================


        // ? super ZiClass == 只能修改，不能获取
        List<? super ZiClass> list2 = new ArrayList<FuClass>();

        // 能添加
        list2.add(ziClass);

        // 不能获取
        // ZiClass ziClass = list2.get(0);
    }


    private void forShow(List<? extends FuClass> lists) {
        // 能获取吗？ 可以获取的
        lists.get(0);
        for (FuClass list : lists) {

        }

        // 能修改吗
        // lists.add(fuClass)
    }
    void test02() {
        List<ZiClass> ziClasses = new ArrayList<ZiClass>();
        forShow(ziClasses);
    }

    // TODO  ===================================

    private void forMethod(List<? super ZiClass> lists) {
        // 能获取吗？ 不可以获取的
        /*ZiClass ziClass = lists.get(0);
        for (FuClass list : lists) {

        }*/

        // 能修改吗？  能修改
        lists.add(ziClass);
    }
    void test03() {
        List<FuClass> fuClasses = new ArrayList<FuClass>();
        forMethod(fuClasses);
    }

}

