package com.xiaoming.a001viewcustom.input.custominput;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.xiaoming.a001viewcustom.R;

public class CustomInputLayout extends RelativeLayout{
    private Context mContext;

    public CustomInputLayout(Context context) {
        this(context, null,0);
    }

    public CustomInputLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View  layout = inflater.inflate(R.layout.layout_custom_input, null);
        addView(layout);
    }
}
