package com.xiaoming.a008project.fenle.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.xiaoming.a008project.R;
import com.xiaoming.a008project.fenle.home.bottom_tab.TabController;
import com.xiaoming.a008project.fenle.home.header.CommHeader;

public class HomeActivity extends FragmentActivity {
    private LinearLayout mVHomeTabs;
    private CommHeader mCommHeader;
    private TabController mTabController;

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

        initBottomTab();
    }

    private void initBottomTab() {
        mTabController = new TabController(this, mVHomeTabs);
        mTabController.initBottomTab();
    }
}
