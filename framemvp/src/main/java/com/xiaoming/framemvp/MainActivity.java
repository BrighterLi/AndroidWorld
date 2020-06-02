package com.xiaoming.framemvp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaoming.framemvp.presenter.DemoContract;
import com.xiaoming.framemvp.presenter.DemoPresenter;
import com.xiaoming.framemvp.utils.ActivityUtil;
import com.xiaoming.framemvp.view.DemoFragment;

//mvp
//https://www.jianshu.com/p/f5e13706ae52
public class MainActivity extends AppCompatActivity {
    DemoContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(getString(R.string.app_name) + "1111");

        DemoFragment fragment = (DemoFragment) getSupportFragmentManager().findFragmentById(R.id.container);

        if(fragment == null) {
            fragment = DemoFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.container);
        }

        new DemoPresenter(fragment);
    }
}
