package com.example.dazhuang.dz_android_project.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.dazhuang.dz_android_project.MyAdapter;
import com.example.dazhuang.dz_android_project.R;
import com.example.dazhuang.dz_android_project.base.BaseActivity;
import com.example.dazhuang.dz_android_project.head.MyRefreshHeader;
import com.example.dazhuang.dz_android_project.utils.AntiHijackingUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends BaseActivity {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private List<String> mList = new ArrayList<>();


    @Override
    protected int getLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initEventAndData() {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        for (int i = 0; i < 10; i++) {
            mList.add(i + "测试数据");
        }
        MyAdapter myAdapter = new MyAdapter(getContext(), mList);
        mRecyclerView.setAdapter(myAdapter);
        refreshLayout.setRefreshHeader(new MyRefreshHeader(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(3000); // 模拟请求数据, 3秒后结束
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
        // 白名单
        boolean safe = AntiHijackingUtil.checkActivity(getApplicationContext());
        // 系统桌面
        boolean isHome = AntiHijackingUtil.isHome(getApplicationContext());
        // 锁屏操作
        boolean isReflectScreen = AntiHijackingUtil.isReflectScreen(getApplicationContext());
        // 判断程序是否当前显示
        if (!safe && !isHome && !isReflectScreen) {
//                    Looper.prepare();
            Toast.makeText(getApplicationContext(), "TestActivity已切换至后台运行,请确认登陆环境是否安全！",
                    Toast.LENGTH_LONG).show();
//                    Looper.loop();
        }
//            }
//        }).start();

    }

}
