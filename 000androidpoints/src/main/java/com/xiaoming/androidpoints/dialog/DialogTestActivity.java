package com.xiaoming.androidpoints.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoming.androidpoints.R;
import com.xiaoming.androidpoints.dialog.fullscreendialog.FullScreenDialog;

public class DialogTestActivity extends Activity {
    private Button btnClick;
    private Button mBtCustomLayoutDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_test);

        btnClick = findViewById(R.id.btn_click);
        mBtCustomLayoutDialog = findViewById(R.id.btn_custom_layout_dialog);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFullScreenDialog();
            }
        });
        mBtCustomLayoutDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
    }



    //https://www.cnblogs.com/wwicked/articles/4339629.html
    /*
     *主布局main.xml里有一个TextView和一个Button，当点击Button，
     * 出现 Dialog,而这个Dialog的布局方式是我们在layout目录下定义的custom_dialog.xml文件
     * (里面左右分布，左边 ImageView,右边TextView)。
     * */
    private void showFullScreenDialog() {
        FullScreenDialog fullScreenDialog = new FullScreenDialog(this);
        fullScreenDialog.showDialog();
        fullScreenDialog.mTvContentTop.setText("更新标题");
        fullScreenDialog.mContentButton1.setVisibility(View.VISIBLE);
        fullScreenDialog.mTvContent1.setText("更新按钮1");
        fullScreenDialog.mContentButton2.setVisibility(View.VISIBLE);
        fullScreenDialog.mTvContent2.setText("更新按钮2");
        fullScreenDialog.mContentButton3.setVisibility(View.VISIBLE);
        fullScreenDialog.mTvContent3.setText("更新按钮3");
        fullScreenDialog.mContentBottom.setVisibility(View.VISIBLE);
        fullScreenDialog.mTvContentBottom.setText("更新底部按钮");

        fullScreenDialog.mContentButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DialogTestActivity.this, "点击了按钮1", Toast.LENGTH_SHORT).show();
            }
        });
        fullScreenDialog.mContentButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DialogTestActivity.this, "点击了按钮2", Toast.LENGTH_SHORT).show();
            }
        });
        fullScreenDialog.mContentButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DialogTestActivity.this, "点击了按钮3", Toast.LENGTH_SHORT).show();
            }
        });
        fullScreenDialog.mTvContentBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DialogTestActivity.this, "点击了底部按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //自定义Dialog的布局
    private void showCustomDialog() {
        AlertDialog.Builder builder;
        AlertDialog alertDialog;
        Context context = DialogTestActivity.this;
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
