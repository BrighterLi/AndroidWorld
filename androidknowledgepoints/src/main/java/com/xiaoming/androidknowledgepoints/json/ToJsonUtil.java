package com.xiaoming.androidknowledgepoints.json;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//生成json
public class ToJsonUtil {

    //String转换为JSONObject/json
    public static void stringToJson() {
        try {
            String str = "{'item':'value'}";
            JSONObject jsonObject = new JSONObject(str);
            System.out.println("bright#stringToJson#jsonObject：" + jsonObject);
            //Log.i("ToJsonUtil", "bright#stringToJson#jsonObject.toString：" + jsonObject.toString());
        } catch (JSONException e) {
            System.out.println("bright#stringToJson#e：" + e.toString());
            e.printStackTrace();
        }
    }

    //将数据直接放进JSONObject生成JSONObject/json
    public static void toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("item1", "value1" );
            jsonObject.put("item2", "value2");
            System.out.println("bright#toJson#jsonObject：" + jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //https://blog.csdn.net/u012448904/article/details/84292821
    //将数据直接放进JSONObject生成JSONObject/json
    public static void toJson2() {
        JSONObject zhangsan = new JSONObject();
        try {
            //添加
            zhangsan.put("name", "张三");
            zhangsan.put("age", 18.4);
            zhangsan.put("birthday", "1900-20-03");
            zhangsan.put("majar", new String[]{"哈哈", "嘿嘿"});
            zhangsan.put("null", null);
            zhangsan.put("house", false);
            System.out.println("bright#toJson#zhangsan：" + zhangsan);
        } catch (JSONException e) {
            System.out.println("bright#toJson2#e.toString()：" + e.toString());
            e.printStackTrace();
        }
    }

    //hashMap生成JSONObject/json
    public static void doHashMapToJson() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("item1", "value1");
        map.put("item2", "value2");
        map.put("item3", "value3");
        JSONObject jsonObject = new JSONObject(map);
        System.out.println("bright#doHashMapToJson#jsonObject：" + jsonObject);
    }

    //数组生成JSONObject/json
    public static void arrayToJson() {
        JSONObject jsonObject = new JSONObject();
        String[] array = new String[] {"item1","item2","item3"};
        try {
            jsonObject.put("array", array);
            System.out.println("bright#arrayToJson#jsonObject：" + jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //String、array、map生成JSONObject/json
    public static void multiToJson() {
        String str = "{'item1' : 'value1'}";
        try {
            JSONObject jsonObject = new JSONObject(str); //str
            String[] array = new String[] {"item2", "item3", "item4"};
            jsonObject.put("array", array); //数组
            Map<String, String> map = new HashMap<>();
            map.put("item5", "value5");
            map.put("item6", "value6");
            map.put("item7", "value7");
            jsonObject.put("map", map);
            System.out.println("bright#multiToJson#jsonObject：" + jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //list生成JSONObject/json
    public static void listToJson() {
        JSONObject jsonObject = new JSONObject();
        List<String> list = Arrays.asList("item1", "item2", "item3");
        try {
            jsonObject.put("list", list);
            System.out.println("bright#listToJson#jsonObject：" + jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //String、int、boolean、list、null生成JSONObject/json
    public static void multiToJson2() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("string", "string");
            jsonObject.put("int", 1);
            jsonObject.put("boolean", true);
            jsonObject.put("null", null);
            List<Integer> list = Arrays.asList(1,2,3);
            jsonObject.put("list", list);
            System.out.println("bright#multiToJson2#jsonObject：" + jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //对象生成
    public static void doObjectToJson() {

    }


    //生成数组/列表形式JSONObject/json
    //直接操作JSONArray
    public static void arrayJson() {
        JSONObject jsonObject = new JSONObject(); //创建json格式的数据
        JSONArray jsonArray = new JSONArray(); //json格式的数组
        JSONObject innerjsonObject = new JSONObject(); //json格式的数据
        JSONObject innerJsonObject2 = new JSONObject();//json格式的数据
        try {
            innerjsonObject.put("item1", "value1");
            innerjsonObject.put("item2", "value2");
            innerJsonObject2.put("item3", "value3");
            innerJsonObject2.put("item4", "value4");
            jsonArray.put(innerjsonObject); //将json格式的数据放到json格式的数组里
            jsonArray.put(innerJsonObject2);
            jsonObject.put("rows", jsonArray); //再将这个json格式的的数组放到最终的json对象中
            System.out.println("bright#arrayJson#jsonObj：" + jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
