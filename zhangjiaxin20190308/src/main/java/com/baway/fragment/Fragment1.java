package com.baway.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.widget.ListView;
import android.widget.Toast;

import com.baway.adapter.MyAdapter;
import com.baway.been.MyBeens;
import com.baway.dao.SQliteDao;
import com.baway.net.NetUtil;
import com.baway.zhangjiaxin20190308.R;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：${张嘉鑫}
 * @Date：2019/3/8 9:06
 */
public class Fragment1 extends BaseFragment {
    private PullToRefreshListView listView;
    private List<MyBeens.DataBeanX.DataBean> list=new ArrayList<>();
    private MyAdapter adapter;
    private String dataUrl="http://365jia.cn/news/api3/365jia/news/categories/hotnews?page=";
    private int page=1;
    private SQliteDao dao;

    @Override
    protected int initLayout() {
        return R.layout.fragment1;
    }

    @Override
    protected void initView() {
        listView=fvbi(R.id.pull_list1);
        //功能模式
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setScrollingWhileRefreshingEnabled(true);
    }

    @Override
    protected void initData() {
        dao=new SQliteDao(getActivity());
        adapter=new MyAdapter(list,getActivity());
        listView.setAdapter(adapter);
        //判断网络
        if (NetUtil.isConnection(getActivity())){
            //请求数据
            isgoing(page);
            listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                    isgoing(page);
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    page++;
                    isgoing(page);
                }
            });
        }else{
            Toast.makeText(getActivity(),"请检查网络",Toast.LENGTH_SHORT).show();
            //查询数据库
            Cursor query = dao.query("news", null, null, null, null, null, null);
            if (query.moveToFirst()){
                do {
                    String title = query.getString(query.getColumnIndexOrThrow("title"));
                    list.add(new MyBeens.DataBeanX.DataBean(title));
                }while (query.moveToNext());
                //刷新适配器
                adapter.notifyDataSetChanged();
            }
        }

    }

    private void isgoing(int page) {
        //合成接口
        String s = dataUrl + page;
        NetUtil.MyAsyncTask(s, new NetUtil.MyCallBack() {
            @Override
            public void getData(String dataurls) {
                ismaking(dataurls);
            }
        });
    }

    private void ismaking(String dataurls) {
        //gson解析
        Gson gson = new Gson();
        MyBeens fromJson = gson.fromJson(dataurls, MyBeens.class);
        List<MyBeens.DataBeanX.DataBean> data = fromJson.getData().getData();
        for (int i = 0; i <data.size() ; i++) {
            //加入数据库
            ContentValues contentValues = new ContentValues();
            contentValues.put("title",data.get(i).getTitle());
            dao.insert("news",null,contentValues);
        }
        list.addAll(data);
        adapter.notifyDataSetChanged();
        listView.onRefreshComplete();
    }

    @Override
    protected void initListener() {

    }
}
