package com.xiaoming.widgetdialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView demoListView;
    private List<String> demoListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDemoListData();
        demoListView = findViewById(R.id.lv_main);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, demoListData);
        demoListView.setAdapter(arrayAdapter);

        demoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, AlertDialogActivity.class));
                        break;
                }
            }
        });
    }
    private void initDemoListData() {
        demoListData = new ArrayList<>();
        demoListData.add("AlertDialog");
    }
}
