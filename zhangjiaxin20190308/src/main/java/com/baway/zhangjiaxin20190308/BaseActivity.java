package com.baway.zhangjiaxin20190308;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @Author：${张嘉鑫}
 * @Date：2019/3/8 8:38
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        initView();
        initData();
        initListener();
    }
    protected abstract int initLayout();
    protected abstract void initView();
    protected abstract void initData();
    protected <T extends View>T fvbi(int resId){
        return (T) findViewById(resId);
    }
    protected abstract void initListener();
}
