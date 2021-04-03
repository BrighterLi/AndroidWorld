package com.xiaoming.androidpoints.uiframe.waterfall;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.xiaoming.androidpoints.R;

public class WaterFallActivity extends AppCompatActivity {
    private RecyclerView mRvPu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_fall);

        mRvPu=findViewById(R.id.rv_pu);
        mRvPu.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)); //水平每行两列
        mRvPu.setAdapter(new WaterFallAdapter(WaterFallActivity.this, new WaterFallAdapter.OnItemClickListener() { //接口
            @Override
            public void onClick(int pos) {  //监听器
                Toast.makeText(WaterFallActivity.this,"Click"+pos,Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
