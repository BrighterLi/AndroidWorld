package com.widget.textview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.CompoundButton;

import com.widget.R;

public class FoldAndExpansionActivity extends AppCompatActivity {
    TextView  headTv;
    CheckBox ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fold_and_expansion);

        ch = findViewById(R.id.check_zhankai);
        headTv = findViewById(R.id.text);
        headTv.post(new Runnable() { //用post方法是因为当view绘制完获取信息，不然可能第一次获取不到正确的行数。
            @Override
            public void run() {
                //dataBean.setMaxLines(2); //保存一个最大行数，在集合对应的对象中
                if (headTv.getLineCount() > 5) {   //如果大于5行
                    ch.setVisibility(View.VISIBLE);  //展开按钮显示
                } else {
                    ch.setVisibility(View.GONE);   //否则，展开按钮隐藏
                }
            }
        });


        ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //dataBean.setCheckZK(isChecked); //这里在你的对象集合中记录一个状态值，防止滑动checkBox选中状态错乱
                if (isChecked) {
                    ch.setText("收起");
                    //headTv.setMaxLines(dataBean.getMaxLines());
                    headTv.setMaxLines(2);
                    headTv.postInvalidate();
                } else {
                    headTv.setMaxLines(5);
                    headTv.postInvalidate(); //刷新控件，不加的话，如果下拉刷新列表notifyDataSetChanged()的时候TextView会自动重绘
                    ch.setText("全文");
                }
            }
        });
        //ch.setChecked(dataBean.isCheckZK()); //随时释放选中状态
    }
}
