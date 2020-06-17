package com.xiaoming.androidknowledgepoints.regularexpression;

import java.util.regex.Pattern;

public class RegularUtil {

    //6位不连续、不相同纯数字的正则表达式
    public static boolean match(String password) {
        //数字不是连续的
        String regres = "^(?:(\\d)(?!((?<=9)8|(?<=8)7|(?<=7)6|(?<=6)5|(?<=5)4|(?<=4)3|(?<=3)2|(?<=2)1|(?<=1)0){5})(?!\1{5})(?!((?<=0)1|(?<=1)2|(?<=2)3|(?<=3)4|(?<=4)5|(?<=5)6|(?<=6)7|(?<=7)8|(?<=8)9){5})){6}$";
        //数字不是重复的
        String reg = "^(?=.*\\d+)(?!.*?([\\d])\\1{5})[\\d]{6}$";
        if(!Pattern.matches(regres, password)||!Pattern.matches(reg,password))
        {
             return false;
        }else{
            return true;
        }
    }

    //8-20位不连续、不相同纯数字的正则表达式 ?
    public static boolean match2(String password) {
        //数字不是连续的
        String regres = "^(?:(\\d)(?!((?<=9)8|(?<=8)7|(?<=7)6|(?<=6)5|(?<=5)4|(?<=4)3|(?<=3)2|(?<=2)1|(?<=1)0){5})(?!\1{5})(?!((?<=0)1|(?<=1)2|(?<=2)3|(?<=3)4|(?<=4)5|(?<=5)6|(?<=6)7|(?<=7)8|(?<=8)9){5})){8,20}$";
        //数字不是重复的
        String reg = "^(?=.*\\d+)(?!.*?([\\d])\\1{5})[\\d]{8,20}$";
        if(!Pattern.matches(regres, password)||!Pattern.matches(reg,password))
        {
            return false;
        }else{
            return true;
        }
    }

    //6位不连续纯数字的正则表达式
    public static boolean match3(String password) {
        //数字不是连续的
        String reg = "^(?=.*\\d+)(?!.*?([\\d])\\1{5})[\\d]{8,20}$";
        if(Pattern.matches(reg,password))
        {
            return true;
        }else{
            return false;
        }
    }

    //6位不重复的纯数字的正则表达式
    public static boolean match4(String password) {
        //数字不是重复的的
        String reg = "^(?=.*\\d+)(?!.*?([\\d])\\1{5})[\\d]{6}$";
        if(Pattern.matches(reg,password))
        {
            return true;
        }else{
            return false;
        }
    }

}
