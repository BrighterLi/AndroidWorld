package com.widget.aaaview.layout.dynamic_add_remove;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.ImageView;

import com.widget.R;

//使用RelativeLayout动态添加View总结: https://blog.csdn.net/qq_27426665/article/details/79072812
//在RelativeLayout 里面 动态创建4个View，是两行两列的效果
public class DynamicAddOrRemoveActivity2 extends AppCompatActivity {
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_add_or_remove2);
        layout = (RelativeLayout) findViewById(R.id.root_view);
        addChildView();
    }

    @SuppressLint("ResourceType")
    private void addChildView() {
        //添加第一个View
        ImageView item1 = new ImageView(this);
        //item1.setImageResource(R.drawable.red_leaf);//设置图片
        item1.setImageResource(R.drawable.dian1);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
               200, WindowManager.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);//与父容器的左侧对齐
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);//与父容器的上侧对齐
        lp.leftMargin = 30;
        lp.topMargin = 30;
        item1.setId(1);//设置这个View 的id
        item1.setLayoutParams(lp);//设置布局参数
        layout.addView(item1);//RelativeLayout添加子View

        //添加第二个View
        ImageView item2 = new ImageView(this);
        item2.setImageResource(R.drawable.dian2);//设置图片
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
                200, WindowManager.LayoutParams.WRAP_CONTENT);
        lp2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp2.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lp2.rightMargin=30;
        lp2.topMargin=30;
        item2.setId(2);
        item2.setLayoutParams(lp2);
        layout.addView(item2);

        //添加第三个View
        View childView1 = layout.getChildAt(0);//刚加进去RelativeLayout的第一个子View
        ImageView item3 = new ImageView(this);
        item3.setImageResource(R.drawable.dian3);//设置图片
        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(
                300, 300);
        lp3.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp3.addRule(RelativeLayout.BELOW,  childView1.getId());//设置item3在     //chlidView1的下面
        lp3.leftMargin=30;
        lp3.topMargin=30;
        item3.setId(3);
        item3.setLayoutParams(lp3);
        layout.addView(item3);

        //添加第四个View
        View childView2 = layout.getChildAt(1);//获取容器的第二个子view
        ImageView item4 = new ImageView(this);
        item4.setImageResource(R.drawable.dian4);//设置图片
        RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(
                300, 300);
        lp4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp4.addRule(RelativeLayout.BELOW,  childView2.getId());
        lp4.rightMargin=30;
        lp4.topMargin=30;
        item4.setId(4);
        item4.setLayoutParams(lp4);
        layout.addView(item4);


    }
}
