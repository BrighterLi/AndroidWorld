package com.xiaoming.aop.aspectj.click_demo;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class ClickTestAspect {

    @Pointcut("execution(* *(..))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String name = signature.getName();
        if (name.equals("onClick")) {
            Log.e("AspectJ", "click#preClick ");

            joinPoint.proceed();
            Log.e("AspectJ", "click#afterClick ");
        }else {
            return  joinPoint.proceed();
        }


        return null;
    }
}
