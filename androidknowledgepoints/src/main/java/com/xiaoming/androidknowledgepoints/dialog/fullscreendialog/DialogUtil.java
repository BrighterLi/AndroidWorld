package com.xiaoming.androidknowledgepoints.dialog.fullscreendialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.xiaoming.androidknowledgepoints.R;

public class DialogUtil {
    public static void showDialog(Context context) {
//        final Dialog dialog = new Dialog(context);
        final Dialog dialog = new Dialog(context, R.style.MyDialogStyle);

        Window window = dialog.getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.getDecorView().setBackgroundColor(0xFFABADB2);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(layoutParams);
        }

        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_content, null);
        contentView.findViewById(R.id.tv_content_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        /*contentView.findViewById(R.id.tv_content_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });*/

        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);

        dialog.show();
    }
}
