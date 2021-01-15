package com.xiaoming.a005algorithm.basealgorithm;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaoming.a005algorithm.R;
import com.xiaoming.a005algorithm.basealgorithm.search.BinarySearch;

public class BaseAlgorithmActivity extends Activity {
    private Button mBtBinarySearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_algorithm);

        initView();
    }

    private void initView() {
        //二分查找
        mBtBinarySearch = findViewById(R.id.bt_binary_search);
        mBtBinarySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = 5;
                int value = 4;
                int[] a = {1, 2, 4, 4, 5};
                BinarySearch.doBinarySearch11(size, value, a);
            }
        });
    }
}
