package com.example.dazhuang.dz_android_project.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dazhuang.dz_android_project.R;
import com.example.dazhuang.dz_android_project.adapter.GuideAdapter;
import com.example.dazhuang.dz_android_project.adapter.MyAdapter;
import com.example.dazhuang.dz_android_project.base.BaseActivity;
import com.example.dazhuang.dz_android_project.view.guideView.Guide;
import com.example.dazhuang.dz_android_project.view.guideView.GuideBuilder;
import com.example.dazhuang.dz_android_project.view.guideView.MutiComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends BaseActivity {
    @BindView(R.id.my_recyclerview)
    RecyclerView my_recyclerview;
    private List<String> mList = new ArrayList<>();


    @Override
    protected int getLayout() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initEventAndData() {
        for (int i = 0; i < 16; i++) {
            mList.add("0" + i);
        }
        my_recyclerview.setLayoutManager(new GridLayoutManager(GuideActivity.this, 4));
        GuideAdapter myAdapter = new GuideAdapter(GuideActivity.this,mList);
        my_recyclerview.setAdapter(myAdapter);
    }


}
