package com.xiaoming.function.phone_data.sms;

public class StringUtil {

    public static boolean isPhone(String phone) {
        //第三种为了兼容测试帐号
        return phone.matches("1[0-9]{10}|\\+?86 ?1[0-9]{10}") || phone.matches("9[0-9]{10}");
    }

}
