package com.widget.dialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.widget.R;

//Android中动态添加View的两种方法:https://www.cnblogs.com/joeleedreamer/p/4662503.html
public class DialogTestActivity extends AppCompatActivity {
    private Button mBtFullScreenDialog;
    private Button mBtFullScreenDialogDynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_test);

        initView();
    }

    private void initView() {
        mBtFullScreenDialog = findViewById(R.id.bt_full_screen_dialog);
        mBtFullScreenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               FullScreenDialog dialog = new FullScreenDialog(DialogTestActivity.this,  WindowManager.LayoutParams.MATCH_PARENT);
                dialog.showDialog();
               dialog.mContentButton1.setVisibility(View.VISIBLE);
               dialog.mTvContent1.setText("Button1");
               dialog.mContentBottom.setVisibility(View.VISIBLE);
               dialog.mTvContentBottom.setText("取消");
            }
        });

        //动态添加Dialog的内容
        mBtFullScreenDialogDynamic = findViewById(R.id.bt_full_screen_dialog_dynamic);
        mBtFullScreenDialogDynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullScreenDialog dialog2 = new FullScreenDialog(DialogTestActivity.this,  WindowManager.LayoutParams.MATCH_PARENT);
                dialog2.showDialog();
                dialog2.mContentBottom.setVisibility(View.VISIBLE);
                dialog2.mTvContentBottom.setText("取消");

                //使用布局方式添加
                LinearLayout dialogItem = ((LinearLayout) getLayoutInflater().inflate(R.layout.dialog_content_item, null));
                TextView textView = dialogItem.findViewById(R.id.tv_content);
                textView.setText("Button1");
                dialog2.mContentButtonContainer.addView(dialogItem);
                LinearLayout dialogItem2 = ((LinearLayout) getLayoutInflater().inflate(R.layout.dialog_content_item, null));
                TextView textView2 = dialogItem2.findViewById(R.id.tv_content);
                textView2.setText("Button2");
                dialog2.mContentButtonContainer.addView(dialogItem2);
                LinearLayout dialogItem3 = ((LinearLayout) getLayoutInflater().inflate(R.layout.dialog_content_item, null));
                TextView textView3 = dialogItem3.findViewById(R.id.tv_content);
                textView3.setText("Button3");
                dialog2.mContentButtonContainer.addView(dialogItem3);
                dialogItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DialogTestActivity.this, "click button1", Toast.LENGTH_SHORT).show();
                    }
                });
                dialogItem2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DialogTestActivity.this, "click button2", Toast.LENGTH_SHORT).show();
                    }
                });
                dialogItem3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DialogTestActivity.this, "click button3", Toast.LENGTH_SHORT).show();
                    }
                });

                //使用java代码方式添加
               /* LinearLayout container = new LinearLayout(DialogTestActivity.this);//主布局container
                TextView textView1 = new TextView(DialogTestActivity.this);
                // 为主布局container设置布局参数
                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                        WindowManager.LayoutParams.FILL_PARENT, 52);
                textView1.setLayoutParams(llp);//设置container的布局
                textView1.setText("Button 1");
                dialog2.mContentButtonContainer.addView(textView1);*/

            }
        });
    }
}
