package com.baway.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.been.MyBeens;
import com.baway.zhangjiaxin20190308.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @Author：${张嘉鑫}
 * @Date：2019/3/8 9:52
 */
public class MyAdapter extends BaseAdapter {
    private List<MyBeens.DataBeanX.DataBean> list;
    private Context context;

    public MyAdapter(List<MyBeens.DataBeanX.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return position%3;
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
        switch (getItemViewType(position)){
            case 0:
                Holder1 holder1;
                if (convertView==null){
                    convertView=View.inflate(context, R.layout.item1,null);
                    holder1=new Holder1();
                    holder1.textView1=convertView.findViewById(R.id.text_100);
                    holder1.imageView1=convertView.findViewById(R.id.img100);
                    convertView.setTag(holder1);
                }else{
                    holder1= (Holder1) convertView.getTag();
                }
                holder1.textView1.setText(list.get(position).getTitle());
               // Glide.with(context).load("http://img.365jia.cn/uploads/"+list.get(position).getPics().get(0)).into(holder1.imageView1);
                break;
            case 1:
                Holder2 holder2;
                if (convertView==null){
                    convertView=View.inflate(context, R.layout.item2,null);
                    holder2=new Holder2();
                    holder2.textView2=convertView.findViewById(R.id.text_200);
                    holder2.imageView2=convertView.findViewById(R.id.img200);
                    convertView.setTag(holder2);
                }else{
                    holder2= (Holder2) convertView.getTag();
                }
                holder2.textView2.setText(list.get(position).getTitle());
                // Glide.with(context).load("http://img.365jia.cn/uploads/"+list.get(position).getPics().get(0)).into(holder2.imageView2);
                break;
            case 2:
                Holder3 holder3;
                if (convertView==null){
                    convertView=View.inflate(context, R.layout.item3,null);
                    holder3=new Holder3();
                    holder3.textView3=convertView.findViewById(R.id.text_300);
                    holder3.imageView3=convertView.findViewById(R.id.img300);
                    convertView.setTag(holder3);
                }else{
                    holder3= (Holder3) convertView.getTag();
                }
                holder3.textView3.setText(list.get(position).getTitle());
                // Glide.with(context).load("http://img.365jia.cn/uploads/"+list.get(position).getPics().get(0)).into(holder2.imageView2);
                break;
                //图片实例(http://img.365jia.cn/uploads/)
        }
        return convertView;
    }

    class Holder1{
        TextView textView1;
        ImageView imageView1;
    }

    class Holder2{
        TextView textView2;
        ImageView imageView2;
    }
    class Holder3{
        TextView textView3;
        ImageView imageView3;
    }
}
