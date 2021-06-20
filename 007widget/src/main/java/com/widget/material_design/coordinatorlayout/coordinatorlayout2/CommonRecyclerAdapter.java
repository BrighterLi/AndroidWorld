package com.widget.material_design.coordinatorlayout.coordinatorlayout2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<CommonRecyclerHolder> {
    private Context context;
    private List<T> list;
    private LayoutInflater inflater;
    private int itemLayoutId;
    private boolean isScrolling;
    private CommonRecyclerAdapter.OnItemClickListener listener;
    private CommonRecyclerAdapter.OnItemLongClickListener longClickListener;
    private RecyclerView recyclerView;

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    public void insert(T item, int position) {
        this.list.add(position, item);
        this.notifyItemInserted(position);
    }

    public void delete(int position) {
        this.list.remove(position);
        this.notifyItemRemoved(position);
    }

    public CommonRecyclerAdapter(Context context, List<T> list, int itemLayoutId) {
        this.context = context;
        this.list = list;
        this.itemLayoutId = itemLayoutId;
        this.inflater = LayoutInflater.from(context);
    }

    public CommonRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = this.inflater.inflate(this.itemLayoutId, parent, false);
        return CommonRecyclerHolder.getRecyclerHolder(this.context, view);
    }

    public void onBindViewHolder(CommonRecyclerHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CommonRecyclerAdapter.this.listener != null && view != null && CommonRecyclerAdapter.this.recyclerView != null) {
                    int position = CommonRecyclerAdapter.this.recyclerView.getChildAdapterPosition(view);
                    CommonRecyclerAdapter.this.listener.onItemClick(CommonRecyclerAdapter.this.recyclerView, view, position);
                }

            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (CommonRecyclerAdapter.this.longClickListener != null && view != null && CommonRecyclerAdapter.this.recyclerView != null) {
                    int position = CommonRecyclerAdapter.this.recyclerView.getChildAdapterPosition(view);
                    CommonRecyclerAdapter.this.longClickListener.onItemLongClick(CommonRecyclerAdapter.this.recyclerView, view, position);
                    return true;
                } else {
                    return false;
                }
            }
        });
        this.convert(holder, this.list.get(position), position, this.isScrolling);
    }

    public int getItemCount() {
        return this.list == null ? 0 : this.list.size();
    }

    public void setOnItemClickListener(CommonRecyclerAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnItemLongClickListener(CommonRecyclerAdapter.OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public abstract void convert(CommonRecyclerHolder var1, T var2, int var3, boolean var4);

    public interface OnItemLongClickListener {
        boolean onItemLongClick(RecyclerView var1, View var2, int var3);
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView var1, View var2, int var3);
    }
}

