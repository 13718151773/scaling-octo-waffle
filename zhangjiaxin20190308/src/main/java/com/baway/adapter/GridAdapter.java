package com.baway.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baway.zhangjiaxin20190308.R;

import java.util.List;

/**
 * @Author：${张嘉鑫}
 * @Date：2019/3/8 9:20
 *///频道管理适配
public class GridAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public GridAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
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
        holder holder;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.item,null);
            holder=new holder();
            holder.textView=convertView.findViewById(R.id.text_grid);
            convertView.setTag(holder);
        }else{
            holder= (GridAdapter.holder) convertView.getTag();
        }
        holder.textView.setText(list.get(position));
        return convertView;
    }

    class holder{
        TextView textView;
    }
}
