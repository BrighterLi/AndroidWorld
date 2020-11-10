package com.xiaoming.androidknowledgepoints.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaoming.androidknowledgepoints.R;
import com.xiaoming.androidknowledgepoints.json.json.FromJsonUtil;
import com.xiaoming.androidknowledgepoints.json.json.ToJsonUtil;

//JSON数据就是一个map
// 为什么JSON可以用来不同语言的通信呢?是因为在不同的语言当中，都可以将JSON数据转换成JSON对象


public class JsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        //生成json
        ToJsonUtil.stringToJson();
        ToJsonUtil.toJson();
        ToJsonUtil.toJson2();
        ToJsonUtil.doHashMapToJson();
        ToJsonUtil.arrayToJson();
        ToJsonUtil.multiToJson();
        ToJsonUtil.listToJson();
        ToJsonUtil.multiToJson2();

        ToJsonUtil.arrayJson();

        //json解析
        FromJsonUtil.stringFromJson();
        FromJsonUtil.listFromJson();
    }
}
