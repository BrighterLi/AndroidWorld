package com.xiaoming.androidpoints.json;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.androidpoints.R;
import com.xiaoming.androidpoints.json.Gson.GsonUtil;
import com.xiaoming.androidpoints.json.json.FromJsonUtil;
import com.xiaoming.androidpoints.json.json.ToJsonUtil;

//JSON数据就是一个map
// 为什么JSON可以用来不同语言的通信呢?是因为在不同的语言当中，都可以将JSON数据转换成JSON对象


public class JsonActivity extends Activity {
    private Button mBtJson;
    private Button mBtGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        initView();
    }

    private void initView() {
        mBtJson = findViewById(R.id.bt_json);
        mBtGson = findViewById(R.id.bt_gson);

        mBtJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doJson();
            }
        });
        mBtGson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doGson();
            }
        });
    }

    private void doJson() {
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

    private void doGson() {
        //对象转成json
        //GsonUtil.objectToJson(JsonActivity.this);

        //json转对象
        GsonUtil.jsonToObject(this);
    }
}
