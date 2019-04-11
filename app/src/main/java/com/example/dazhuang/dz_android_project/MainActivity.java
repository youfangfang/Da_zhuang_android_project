package com.example.dazhuang.dz_android_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dazhuang.dz_android_project.activity.DownListActivity;
import com.example.dazhuang.dz_android_project.activity.GuideActivity;
import com.example.dazhuang.dz_android_project.activity.KeyboardActivity;
import com.example.dazhuang.dz_android_project.activity.OtherKeyboardActivity;
import com.example.dazhuang.dz_android_project.activity.RefreshActivity;
import com.example.dazhuang.dz_android_project.activity.TablayoutActivity;
import com.example.dazhuang.dz_android_project.activity.TestActivity;
import com.example.dazhuang.dz_android_project.activity.ViewPagerActivity;
import com.example.dazhuang.dz_android_project.head.MyRefreshHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_refresh)
    public void btn_refresh() {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_search)
    public void btn_search() {
        Intent intent = new Intent(this, KeyboardActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_other_search)
    public void btn_other_search() {
        Intent intent = new Intent(this, OtherKeyboardActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_down_list)
    public void btn_down_list() {
        Intent intent = new Intent(this, DownListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_viewpage)
    public void btn_viewpage() {
        Intent intent = new Intent(this, ViewPagerActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_tablayout)
    public void btn_tablayout() {
        Intent intent = new Intent(this, TablayoutActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_guide)
    public void btn_guide() {
        Intent intent = new Intent(this, GuideActivity.class);
        startActivity(intent);
    }
}
