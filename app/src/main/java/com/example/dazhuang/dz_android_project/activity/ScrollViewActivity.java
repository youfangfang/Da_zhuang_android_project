package com.example.dazhuang.dz_android_project.activity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dazhuang.dz_android_project.R;
import com.example.dazhuang.dz_android_project.adapter.DetailsAdapter;
import com.example.dazhuang.dz_android_project.adapter.NewDetailsAdapter;
import com.example.dazhuang.dz_android_project.base.BaseActivity;
import com.example.dazhuang.dz_android_project.bean.DetailsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dazhuang.dz_android_project.adapter.DetailsAdapter.GOODS_FIRST;

public class ScrollViewActivity extends BaseActivity {
    private String TAG = "ScrollViewActivity";
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.tv_goods)
    TextView tv_goods;
    @BindView(R.id.tv_details)
    TextView tv_details;
    @BindView(R.id.tv_all)
    TextView tv_all;
    private float mRecyclerFactor;
    private int totaldy;
    private int item1 = 0;
    private int item2 = 0;
    private Resources res;
    private List<DetailsBean> mList = new ArrayList<>();


    @Override
    protected int getLayout() {
        return R.layout.activity_scrollview;
    }

    @Override
    protected void initEventAndData() {
        initTitle();
        initListener();
        recycler_view.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(linearLayoutManager);
        recycler_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        for (int i = 0; i < 2; i++) {
            DetailsBean bean = new DetailsBean();
            bean.setType(i + 1);
            mList.add(bean);
        }

        DetailsAdapter myAdapter = new DetailsAdapter(this, mList);
        recycler_view.setAdapter(myAdapter);
        myAdapter.setListener(new DetailsAdapter.OnItemHeightListener() {
            @Override
            public void setOnItemHeightListener(int height, int type) {
                if (height != 0) {
                    if (type == GOODS_FIRST) {
                        item1 = (int) (height + mRecyclerFactor);
                    } else {
                        item2 = item1 + height;
                    }
                }

            }
        });
    }

    private void initListener() {

        tv_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item1 != 0) {
                    recycler_view.scrollBy(0,  -totaldy);
                }
            }
        });
        tv_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 判断滑动距离是否超过商品
                if (totaldy > item1)
                    recycler_view.scrollBy(0,  -(totaldy - item1) + 20);
                else
                    recycler_view.scrollBy(0,  (item1 - totaldy) + 20);


            }
        });


        recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //滑动的距离
                totaldy += dy;
                if (item2 != 0 && item1 != 0) {
                    if (totaldy < item1) {
                        tv_goods.setTextColor(res.getColor(R.color.orange));
                        tv_details.setTextColor(res.getColor(R.color.black));
                    } else if (totaldy > item1 && totaldy < item2) {
                        tv_details.setTextColor(res.getColor(R.color.orange));
                        tv_goods.setTextColor(res.getColor(R.color.black));
                    }
                }
                Log.e(TAG, "dy=  " + dy + "item2=  " + item2 + "item1=  " + item1 + "totaldy=  " + totaldy);
            }
        });
    }

    private void initTitle() {
        res = getResources();
        mRecyclerFactor = getStatusBarHeight(this);
    }

    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }
}
