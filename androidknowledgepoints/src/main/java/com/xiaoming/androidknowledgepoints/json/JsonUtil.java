package com.xiaoming.androidknowledgepoints.json;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class JsonUtil {

    //https://blog.csdn.net/u012448904/article/details/84292821
    //通过原生生成json数据格式
    public static void toJson() {
        JSONObject zhangsan = new JSONObject();
        try {
            //添加
            zhangsan.put("name", "张三");
            zhangsan.put("age", 18.4);
            zhangsan.put("birthday", "1900-20-03");
            zhangsan.put("majar", new String[]{"哈哈", "嘿嘿"});
            zhangsan.put("null", null);
            zhangsan.put("house", false);
            System.out.println("bright#" + zhangsan.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //通过hashMap数据结构生成
    public static void doHashMapToJson() {
        HashMap<String, Object> zhangsan = new HashMap<>();
        zhangsan.put("name", "张三");
        zhangsan.put("age", 18.4);
        zhangsan.put("birthday", "1900-20-03");
        zhangsan.put("majar", new String[]{"哈哈", "嘿嘿"});
        zhangsan.put("null", null);
        zhangsan.put("house", false);
        System.out.println(new JSONObject(zhangsan).toString());
    }

    //通过实体生成
    public static void doObjectToJson() {

    }
}
