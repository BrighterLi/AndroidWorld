package com.xiaoming.androidknowledgepoints.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//json解析
public class FromJsonUtil {

    //解析出String
    public static void stringFromJson() {
        String jsonStr = "{\"request\":\"success\",\"age\":18,\"school\":\"清华大学\"}";
        try {
            Map<String, Object> map = new HashMap();
            JSONObject jsonObject = new JSONObject(jsonStr);
            int age = jsonObject.getInt("age"); //int
            String request = ((String) jsonObject.get("request")); //String
            System.out.println("bright#stringFromJson#age：" + age);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //解析出map

    //解析出list
    //根节点为”[]”的json
    public static void listFromJson() {
        String str = "[\n" +
                "    {\n" +
                "        \"id\": 1580615,\n" +
                "        \"name\": \"皮的嘛\",\n" +
                "        \"packageName\": \"com.renren.mobile.android\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 1540629,\n" +
                "        \"name\": \"不存在的\",\n" +
                "        \"packageName\": \"com.ct.client\"\n" +
                "    }\n" +
                "]";

        try {
            JSONArray jsonArray = new JSONArray(str);
            List<Map>  list = new ArrayList<>();
            for(int i= 0; i<jsonArray.length();i++) {
                Map<String, String> map = new HashMap<>();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String packageName = jsonObject.getString("packageName");
                map.put("id", id);
                map.put("name", name);
                map.put("packageName", packageName);
                list.add(map);
            }
            System.out.println("bright#list：" + list.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //解析出对象

    //解析出String、Map、List
}
