package com.xiaoming.aop.aspectj.activity_life_demo;


import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

//在Activity的声明周期方法中开始添加一个打印方法名称的aspect，我们需要创建切面类，然后定义PointCut

//@Aspect，它用来描述当前类为aspect类
@Aspect
public class ActivityAspect {

    //PointCut:切点，Aop通过切点定位特定的连接点
    //新建了一个空实现的方法，用@Pointcut注解标注，其value内容为"execution(* com.example.htestproj.MainActivity.on**(..))",
    // 该Pointcut表示是一个execution类型的JoinPoint，它筛选的对象为onXX开头的方法。
    @Pointcut("execution(* com.xiaoming.aop.aspectj.demo.AspectJActivity.on**(..))")
    public void actlifecycle() {
    }

    //advice:增强，是织入到目标类连接点上的一段程序代码
    //JoinPoint: 连接点，程序执行的某个特定位置
    // 定义好Pointcut之后就可以创建advice了，通过设置advice我们可以指定代码注入的时机，是位于pointcut之前还是之后，或者两者都有
    @Before("actlifecycle()")
    public void beforeLifeCycle(JoinPoint joinPoint) throws Throwable {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        String methodName = ms.getName();
        String cname = joinPoint.getThis().getClass().getSimpleName();
        Log.d("hgl_tag", "inject log  before " + methodName + " in class: " + cname);
    }


}
