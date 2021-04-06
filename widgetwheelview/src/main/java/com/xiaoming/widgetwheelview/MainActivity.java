package com.xiaoming.widgetwheelview;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView tvAge;
    private TextView tvHeight;
    private TextView tvWeight;

    private String selectedText = "";

    //数据列表
    private ArrayList<String> heightList = new ArrayList<>();
    private ArrayList<String> weightList = new ArrayList<>();
    private ArrayList<String> ageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListeners();
    }

    private void initView() {
        tvAge = findViewById(R.id.tv_age);
        tvHeight = findViewById(R.id.tv_height);
        tvWeight = findViewById(R.id.tv_weight);
    }

    private void initData() {
        //填充数据列表
        ageList.clear();
        heightList.clear();
        weightList.clear();

        for(int i=10;i<=50;i++) {
            ageList.add(String.format("%d岁",i));
        }
        for(int i=120;i<=200;i++) {
            heightList.add(String.format("%dcm", i));
        }
        for(int i=40;i<=110;i++) {
            weightList.add(String.format("%d公斤", i));
        }
    }

    private void initListeners() {
        tvAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(tvAge, ageList, 0);
            }
        });
        tvHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(tvHeight, heightList, 40);
            }
        });
        tvWeight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDialog(tvWeight, weightList, 40);
            }
        });
    }

    private void showDialog(TextView textView, ArrayList<String> list, int selected) {
       showChoiceDialog(list, textView, selected, new WheelView.OnWheelViewListener() {
           @Override
           public void onSelected(int selectedIndex, String item) { //监听回调
               selectedText = item;
           }
       });
    }

    private void showChoiceDialog(ArrayList<String> dataList, final TextView textView, int selected, WheelView.OnWheelViewListener listener) {
        selectedText = "";
        //动态加载布局dialog_wheelview
        View outerView = LayoutInflater.from(this).inflate(R.layout.dialog_wheelview, null);
        //加载布局dialog_wheelview的控件wheel_view
        //wheelView对象可以通过该种方式产生，而不是new
        final WheelView wheelView = outerView.findViewById(R.id.wheel_view);
        wheelView.setOffset(2); // 对话框中当前项上面和下面的项数
        wheelView.setItems(dataList); // 设置数据源
        wheelView.setSeletion(selected); // 默认选中项
        wheelView.setOnWheelViewListener(listener);

        // 显示对话框
        AlertDialog alertDialog = new AlertDialog.Builder(this) //创建对话框
                .setView(outerView) //将view放进Dialog,自定义对话框界面
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText(selectedText); //设置textView显示内容
                        textView.setTextColor(MainActivity.this.getResources().getColor(R.color.red)); //设置textView内容颜色
                    }
                })
                .setNegativeButton("取消",null).create();
        alertDialog.show();
    }
}
