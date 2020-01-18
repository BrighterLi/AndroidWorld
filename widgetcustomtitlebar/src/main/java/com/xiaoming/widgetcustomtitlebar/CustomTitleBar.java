package com.xiaoming.widgetcustomtitlebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;

/**
 * @Author : bright
 * @Date : 2020/01/17/02:43
 *一个简单的自定义标题栏
 */

public class CustomTitleBar extends RelativeLayout {
    private ImageView ivClose;
    private TextView tvTitle;
    private ImageView ivMore;
    private TextView tvMore;

    public CustomTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.custom_titlebar, this);

        ivClose = inflate.findViewById(R.id.iv_close);
        tvTitle = inflate.findViewById(R.id.tv_title);
        ivMore = inflate.findViewById(R.id.iv_more);
        tvMore = inflate.findViewById(R.id.tv_more);

        initRes(context, attrs);

    }

    //初始化资源文件
    private void initRes(Context context, AttributeSet attrs) {
        //TypedArray：属性的集合，AttributeSet：XML文件中定义的View的属性的集合
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
        String title = typedArray.getString(R.styleable.CustomTitleBar_title);
        int leftIcon = typedArray.getResourceId(R.styleable.CustomTitleBar_left_icon, R.drawable.icon_close);
        int rightIcon = typedArray.getResourceId(R.styleable.CustomTitleBar_right_icon, R.drawable.icon_more);
        String rightText = typedArray.getString(R.styleable.CustomTitleBar_right_text);
        int titleBarType = typedArray.getInt(R.styleable.CustomTitleBar_title_bar_type, 10);

        //给标题栏赋值
        tvTitle.setText(title);
        ivClose.setImageResource(leftIcon);
        ivMore.setImageResource(rightIcon);
        tvMore.setText(rightText);

        //根据传入的type值自定义
        if(titleBarType == 10) { //显示文字
            ivMore.setVisibility(View.GONE);
            tvMore.setVisibility(View.VISIBLE);
        } else if(titleBarType == 11) {
            tvMore.setVisibility(View.GONE);
            ivMore.setVisibility(View.VISIBLE);
        }
    }

    //左边图片点击事件
    public void setLeftIconClickListener(View.OnClickListener onClickListener) {
        ivClose.setOnClickListener(onClickListener);
    }

    //右边图片点击事件
    public void setRightIconClickListener(View.OnClickListener onClickListener) {
        ivMore.setOnClickListener(onClickListener);
    }

    //右边文字点击事件
    public void setRightTextOnClickListener(View.OnClickListener onClickListener) {
        tvMore.setOnClickListener(onClickListener);
    }

}
