package com.widget.pullrefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.widget.R;

import java.util.Date;

//Android ListView实现下拉加载功能:https://www.jb51.net/article/121864.htm
public class MyListView extends ListView implements OnScrollListener {
    private final static int RELEASE_TO_REFRESH = 0; // 下拉过程的状态值
    private final static int PULL_TO_REFRESH = 1; // 从下拉返回到不刷新的状态值
    private final static int REFRESHING = 2; // 正在刷新的状态值
    private final static int DONE = 3;
    private final static int LOADING = 4;

    //实际的padding的距离与界面上偏移距离的比例
    private final static int RATIO = 3;
    private LayoutInflater inflater;

    // ListView头部下拉刷新的布局
    private LinearLayout headerView;
    private TextView lvHeaderTipsTv;
    private TextView lvHeaderLastUpdatedTv;
    private ImageView lvHeaderArrowIv;
    private ProgressBar lvHeaderProgressBar;

    //定义头部下拉刷新的布局的高度
    private int headerContentHeight;

    private RotateAnimation animation;
    private RotateAnimation reverseAnimation;

    private int startY;
    private int state;
    private boolean isBack;

    // 用于保证startY的值在一个完整的touch事件中只被记录一次
    private boolean isRecored;
    private OnRefreshListener refreshListener;
    private boolean isRefreshable;


    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem == 0) {
            isRefreshable = true;
        } else {
            isRefreshable = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isRefreshable) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (!isRecored) {
                        isRecored = true;
                        startY = ((int) ev.getY()); // 手指按下时记录当前位置
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (state != REFRESHING && state != LOADING) {
                        if (state == PULL_TO_REFRESH) {
                            state = DONE;
                            changeHeaderViewByStyle();
                        }
                        if(state == RELEASE_TO_REFRESH) {
                            state = REFRESHING;
                            changeHeaderViewByStyle();
                            onLvRefresh();
                        }
                    }
                    isRecored = false;
                    isBack = false;

                    break;

                case MotionEvent.ACTION_MOVE:
                    int tempY = ((int) ev.getY());
                    if (!isRecored) {
                        isRecored = true;
                        startY = tempY;
                    }
                    if (state != REFRESHING && isRecored && state != LOADING) {
                        // 保证在设置padding的过程中，当前的位置一直是在head，否则如果当列表超出屏幕的话，当在上推的时候，列表会同时进行滚动
                        // 可以松手去刷新了
                        if (state == RELEASE_TO_REFRESH) {
                            setSelection(0);
                            // 往上推了，推到了屏幕足够掩盖head的程度，但是还没有推到全部掩盖的地步
                            if (((tempY - startY) / RATIO < headerContentHeight) && (tempY - startY) > 0) { //由松开刷新状态转变到下拉刷新状态
                                state = PULL_TO_REFRESH;
                                changeHeaderViewByStyle();
                            }
                            // 一下子推到顶了
                            else if (tempY - startY <= 0) { // 由松开刷新状态转变到done状态
                                state = DONE;
                                changeHeaderViewByStyle();
                            }
                        }
                        // 还没有到达显示松开刷新的时候,DONE或者是PULL_To_REFRESH状态
                        if(state == PULL_TO_REFRESH) {
                            setSelection(0);
                            // 下拉到可以进入RELEASE_TO_REFRESH的状态
                            if((tempY - startY) / RATIO >= headerContentHeight) { // 由done或者下拉刷新状态转变到松开刷新
                                state = RELEASE_TO_REFRESH;
                                isBack = true;
                                changeHeaderViewByStyle();
                            }
                            // 上推到顶了
                            else if(tempY - startY <= 0) { // 由DOne或者下拉刷新状态转变到done状态
                                state = DONE;
                                changeHeaderViewByStyle();
                            }
                        }
                        // done状态下
                        if(state == DONE) {
                            if(tempY - startY > 0) {
                                state = PULL_TO_REFRESH;
                                changeHeaderViewByStyle();
                            }
                        }
                        // 更新headView的size
                        if(state == PULL_TO_REFRESH) {
                            headerView.setPadding(0, -1 * headerContentHeight + (tempY - startY) / RATIO, 0, 0);
                        }
                        // 更新headView的paddingTop
                        if(state == RELEASE_TO_REFRESH) {
                            headerView.setPadding(0, (tempY - startY) / RATIO - headerContentHeight, 0 ,0);
                        }
                    }
                    break;

                    default:
                        break;
            }
        }
        return super.onTouchEvent(ev);
    }

    private void init(Context context) {
        inflater = LayoutInflater.from(context);
        headerView = ((LinearLayout) inflater.inflate(R.layout.lv_header, null));
        lvHeaderTipsTv = headerView.findViewById(R.id.lvHeaderTipsTv);
        lvHeaderLastUpdatedTv = headerView.findViewById(R.id.lvHeaderLastUpdatedTv);
        lvHeaderArrowIv = headerView.findViewById(R.id.lvHeaderArrowIv);
        // 设置下拉刷新图标的最小高度和宽度
        lvHeaderArrowIv.setMinimumWidth(70);
        lvHeaderArrowIv.setMinimumHeight(50);

        lvHeaderProgressBar = headerView.findViewById(R.id.lvHeaderProgressBar);
        measureView(headerView);
        headerContentHeight = headerView.getMeasuredHeight();
        // 设置内边距，正好距离顶部为一个负的整个布局的高度，正好把头部隐藏
        headerView.setPadding(0, -1 * headerContentHeight, 0, 0);
        // 重绘一下
        headerView.invalidate();
        // 将下拉刷新的布局加入ListView的顶部
        addHeaderView(headerView, null, false);
        // 设置滚动监听事件
        setOnScrollListener(this);

        // 设置旋转动画事件
        animation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(250);
        animation.setFillAfter(true);

        reverseAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        reverseAnimation.setInterpolator(new LinearInterpolator());
        reverseAnimation.setFillAfter(true);

        // 一开始的状态就是下拉刷新完的状态，所以为DONE
        state = DONE;
        // 是否正在刷新
        isRefreshable = false;
    }

    // 此方法直接照搬自网络上的一个下拉刷新的demo，此处是“估计”headView的width以及height
    private void measureView(View child) {
        ViewGroup.LayoutParams params = child.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, params.width);
        int lpHeight = params.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    public void setonRefreshListener(OnRefreshListener refreshListener) {
        this.refreshListener = refreshListener;
        isRefreshable = true;
    }

    public interface OnRefreshListener {
         void onRefresh();
    }

    public void onRefreshComplete() {
        state = DONE;
        lvHeaderLastUpdatedTv.setText("最近更新：" + new Date().toLocaleString());
        changeHeaderViewByStyle();
    }

    private void onLvRefresh() {
        if (refreshListener != null) {
            refreshListener.onRefresh();
        }
    }

    public void setAdapter(ListViewAdapter adapter) {
        lvHeaderLastUpdatedTv.setText("最近更新:" + new Date().toString());
        super.setAdapter(adapter);
    }

    // 当状态改变时候，调用该方法，以更新界面
    private void changeHeaderViewByStyle() {
        switch (state) {
            case RELEASE_TO_REFRESH:
                lvHeaderArrowIv.setVisibility(View.VISIBLE);
                lvHeaderProgressBar.setVisibility(View.GONE);
                lvHeaderTipsTv.setVisibility(View.VISIBLE);
                lvHeaderLastUpdatedTv.setVisibility(View.VISIBLE);

                lvHeaderArrowIv.clearAnimation(); //清除动画
                lvHeaderArrowIv.startAnimation(animation); // 开始动画效果

                lvHeaderTipsTv.setText("松开刷新");
                break;
            case PULL_TO_REFRESH:
                lvHeaderProgressBar.setVisibility(View.GONE);
                lvHeaderTipsTv.setVisibility(View.VISIBLE);
                lvHeaderLastUpdatedTv.setVisibility(View.VISIBLE);
                lvHeaderArrowIv.clearAnimation();
                lvHeaderArrowIv.setVisibility(View.VISIBLE);
                // 是由RELEASE_To_REFRESH状态转变来的
                if (isBack) {
                    isBack = false;
                    lvHeaderArrowIv.clearAnimation();
                    lvHeaderArrowIv.startAnimation(reverseAnimation);
                    lvHeaderTipsTv.setText("下拉刷新");
                } else {
                    lvHeaderTipsTv.setText("下拉刷新");
                }
                break;
            case REFRESHING:
                headerView.setPadding(0, 0, 0, 0);

                lvHeaderProgressBar.setVisibility(View.VISIBLE);
                lvHeaderArrowIv.clearAnimation();
                lvHeaderArrowIv.setVisibility(View.GONE);
                lvHeaderTipsTv.setText("正在刷新...");
                lvHeaderLastUpdatedTv.setVisibility(View.VISIBLE);
                break;
            case DONE:
                headerView.setPadding(0, -1 * headerContentHeight, 0, 0);

                lvHeaderProgressBar.setVisibility(View.GONE);
                lvHeaderArrowIv.clearAnimation();
                lvHeaderArrowIv.setImageResource(R.drawable.arrow_more);
                lvHeaderTipsTv.setText("下拉刷新");
                lvHeaderLastUpdatedTv.setVisibility(View.VISIBLE);
                break;
        }
    }
}
