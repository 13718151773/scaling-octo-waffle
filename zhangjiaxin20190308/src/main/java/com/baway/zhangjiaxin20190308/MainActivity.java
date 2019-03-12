package com.baway.zhangjiaxin20190308;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.baway.fragment.Frag1;
import com.baway.fragment.Frag2;
import com.baway.fragment.Frag3;
import com.baway.fragment.Frag4;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private RadioGroup group;
    private Frag1 frag1;
    private Frag2 frag2;
    private Frag3 frag3;
    private Frag4 frag4;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        group=fvbi(R.id.group);
        frag1 = new Frag1();
        frag2 = new Frag2();
        frag3 = new Frag3();
        frag4 = new Frag4();
        manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.frag_layout1,frag1)
                .add(R.id.frag_layout1,frag2)
                .add(R.id.frag_layout1,frag3)
                .add(R.id.frag_layout1,frag4)
                .show(frag1)
                .hide(frag2)
                .hide(frag3)
                .hide(frag4)
                .commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                transaction = manager.beginTransaction();
                switch (checkedId){
                    case R.id.rbut1:
                        transaction.show(frag1)
                                .hide(frag2)
                                .hide(frag3)
                                .hide(frag4);
                        break;
                    case R.id.rbut2:
                        transaction.show(frag2)
                                .hide(frag1)
                                .hide(frag3)
                                .hide(frag4);
                        break;
                    case R.id.rbut3:
                        transaction.show(frag3)
                                .hide(frag2)
                                .hide(frag1)
                                .hide(frag4);
                        break;
                    case R.id.rbut4:
                        transaction.show(frag4)
                                .hide(frag2)
                                .hide(frag3)
                                .hide(frag1);
                        break;
                }
                transaction.commit();
            }
        });
    }
}
