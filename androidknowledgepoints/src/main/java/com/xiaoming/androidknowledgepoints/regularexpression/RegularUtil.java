package com.xiaoming.androidknowledgepoints.regularexpression;

import java.util.regex.Pattern;

public class RegularUtil {
    //6位不连续、不相同纯数字的正则表达式
    //数字不是连续的
    static String password = "123456";
    static String regres = "^(?:(\\d)(?!((?<=9)8|(?<=8)7|(?<=7)6|(?<=6)5|(?<=5)4|(?<=4)3|(?<=3)2|(?<=2)1|(?<=1)0){5})(?!\1{5})(?!((?<=0)1|(?<=1)2|(?<=2)3|(?<=3)4|(?<=4)5|(?<=5)6|(?<=6)7|(?<=7)8|(?<=8)9){5})){6}$";
    //数字不是重复的
    static String reg = "^(?=.*\\d+)(?!.*?([\\d])\\1{5})[\\d]{6}$";

    public static void match() {
        if(!Pattern.matches(regres, password)||!Pattern.matches(reg,password))
        {
             //"不满足规则";
        }else{
            //"满足规则";
        }
    }

}
