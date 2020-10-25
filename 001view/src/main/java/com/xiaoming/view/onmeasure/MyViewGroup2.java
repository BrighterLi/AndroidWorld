package com.xiaoming.view.onmeasure;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


public class MyViewGroup2 extends ViewGroup {


    public MyViewGroup2(Context context) {
        this(context, null);
    }

    public MyViewGroup2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewGroup2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        这里确定吧子view的布局放在那里. 很好理解吧.一般简单的就是 横着放 竖着放 .
//        不断地更改每个子布局的位置 .
        int height = 0;
        if (getChildCount() > 0) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                MyLayoutParams ml = (MyLayoutParams) child.getLayoutParams();
                child.layout(//
                        getPaddingLeft() + ml.leftMargin,//
                        getPaddingTop() + ml.topMargin + height,//
                        getPaddingLeft() + ml.leftMargin + child.getMeasuredWidth(),//
                        getPaddingTop() + ml.topMargin + child.getMeasuredHeight() + height//
                );
                height += child.getMeasuredHeight() + ml.bottomMargin + ml.topMargin;

            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = 0;//父窗体根据子布局的大小变化 其实就是档wrap_content的时候
        int parentHeight = 0;
        int cacheWidth = 0;//缓存父宽度


        if (getChildCount() > 0) {
            //        直接获取第一个view的宽度 ,之后就和其他的view进行对比了
            View firstchild = getChildAt(0);
            MyLayoutParams firstml = (MyLayoutParams) firstchild.getLayoutParams();
            cacheWidth = firstchild.getMeasuredWidth() + firstml.leftMargin + firstml.rightMargin;

            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
//                必须要自定义 然后继承MarginLayoutParams 才能获取margin ,不然空指针
                MyLayoutParams ml = (MyLayoutParams) child.getLayoutParams();
                measureChildren(widthMeasureSpec, heightMeasureSpec);


                //我们想获取竖着的 宽度最大值的那个view的宽度作为 父窗体的宽度  (这么绕嘴呢)
                parentWidth = Math.max(cacheWidth, child.getMeasuredWidth() + ml.leftMargin + ml.rightMargin);
                cacheWidth = parentWidth;//把最大值赋值给缓存 在进行和下一个的比较
                parentHeight += child.getMeasuredHeight() + ml.topMargin + ml.bottomMargin;

            }
            //获取padding边距
            parentWidth += getPaddingLeft() + getPaddingRight();
            parentHeight += getPaddingTop() + getPaddingBottom();
        }
        // 设置最终测量值O
        setMeasuredDimension(resolveSize(parentWidth, widthMeasureSpec), resolveSize(parentHeight, heightMeasureSpec));
//        resolveSize(int,int);这个方法不要怕,就是 在 两个int值里面选择一个 作为最后只 赋值给viewgroup
//        总之就是:在AT_MOST模式下 从xml里面获取的值 和viewgroup获取子view占的面积的值 选择最小的
    }

    /*———————————————如果想获取margin 所有自定义viewgroup都要自定义这个,可以参考LinearLayout,FrameLayout等 ———————————————————*/
    public static class MyLayoutParams extends MarginLayoutParams {
        public MyLayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public MyLayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
        }

        public MyLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public MyLayoutParams(int width, int height) {
            super(width, height);
        }
    }

    @Override
    protected MyLayoutParams generateDefaultLayoutParams() {
        return new MyLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return new MyLayoutParams(p);
    }

    @Override
    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MyLayoutParams(getContext(), attrs);
    }

    @Override
    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return p instanceof MyLayoutParams;
    }

}
