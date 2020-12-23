package com.xiaoming.androidpoints.dialog.fullscreendialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaoming.androidpoints.R;

public class FullScreenDialog extends Dialog {
    private Context mContext;
    public LinearLayout mContentTop;
    public TextView mTvContentTop;
    public LinearLayout mContentButton1;
    public TextView mTvContent1;
    public LinearLayout mContentButton2;
    public TextView mTvContent2;
    public LinearLayout mContentButton3;
    public TextView mTvContent3;
    public LinearLayout mContentBottom;
    public TextView mTvContentBottom;
    private Dialog mDialog;

    public FullScreenDialog(Context context) {
        super(context, R.style.MyDialogStyle);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showDialog() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_content, null);
        mDialog = new Dialog(mContext, R.style.MyDialogStyle);

        Window window = mDialog.getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.getDecorView().setBackgroundColor(0xFFABADB2);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(layoutParams);
        }
        mContentTop = contentView.findViewById(R.id.content_top);
        mTvContentTop =  contentView.findViewById(R.id.tv_content_top);
        mContentButton1 =  contentView.findViewById(R.id.content_button1);
        mTvContent1 =  contentView.findViewById(R.id.tv_content1);
        mContentButton2 =  contentView.findViewById(R.id.content_button2);
        mTvContent2 =  contentView.findViewById(R.id.tv_content2);
        mContentButton3 =  contentView.findViewById(R.id.content_button3);
        mTvContent3 =  contentView.findViewById(R.id.tv_content3);
        mContentBottom =  contentView.findViewById(R.id.content_bottom);
        mTvContentBottom =  contentView.findViewById(R.id.tv_content_bottom);

        mDialog.setContentView(contentView);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.setCancelable(true);
        mDialog.show();
    }
}
