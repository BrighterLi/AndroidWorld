package com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.header;

import android.accounts.AccountManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.xiaoming.a008project.R;
import com.xiaoming.a008project.fenle.BaseActivity;
import com.xiaoming.a008project.fenle.home.HomeActivity;
import com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.pull_refresh.AppBarStateChangeListener;

import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.legacy.widget.Space;

public class CollapsibleHeader extends ConstraintLayout {

    private final Context context;
    @Nullable
    private ControllerCallback callback;
    private boolean mNeesExpand;
    private AppBarStateChangeListener.State mAppbarLayoutState;
    @Nullable
    private AppBarLayout mAppBarLayout;

    private boolean isShowing = false;


    //是否打开过半浮层,这个表示app首次启动的自动打开(重新安装app重置)
    private boolean mHasOpened = false;
    //是否根据后台下发的 "isOpenFloat" 打开过，如果打开则不重进app 不再自动展开(重进app重置，重新登录重置)
    private boolean mHasAutoOpenFloat = false;
    //首页弹窗广告
    private boolean isNeedRequestAD = false;

    private View mRootView;
    private CollapsibleContent mCollapsibleContent;
    private CollapsibleTips mCollapsibleTips;

    //新人专区
    private Space spacePullDown;
    protected ImageView mClickImgLeft;
    protected ImageView mClickImgRight;
    protected TextView mPullDownTip;
    public LinearLayout mPullDownLl;
    @Nullable
    private String mPageKey;
    //0 默认type(半浮层)，1,新人专区
    public int mTypeContent = TYPE_CONTENT_0;
    public static final int TYPE_CONTENT_0 = 0;
    public static final int TYPE_CONTENT_1 = 1;



    public CollapsibleHeader(Context context) {
        this(context, null);
    }

    public CollapsibleHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CollapsibleHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        mRootView = LayoutInflater.from(context).inflate(R.layout.view_collapsible_header, this, true);
        ViewStub viewStubQuotaContent = mRootView.findViewById(R.id.viewStubQuotaContent);
        mCollapsibleContent = new CollapsibleContent(viewStubQuotaContent);
        ViewStub viewStubQuotaTip = mRootView.findViewById(R.id.viewStubQuotaTip);
        mCollapsibleTips = new CollapsibleTips(viewStubQuotaTip);

    }

    public void initPull(View rootView){
        mPullDownTip = rootView.findViewById(R.id.mPullDownTip);
        mClickImgLeft = rootView.findViewById(R.id.mClickImgLeft);
        mClickImgRight = rootView.findViewById(R.id.mClickImgRight);
        mPullDownLl = rootView.findViewById(R.id.mPullDownLl);
        spacePullDown = rootView.findViewById(R.id.spacePullDown);

        spacePullDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public void changeType(int type) {
        mTypeContent = type;
        if (type == TYPE_CONTENT_0) {
            mPullDownLl.setVisibility(View.GONE);
            spacePullDown.setVisibility(View.GONE);
            mRootView.setVisibility(View.VISIBLE);
        }else {
            mPullDownLl.setVisibility(View.VISIBLE);
            spacePullDown.setVisibility(View.VISIBLE);
            mRootView.setVisibility(View.GONE);
        }
    }

    public void setCurrentPageKey(String pageKey){
        mPageKey = pageKey;
    }



    public void setControllerCallback(ControllerCallback controllerCallback) {
        this.callback = controllerCallback;
    }

    /**
     * 折叠或展开
     */
    public void toggleExpand(){
        if (callback != null) {
            callback.toggleExpand(false);
        }
    }

    public void resume(){
        isShowing = true;
    }

    public void pause(){
        isShowing = false;
    }

    /**
     * 返回true，表示可以展示并不表示已经展示
     */
    public boolean showAble() {
        return isShowing;
    }


    public void onOffsetChanged(AppBarLayout appBarLayout, int offset){
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

    }


    /**
     * 通过这个接口回调 linkageFragment 中的方法
     */
    public interface ControllerCallback {
        void toggleExpand(boolean forceOpen);
        /**
         *
         * @param usable true 可用，false 不可用
         */
        void appbarUsableChange(boolean usable);
    }

}

