package com.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 类描述：RecyclerView 适配器基类
 * 创建人：Simple
 * 创建时间：2017/5/10 11:03
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public abstract class AbstractRecyclerViewAdapter<T extends Object> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<T> items = new ArrayList<T>();
    protected Context context;

    protected OnItemViewClickListener onItemViewClickListener;

    public void setOnItemViewClickListener(OnItemViewClickListener onItemViewClickListener) {

        this.onItemViewClickListener = onItemViewClickListener;
    }

    public interface OnItemViewClickListener {

        void onViewClick(View view, int position);

    }

    protected AbstractRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public T getItem(int position) {
        return items.get(position);
    }

    public void setData(List<T> items) {

        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void insert(int position, T item) {

        items.add(position, item);
        notifyDataSetChanged();
    }

    public void insert(int position, List<T> items) {

        this.items.addAll(position, items);
        notifyDataSetChanged();
    }

    public void remove(int position) {

        T remove = items.remove(position);
        if (remove != null) {
            notifyDataSetChanged();
        }
    }

    public void remove(T item) {

        for (int i = 0; i < items.size(); i++) {
            T temp = items.get(i);
            if (temp.equals(item)) {
                remove(i);
                i--;
            }
        }
    }

    public void replace(int position, T item) {
        Collections.replaceAll(items, getItem(position), item);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<T> getItems() {
        return items;
    }

    /**
     * 获取相对实时位置;
     * 注：当RecyclerView列表可添加删除时，position为final，会导致view和data数据不对应
     * @param holder
     */
    public int getRelativePosition(RecyclerView.ViewHolder holder){
        return holder.getAdapterPosition() -1;
    }

    /**
     * 绑定ItemView事件
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder.itemView != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemViewClickListener != null) {
                        onItemViewClickListener.onViewClick(holder.itemView, position);
                    }
                }
            });
        }
    }
}