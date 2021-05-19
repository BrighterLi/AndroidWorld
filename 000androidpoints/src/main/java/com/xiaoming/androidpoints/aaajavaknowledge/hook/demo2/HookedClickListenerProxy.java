package com.xiaoming.androidpoints.aaajavaknowledge.hook.demo2;


import android.view.View;
import android.widget.Toast;

class HookedClickListenerProxy implements View.OnClickListener {

    private View.OnClickListener origin;

    public HookedClickListenerProxy(View.OnClickListener originOnClickListener) {
        this.origin = originOnClickListener;
        //        this=context;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(),
                " 拦截后 after Hook Click Listener",
                Toast.LENGTH_SHORT).show();
        if (origin != null) {
            origin.onClick(v);
        }
    }

}
