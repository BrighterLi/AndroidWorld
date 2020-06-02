package com.xiaoming.framemvp;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.xiaoming.framemvp.presenter.DemoContract;
import com.xiaoming.framemvp.presenter.DemoPresenter;

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
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(getString(R.string.app_name));*/

        DemoFragment fragment = (DemoFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(fragment == null) {
            fragment = DemoFragment.newInstance();
            transaction.add(R.id.container, fragment);
        }

        new DemoPresenter(fragment);
    }
}
