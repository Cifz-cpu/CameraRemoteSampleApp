package com.base;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 类描述：   ListView...适配器基类
 * 创建人：毛化磊
 * 创建时间：2016/10/10 11:03
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public abstract class AbstractBaseAdapter<T> extends BaseAdapter {

    protected List<T> items = new ArrayList<T>();
    protected Context context;

    public AbstractBaseAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<T> datas) {
        items.clear();
        addData(datas);
    }

    public void addData(List<T> datas) {
        items.addAll(datas);
        notifyDataSetChanged();
    }

    public void addData(T data) {
        items.add(data);
        notifyDataSetChanged();
    }

    public void insert(int position, T data) {
        items.add(position, data);
        notifyDataSetChanged();
    }

    public void insert(int position, List<T> datas) {
        items.addAll(position, datas);
        notifyDataSetChanged();

    }


    public void refreshAdapter(List<T> items) {

        this.items = items;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
