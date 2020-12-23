package com.xiaoming.androidpoints.aaautils;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//读取 android /system/build.prop 的最简单方法:https://blog.csdn.net/lzy0168/article/details/56481535?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param
public class AndroidPropertyUtil {

    //读取 android /system/build.prop
    public static String getProperty(final String name, final String defaultValue) throws IOException {
        Properties properties = new Properties();
        FileInputStream is = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
        properties.load(is);
        is.close();
        return properties.getProperty(name, defaultValue);
    }

}
