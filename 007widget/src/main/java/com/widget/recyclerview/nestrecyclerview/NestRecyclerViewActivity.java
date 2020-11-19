package com.widget.recyclerview.nestrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.widget.R;

import java.util.ArrayList;
import java.util.List;

//横向+垂直
//https://blog.csdn.net/chentyit/article/details/79307199?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
public class NestRecyclerViewActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest_recycler_view);

        initFruits();

        RecyclerView recyclerView = findViewById(R.id.out_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        OutRVAdapter outRVAdapter = new OutRVAdapter(this, fruitList);
        recyclerView.setAdapter(outRVAdapter);
    }

    //初始化fruitList内的数据
    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit("Apple", R.drawable.liner_rv_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit("Banana", R.drawable.liner_rv_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit("Orange", R.drawable.liner_rv_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon", R.drawable.liner_rv_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit("Pear", R.drawable.liner_rv_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit("Grape", R.drawable.liner_rv_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple", R.drawable.liner_rv_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry", R.drawable.liner_rv_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry", R.drawable.liner_rv_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit("Mango", R.drawable.liner_rv_pic);
            fruitList.add(mango);
        }
    }
}
