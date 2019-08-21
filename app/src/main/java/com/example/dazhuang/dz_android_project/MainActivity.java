package com.example.dazhuang.dz_android_project;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.dazhuang.dz_android_project.activity.CalendarActivity;
import com.example.dazhuang.dz_android_project.activity.DownListActivity;
import com.example.dazhuang.dz_android_project.activity.GuideActivity;
import com.example.dazhuang.dz_android_project.activity.KeyboardActivity;
import com.example.dazhuang.dz_android_project.activity.LocationActivity;
import com.example.dazhuang.dz_android_project.activity.MoreGuideActivity;
import com.example.dazhuang.dz_android_project.activity.OtherKeyboardActivity;
import com.example.dazhuang.dz_android_project.activity.RefreshActivity;
import com.example.dazhuang.dz_android_project.activity.ScrollViewActivity;
import com.example.dazhuang.dz_android_project.activity.TablayoutActivity;
import com.example.dazhuang.dz_android_project.activity.TestActivity;
import com.example.dazhuang.dz_android_project.activity.TestImageActivity;
import com.example.dazhuang.dz_android_project.activity.ViewPagerActivity;
import com.example.dazhuang.dz_android_project.base.BaseActivity;
import com.example.dazhuang.dz_android_project.head.MyRefreshHeader;
import com.example.dazhuang.dz_android_project.utils.AntiHijackingUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {

    }

    @OnClick(R.id.btn_refresh)
    public void btn_refresh() {
        Intent intent = new Intent(getContext(), TestActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_search)
    public void btn_search() {
        Intent intent = new Intent(getContext(), KeyboardActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_other_search)
    public void btn_other_search() {
        Intent intent = new Intent(getContext(), OtherKeyboardActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_down_list)
    public void btn_down_list() {
        Intent intent = new Intent(getContext(), DownListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_viewpage)
    public void btn_viewpage() {
        Intent intent = new Intent(getContext(), ViewPagerActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_tablayout)
    public void btn_tablayout() {
        Intent intent = new Intent(getContext(), TablayoutActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_guide)
    public void btn_guide() {
        Intent intent = new Intent(getContext(), GuideActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_scrollView)
    public void btn_scrollView() {
        Intent intent = new Intent(getContext(), ScrollViewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_image)
    public void btn_image() {
        Intent intent = new Intent(getContext(), TestImageActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_more_guide)
    public void btn_more_guide() {
        Intent intent = new Intent(getContext(), MoreGuideActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_location)
    public void btn_location() {
        Intent intent = new Intent(getContext(), LocationActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.btn_time)
    public void btn_time() {
        Intent intent = new Intent(getContext(), CalendarActivity.class);
        startActivity(intent);
    }
}
