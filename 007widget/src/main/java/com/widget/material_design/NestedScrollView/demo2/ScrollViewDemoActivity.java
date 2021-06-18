package com.widget.material_design.NestedScrollView.demo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.widget.R;

public class ScrollViewDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_demo);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        recyclerView.setAdapter(new ScrollViewDemoActivity.MyAdapter());

        recyclerView.setNestedScrollingEnabled(false);//解决卡顿
    }

    class MyAdapter extends RecyclerView.Adapter<ScrollViewDemoActivity.MyViewHolder> {
        @Override
        public ScrollViewDemoActivity.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ScrollViewDemoActivity.MyViewHolder(LayoutInflater.from(ScrollViewDemoActivity.this).inflate(R.layout.item_restedscrollview_recyclerview, null));
        }

        @Override
        public void onBindViewHolder(ScrollViewDemoActivity.MyViewHolder holder, int position) {
            holder.text.setText("position=" + position);
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }
    }
}
