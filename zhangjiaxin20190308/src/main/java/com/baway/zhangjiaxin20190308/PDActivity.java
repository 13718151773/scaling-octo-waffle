package com.baway.zhangjiaxin20190308;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.adapter.GridAdapter;

import java.util.ArrayList;

public class PDActivity extends BaseActivity {
private ArrayList<String> top=new ArrayList<>();
private ArrayList<String> bon=new ArrayList<>();
private GridAdapter top_adapter,bon_adapter;
private ImageView img_no;
private TextView text_ok;
private GridView top_grid,bon_grid;


    @Override
    protected int initLayout() {
        return R.layout.activity_pd;
    }

    @Override
    protected void initView() {
        top_grid=fvbi(R.id.top_grid);
        bon_grid=fvbi(R.id.boton_grid);
        img_no=fvbi(R.id.img_no);
        text_ok=fvbi(R.id.text_ok);
    }

    @Override
    protected void initData() {
        ArrayList<String> title = getIntent().getStringArrayListExtra("title");
        top.addAll(title);
        for (int i = 0; i <10 ; i++) {
            bon.add("频道"+i);
        }
        top_adapter=new GridAdapter(top,PDActivity.this);
        bon_adapter=new GridAdapter(bon,PDActivity.this);
        top_grid.setAdapter(top_adapter);
        bon_grid.setAdapter(bon_adapter);
    }

    @Override
    protected void initListener() {
        img_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        text_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putStringArrayListExtra("top",top);
                setResult(200,intent);
                finish();
            }
        });

        top_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bon.add(top.get(position));

                top.remove(position);

                top_adapter.notifyDataSetChanged();
                bon_adapter.notifyDataSetChanged();
            }
        });

        bon_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                top.add(bon.get(position));

                bon.remove(position);

                bon_adapter.notifyDataSetChanged();
                top_adapter.notifyDataSetChanged();
            }
        });

    }
}
