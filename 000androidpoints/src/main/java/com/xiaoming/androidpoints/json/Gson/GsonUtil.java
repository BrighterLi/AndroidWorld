package com.xiaoming.androidpoints.json.Gson;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

//Gson基本用法:https://blog.csdn.net/chenrenxiang/article/details/80291224
public class GsonUtil {

    //1 简单的 Java Object 序列化/反序列化
    //序列化/对象转json
    public static void objectToJson(Context context) {
        User user = new User("xiaoming", 21);
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        Toast.makeText(context,  "userJson:  " + userJson, Toast.LENGTH_LONG).show();
    }

    //反序列化/json转对象
    public static void jsonToObject(Context context) {
        String userJson = "{'name': 'xiaohua', 'age': '18'}"; //json字符串，即一个String
        Gson gson = new Gson();
        User user = gson.fromJson(userJson, User.class);
        Toast.makeText(context,  "user.name:  " + user.name + "    user.age:   " + user.age, Toast.LENGTH_LONG).show();
    }

    //2 嵌套序列化/反序列化：一个类里面还包含有其它类

    //3 Array 和 List 的序列化/反序列化

    //4 Map 和 Set 的序列化/反序列化

    //5 变量值为null时的序列化/反序列化
      //当某个变量值为null时，Gson在序列化的时候直接把这个变量忽略了
      //对于JSON字符串里没有的变量，Gson在反序列化时会给它一个默认值，int类型默认为0，bool类型默认为false，String类型默认为null。

    //6  控制序列化/反序列化 的变量名称
       //注解方法@SerializedName进行隐射

    //7 序列化/反序列化过程中忽略某些变量
}
