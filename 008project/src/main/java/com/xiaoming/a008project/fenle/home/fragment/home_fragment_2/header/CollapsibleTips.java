package com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.header;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.ViewStub;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoming.a008project.R;
import com.xiaoming.a008project.consumption.utils.ScreenUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CollapsibleTips implements View.OnClickListener {

    private ViewStub viewStub;
    private TextView textQuotaTip;
    private TextView textQuota;
    private TextView textUnit;
    private TextView textGetQuotaSuccess;
    private ImageView imgQuota;
    private ImageView imgGetQuota;
    private ImageView imgOperation;
    private View root;

    private float dp_36;
    private float dp_100;

    //true 表示处于第一阶段
    private boolean mAnimBefore = true;

    public CollapsibleTips(@NonNull ViewStub viewStub) {
        this.viewStub = viewStub;
        dp_36 = ScreenUtil.dip2px(viewStub.getContext(), 36);
        dp_100 = ScreenUtil.dip2px(viewStub.getContext(), 100);
    }

    public void show() {

        if (viewStub != null) {
            root = viewStub.inflate();
            textQuotaTip = root.findViewById(R.id.textQuotaTip);
            textQuota = root.findViewById(R.id.textQuota);
            textUnit = root.findViewById(R.id.textUnit);
            textGetQuotaSuccess = root.findViewById(R.id.textGetQuotaSuccess);
            imgQuota = root.findViewById(R.id.imgQuota);
            imgGetQuota = root.findViewById(R.id.imgGetQuota);
            imgOperation = root.findViewById(R.id.imgOperation);
            imgGetQuota.setOnClickListener(this);
            viewStub = null;
        }
        //还原状态
        root.setVisibility(View.VISIBLE);
        textQuota.setVisibility(View.GONE);
        textUnit.setVisibility(View.GONE);
        textGetQuotaSuccess.setVisibility(View.GONE);
        textQuotaTip.setVisibility(View.VISIBLE);
        imgQuota.setVisibility(View.VISIBLE);
        imgGetQuota.setVisibility(View.VISIBLE);
        imgOperation.setVisibility(View.VISIBLE);

        imgQuota.setTranslationY(0);
        textQuota.setTranslationY(0);
        textUnit.setTranslationY(0);
        textUnit.setTranslationY(0);
        textGetQuotaSuccess.setTranslationY(0);

        Typeface typeFace = null;


//                .append(data.totalCredit).setFontSize(15, true);
//        if (typeFace != null) {
//            spanUtils.setTypeface(typeFace);
//        }
        mAnimBefore = true;
    }



    @Override
    public void onClick(View v) {
        if (mAnimBefore) {


            //1、按钮点击之后消失
            v.setVisibility(View.INVISIBLE);
            imgOperation.setVisibility(View.GONE);
            textQuotaTip.setVisibility(View.GONE);
            //2、200ms 图片向下消失
            ViewPropertyAnimator translation = imgQuota.animate().translationY(imgQuota.getHeight()).setDuration(200);
            translation.start();
            translation.setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    //3、先执行数字滚动动画
                    textQuota.setVisibility(View.VISIBLE);
                    textUnit.setVisibility(View.VISIBLE);
                    ValueAnimator objectAnimator = ObjectAnimator.ofInt(0, (int) 10);
                    objectAnimator.setDuration(2160);

                    objectAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                        }
                    });
                    objectAnimator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            //3.2、数字滚动结束后，回到正确位置
                            ViewPropertyAnimator translationY = textQuota.animate().translationY(-dp_100)
                                    .setDuration(200);
                            translationY.start();
                            translationY.setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    mAnimBefore = false;
                                    imgOperation.setVisibility(View.GONE);
                                    imgGetQuota.setVisibility(View.VISIBLE);
                                }
                            });
                            //单位字符做动画
                            ViewPropertyAnimator translationYUnit = textUnit.animate().translationY(-dp_100)
                                    .setDuration(200);
                            translationYUnit.start();


                            textGetQuotaSuccess.setTranslationY(-dp_36);
                            textGetQuotaSuccess.setVisibility(View.VISIBLE);
                            textGetQuotaSuccess.animate().translationY(-dp_100)
                                    .setDuration(200).start();
                        }
                    });
                    objectAnimator.setInterpolator(new LinearInterpolator());
                    objectAnimator.start();

                    //odds 注意，有时间可以做动画合并
                    //3.1、同时执行从底部由小变大到中间的动画
                    textQuota.setScaleX(0.5f);
                    textQuota.setScaleY(0.5f);
                    ViewPropertyAnimator transAlpha = textQuota.animate();
                    transAlpha.translationY(-dp_36);
                    transAlpha.scaleX(1);
                    transAlpha.scaleY(1);
                    transAlpha.setDuration(500).start();
                    //单位字符做同样的动画
                    textUnit.setScaleX(0.5f);
                    textUnit.setScaleY(0.5f);
                    ViewPropertyAnimator transAlphaUnit = textUnit.animate();
                    transAlphaUnit.translationY(-dp_36);
                    transAlphaUnit.scaleX(1);
                    transAlphaUnit.scaleY(1);
                    transAlphaUnit.setDuration(500).start();

                }
            });
        }
    }

    /**
     * 去除末尾0，或者小数点
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
}

