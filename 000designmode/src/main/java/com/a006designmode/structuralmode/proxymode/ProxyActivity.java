package com.a006designmode.structuralmode.proxymode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.a006designmode.R;
import com.a006designmode.structuralmode.proxymode.staticmode.Proxy;
import com.a006designmode.structuralmode.proxymode.staticmode.Sourceable;

public class ProxyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_proxy);

        staticProxyTest();
    }

    private void staticProxyTest() {
        Sourceable source = new Proxy();
        source.method();
    }
}