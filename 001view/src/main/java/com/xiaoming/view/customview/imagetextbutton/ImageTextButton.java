package com.xiaoming.view.customview.imagetextbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaoming.view.R;


//自定义View：继承方式
public class ImageTextButton extends RelativeLayout {
    private String mStrTextView;
    private int mImgId;
    private Drawable mDrawable;
    public TextView mTv;
    public ImageView mIv;
    private Context mContext;

    public ImageTextButton(Context context) {
        this(context, null);
    }

    public ImageTextButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageTextButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageTextButton, defStyleAttr, 0);
            mStrTextView = a.getString(R.styleable.ImageTextButton_text);
            //mImgId = a.getInt(R.styleable.ImageTextButton_img, 0);
            mDrawable = a.getDrawable(R.styleable.ImageTextButton_drawable);
            a.recycle();
        }

        init();
    }

    private void init() {
        View layout = LayoutInflater.from(mContext).inflate(R.layout.layout_image_text_button, this, true);
        mIv = layout.findViewById(R.id.iv);
        mTv = layout.findViewById(R.id.tv);
        if(mStrTextView != null) {
            mTv.setText(mStrTextView);
        }
        mIv.setImageDrawable(mDrawable);

    }

    public void setBtnText(String str) {
        mTv.setText(str);
        mStrTextView = str;
    }

    public void setBtnImg(Drawable drawable) {
        mIv.setImageDrawable(drawable);
    }
}
