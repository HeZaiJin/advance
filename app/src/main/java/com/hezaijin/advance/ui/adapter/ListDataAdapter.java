package com.hezaijin.advance.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hezaijin.advance.R;
import com.hezaijin.advance.rest.modle.CustomEvent;
import com.hezaijin.advance.ui.fragment.ListDataFragment;
import com.hezaijin.advance.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * ListEvent adapter
 */
public class ListDataAdapter extends BaseAdapter {
    ListDataFragment fragment;
    private List<CustomEvent> list = new ArrayList<CustomEvent>();

    public ListDataAdapter(ListDataFragment fragment) {
        this.fragment = fragment;
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
        if (null == convertView) {
            convertView = View.inflate(parent.getContext(), R.layout.item_listdata, null);
        }
        TextView title = ViewHolder.get(convertView, R.id.item_title);
        TextView detail = ViewHolder.get(convertView, R.id.item_date);
        ImageView img = ViewHolder.get(convertView, R.id.item_img);
        CustomEvent info = list.get(position);
        title.setText(info.title);
        detail.setText(info.des);
        Glide.with(fragment)
                .load(info.pic)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(img);
        return convertView;
    }

    public void refresh(List<CustomEvent> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

}

