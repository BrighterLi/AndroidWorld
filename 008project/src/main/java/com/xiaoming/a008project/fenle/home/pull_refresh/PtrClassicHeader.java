package com.xiaoming.a008project.fenle.home.pull_refresh;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.xiaoming.a008project.R;

import in.srain.cube.views.wt.PtrFrameLayout;
import in.srain.cube.views.wt.PtrUIHandler;
import in.srain.cube.views.wt.indicator.PtrIndicator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class PtrClassicHeader extends FrameLayout implements PtrUIHandler { //实现接口
    private ImageView mPush;
    private TextView mTitleTextView;

    //在代码创建对象
    public PtrClassicHeader(Context context) {
        super(context);
        initView();
    }
    public PtrClassicHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    public PtrClassicHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    //初始化自定义布局文件
    private void initView() {
        //这里加载自定义的布局文件
        View header =  LayoutInflater.from(getContext()).inflate(R.layout.item_push_header_layout, this);
        //找到布局内部的控件
        mPush = (ImageView) header.findViewById(R.id.header_iv);
        mTitleTextView = header.findViewById(R.id.head_tv);
    }

    //定义一个动画,方便下面的调用
    public void initAnim(){
        ObjectAnimator anim = ObjectAnimator.ofFloat(mPush, "rotation", 0f, 180f);
        anim.setDuration(500);
        anim.start();

    }
    //初始化状态
    @Override
    public void onUIReset(PtrFrameLayout frame) {
        //这个方法可以不用管  也可以在这里关闭动画
    }

    //开始向下拉的时候调用
    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        initAnim(); //这里可以执行动画效果
    }

    //刷新过程时调用
    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        //可以不断的改变动画效果以及切换显示的控件
        //判断是否可以刷新
        if (frame.isPullToRefresh()) {
            mTitleTextView.setText("释放刷新");
        } else {
            mTitleTextView.setText("下拉加载");
        }
    }

    //刷新完成后调用,向上移动时调用
    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        //可以不断的改变动画效果以及切换显示的控件
        mTitleTextView.setText("加载中...");
        //animationDrawable.stop(); //模拟动画
        //animationDrawable.start();
    }

    //重复下拉
    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        //在同一次下拉中不断向上向下移动,这里可以不断改变显示效果
        //示例代码: 可以当模板使用
        final int mOffsetToRefresh = frame.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY(); //获取到下拉的高度
        final int lastPos = ptrIndicator.getLastPosY();   //最大下拉的高度
        //根据下拉的位置进行控件的显示
        if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                crossRotateLineFromBottomUnderTouch(frame); //调用方法
            }
        } else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                crossRotateLineFromTopUnderTouch(frame); //调用方法
            }
        }
    }

    @Override
    public void onUIRefreshGoDown(PtrFrameLayout frame, boolean begin, boolean finish) {

    }

    //下拉到可以刷新时显示
    private void crossRotateLineFromTopUnderTouch(PtrFrameLayout frame) {
        if (!frame.isPullToRefresh()) {
            mTitleTextView.setText("释放刷新");
        }
    }
    //动态改变文字
    private void crossRotateLineFromBottomUnderTouch(PtrFrameLayout frame) {
        if (frame.isPullToRefresh()) {
            mTitleTextView.setText("释放刷新");
        } else {
            mTitleTextView.setText("下拉加载");
        }
    }

}
