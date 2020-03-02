package com.xiaoming.widgetreddot;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.SuperscriptSpan;
import android.util.AttributeSet;
import android.widget.TextView;

public class RedDotTextView extends TextView {
    public RedDotTextView(Context context) {
        super(context);
    }

    public RedDotTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RedDotTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RedDotTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //覆写setText方法
    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        String destStr;
        int num = Integer.parseInt(text + "");
        if(num < 0) {
            num = 0;
        }
        if(num > 99) {
            destStr = "99+";
            //富文本SpannableString
            SpannableString spannableString = new SpannableString(destStr);
            //设置想要的文本形式
            spannableString.setSpan(new SuperscriptSpan(),2,3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            super.setText(spannableString,type);
        } else if( num <= 99 && num > 0){
            super.setText(num + "");
        } else {
            super.setText(num + "");
        }
    }
}
