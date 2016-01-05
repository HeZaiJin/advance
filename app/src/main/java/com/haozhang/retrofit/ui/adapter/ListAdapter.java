package com.haozhang.retrofit.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.haozhang.retrofit.R;
import com.haozhang.retrofit.reset.modle.CustomEvent;
import com.haozhang.retrofit.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/18.
 */
public class ListAdapter extends BaseAdapter {
    private List<CustomEvent> list= new ArrayList<CustomEvent>();

    public void ListAdapter() {
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView){
            convertView = View.inflate(parent.getContext(), R.layout.content_list_item,null);
        }
        TextView title = ViewHolder.get(convertView,R.id.item_title);
        TextView detail = ViewHolder.get(convertView,R.id.item_date);
        CustomEvent info = list.get(position);
        title.setText(info.title);
        detail.setText(info.des);
        return convertView;
    }

    public void refresh(List<CustomEvent> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

}

