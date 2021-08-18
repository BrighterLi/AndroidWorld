package com.xiaoming.mvx.mvp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.xiaoming.framearouter.R;
import com.xiaoming.mvx.mvp.presenter.DemoContract;
import com.xiaoming.mvx.mvp.presenter.DemoPresenter;
import com.xiaoming.mvx.mvp.utils.ActivityUtil;
import com.xiaoming.mvx.mvp.view.DemoFragment;

//https://www.jianshu.com/p/f5e13706ae52
public class MvpActivity extends AppCompatActivity {
    DemoContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        init();
    }

    private void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(getString(R.string.app_name) + "1111");

        DemoFragment fragment = (DemoFragment) getSupportFragmentManager().findFragmentById(R.id.container);

        if (fragment == null) {
            fragment = DemoFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.container);
        }

        new DemoPresenter(fragment);
    }
}
