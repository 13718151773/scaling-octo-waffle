package com.baway.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.baway.adapter.PagerAdapters;
import com.baway.zhangjiaxin20190308.MainActivity;
import com.baway.zhangjiaxin20190308.PDActivity;
import com.baway.zhangjiaxin20190308.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：${张嘉鑫}
 * @Date：2019/3/8 8:44
 */
public class Frag1 extends BaseFragment {
    private List<Fragment> list=new ArrayList<>();
    private ArrayList<String> title=new ArrayList<>();
    private ViewPager pager;
    private TabLayout tabLayout;
    private PagerAdapters adapters;
    private TextView text_add;
    @Override
    protected int initLayout() {
        return R.layout.frag1;
    }

    @Override
    protected void initView() {
        pager=fvbi(R.id.pager);
        tabLayout=fvbi(R.id.teb_ta1);
        text_add=fvbi(R.id.text_add);
    }

    @Override
    protected void initData() {
        //获取fragment
        list.add(new Fragment1());
        list.add(new Fragment2());
        list.add(new Fragment3());
        //获取名称
        title.add("关注");
        title.add("头条");
        title.add("视频");
        //添加适配器
        adapters=new PagerAdapters(getChildFragmentManager(),list,title);
        pager.setAdapter(adapters);
        //加入pager
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    protected void initListener() {
        //频道点击事件
        text_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PDActivity.class);
                intent.putStringArrayListExtra("title",title);
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //返回值
        if (requestCode==100&&resultCode==200){
            ArrayList<String> top = data.getStringArrayListExtra("top");
            //清苦数据
            list.clear();
            title.clear();
            tabLayout.removeAllTabs();
            //重新加入数据
            title.addAll(top);
            for (int i = 0; i <title.size() ; i++) {
                TabLayout.Tab tab = tabLayout.newTab();
                tab.setText(title.get(i));
                tabLayout.addTab(tab);
                //判断
                if (i==1){
                    list.add(new Fragment1());
                }else if (i==2){
                    list.add(new Fragment2());
                }else if (i==3){
                    list.add(new Fragment3());
                }else{
                    list.add(new AddFragment());
                }
            }
            //刷新适配其
            adapters.notifyDataSetChanged();
            tabLayout.setupWithViewPager(pager);


        }
    }
}
