package com.xiaoming.layoutinflater;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//https://www.cnblogs.com/wwicked/articles/4339629.html
/*
*主布局main.xml里有一个TextView和一个Button，当点击Button，
* 出现 Dialog,而这个Dialog的布局方式是我们在layout目录下定义的custom_dialog.xml文件
* (里面左右分布，左边 ImageView,右边TextView)。
* */
public class MainActivity extends AppCompatActivity {
    private Button btClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btClick = findViewById(R.id.bt_click);
        btClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder;
        AlertDialog alertDialog;
        Context context = MainActivity.this;
        //获取LayoutInflater服务
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        //动态加载布局
        View dialogLayout = inflater.inflate(R.layout.custom_dialog,null);
        //加载布局中具体的控件
        TextView textView = dialogLayout.findViewById(R.id.tv_dialog);
        textView.setText("Hello");
        ImageView imageView = dialogLayout.findViewById(R.id.iv_dialog);
        imageView.setImageResource(R.drawable.ic_launcher_background);

        builder = new AlertDialog.Builder(context); //创建builder
        builder.setView(dialogLayout);  //将view放入builder
        alertDialog = builder.create(); //创建dialog
        alertDialog.show();  //显示dialog
    }
}
