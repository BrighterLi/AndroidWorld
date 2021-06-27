package com.xiaoming.a008project.fenle.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import in.srain.cube.views.wt.PtrDefaultHandler;
import in.srain.cube.views.wt.PtrFrameLayout;
import in.srain.cube.views.wt.PtrHandler;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.xiaoming.a008project.R;
import com.xiaoming.a008project.fenle.RouterKey;
import com.xiaoming.a008project.fenle.home.bottom_tab.TabController;
import com.xiaoming.a008project.fenle.home.fragment.FirstFragment;
import com.xiaoming.a008project.fenle.home.header.CommHeader;
import com.xiaoming.a008project.fenle.home.pull_refresh.PtrClassicHeader;

public class HomeActivity extends FragmentActivity implements TabController.TabClickListener {
    private LinearLayout mVHomeTabs;
    private CommHeader mCommHeader;
    private TabController mTabController;
    private PtrFrameLayout mPtrRootFrame;
    private PtrClassicHeader mPtrFloorHeader;

    private FragmentManager fragmentManager;
    private FirstFragment f1, f2, f3, f4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bindViewFromXml();
    }

    private void bindViewFromXml() {
        mVHomeTabs = (LinearLayout) findViewById(R.id.mVHomeTabs);
        mCommHeader = (CommHeader) findViewById(R.id.mCommHeader);
        mCommHeader.bind(this);
        mTabController = new TabController(this, mVHomeTabs);
        mPtrRootFrame = (PtrFrameLayout) findViewById(R.id.mPtrRootFrame);
        mPtrFloorHeader = (PtrClassicHeader) findViewById(R.id.mPtrFloorHeader);

        initBottomTab();
        initPullToRefresh();
    }

    private void initBottomTab() {
        mTabController.initBottomTab();
        mTabController.setTabClickListener(this);
    }


    private void initPullToRefresh() {
        mPtrRootFrame.setResistance(1.7f); //设置下拉的阻尼系数，值越大感觉越难下拉
        mPtrRootFrame.setOffsetToRefresh(30);
        mPtrRootFrame.setOffsetToKeepHeaderWhileLoading(30);
        mPtrRootFrame.setDurationToClose(300); //设置下拉回弹的时间
        mPtrRootFrame.setDurationToCloseHeader(500); //设置刷新完成，头部回弹时间，注意和前一个进行区别
        mPtrRootFrame.setPullToRefresh(false); //设置下拉过程中执行刷新，我们一般设置为false
        mPtrRootFrame.setKeepHeaderWhenRefresh(true); //设置刷新的时候是否保持头部
        mPtrRootFrame.disableWhenHorizontalMove(true);
        mPtrRootFrame.addPtrUIHandler(mPtrFloorHeader);
        mPtrRootFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                mPtrRootFrame.refreshComplete();
            }
        });
    }

    @Override
    public void onTabClick(int index, View v) {
        showFragment(index);
    }

    private void showFragment(int index) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideAllFragment(transaction);
        switch (index) {
            case 0:
                if(f1 == null) {
                    f1 = new FirstFragment("第一个Fragment");
                    //将对应的Fragment添加到fragment_container布局中
                    transaction.add(R.id.mFlHomeContent,f1);
                } else {
                    transaction.show(f1);
                }
                break;
            case 1:
                if(f2==null){
                    f2 = new FirstFragment("第二个Fragment");
                    transaction.add(R.id.mFlHomeContent,f2);
                }else{
                    transaction.show(f2);
                }
                break;

            case 2:
                if(f3==null){
                    f3 = new FirstFragment("第三个Fragment");
                    transaction.add(R.id.mFlHomeContent,f3);
                }else{
                    transaction.show(f3);
                }
                break;

            case 3:
                if(f4==null){
                    f4 = new FirstFragment("第四个Fragment");
                    transaction.add(R.id.mFlHomeContent,f4);
                }else{
                    transaction.show(f4);
                }
                break;
        }
        transaction.commit();
    }

    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction) {
        if(f1 != null) {
            transaction.hide(f1);
        }
        if(f2!=null){
            transaction.hide(f2);
        }
        if(f3!=null){
            transaction.hide(f3);
        }
        if(f4!=null){
            transaction.hide(f4);
        }
    }
}
