package com.example.dazhuang.dz_android_project.activity;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dazhuang.dz_android_project.R;
import com.example.dazhuang.dz_android_project.adapter.GuideAdapter;
import com.example.dazhuang.dz_android_project.adapter.LeftGuideAdapter;
import com.example.dazhuang.dz_android_project.adapter.RightGudeAdapter;
import com.example.dazhuang.dz_android_project.base.BaseActivity;
import com.example.dazhuang.dz_android_project.view.guideView.Component;
import com.example.dazhuang.dz_android_project.view.guideView.Guide;
import com.example.dazhuang.dz_android_project.view.guideView.GuideBuilder;
import com.example.dazhuang.dz_android_project.view.guideView.SimpleComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author fangyou
 * @time 2019/6/14
 */
public class MoreGuideActivity extends BaseActivity {
    @BindView(R.id.rv_recycleeerViewleft)
    RecyclerView rv_recycleeerViewleft;
    @BindView(R.id.rv_recycleeerViewright)
    RecyclerView rv_recycleeerViewright;
    @BindView(R.id.ll_layout)
    RelativeLayout ll_layout;
    private List<String> mListLeft = new ArrayList<>();
    private List<String> mListRight = new ArrayList<>();
    private RightGudeAdapter myAdapter;

    @Override
    protected int getLayout() {
        return R.layout.layout_more_guide;
    }

    @Override
    protected void initEventAndData() {

        for (int i = 0; i < 16; i++) {
            mListLeft.add("left" + i);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv_recycleeerViewleft.setLayoutManager(linearLayoutManager);
        LeftGuideAdapter leftGuideAdapter = new LeftGuideAdapter(getContext(), mListLeft);
        rv_recycleeerViewleft.setAdapter(leftGuideAdapter);
        leftGuideAdapter.setOnItemClickListener(mOnItemClickListener);

        for (int i = 0; i < 16; i++) {
            mListRight.add("0" + i);
        }
        rv_recycleeerViewright.setLayoutManager(new GridLayoutManager(getContext(), 3));
        myAdapter = new RightGudeAdapter(getContext(), mListRight);
        rv_recycleeerViewright.setAdapter(myAdapter);
//        myAdapter.setOnItemClickListener(mOnItemClickListener);
    }

    LeftGuideAdapter.OnItemClickListener mOnItemClickListener = new LeftGuideAdapter.OnItemClickListener() {
        @Override
        public void onGuideViewClick() {
            myAdapter.setOnShowDuide(true);
            myAdapter.notifyDataSetChanged();
//            showGuideView2();
        }
    };

    private void showGuideView2() {

    }



}
