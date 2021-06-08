package com.xiaoming.view.customview.commheader;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import android.widget.LinearLayout;

public class CommHeader extends LinearLayout {

    public CommHeader(Context context) {
        super(context);
    }

    public CommHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CommHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CommHeader(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}
