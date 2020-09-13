package com.xiaoming.a001viewcustom.input.custominput2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.xiaoming.a001viewcustom.R;

public class CustomInputLayout2 extends RelativeLayout{
    private Context mContext;

    public CustomInputLayout2(Context context) {
        this(context, null,0);
    }

    public CustomInputLayout2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomInputLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View  layout = inflater.inflate(R.layout.layout_custom_input2, null);
        addView(layout);
    }
}
