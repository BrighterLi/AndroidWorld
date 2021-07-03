package com.xiaoming.androidpoints.aaabug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by brightli on 2021/7/3
 */

//一文搞懂java.util.ConcurrentModificationException异常原因和解决方法:https://blog.csdn.net/belongtocode/article/details/107981590
public class ConcurrentModificationException {

    public static void testList() {
        List<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        for (String item : list) {
            if (item.equals("1")) {
                System.out.println(item);
                list.remove(item);
            }

        }
        System.out.println(list.size());
    }

    public static void testMap() {
        HashMap<String, Integer> temp = new HashMap<>();
        temp.put("aa", 123);
        temp.put("bb", 123);
        temp.put("cc", 123);
        temp.put("dd", 123);
        for (String s : temp.keySet()) {
            if (s.equals("aa")) {
                temp.put("abcd", 123);
            }
        }

    }


}
