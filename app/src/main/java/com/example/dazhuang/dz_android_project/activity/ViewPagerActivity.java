package com.example.dazhuang.dz_android_project.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dazhuang.dz_android_project.R;
import com.example.dazhuang.dz_android_project.adapter.MyAdapter;
import com.example.dazhuang.dz_android_project.base.BaseActivity;
import com.example.dazhuang.dz_android_project.utils.HorizontalPageLayoutManager;
import com.example.dazhuang.dz_android_project.utils.PagingScrollHelper;


import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerActivity extends BaseActivity implements PagingScrollHelper.onPageChangeListener {
    @BindView(R.id.recyclerview_vertical)
    RecyclerView recyclerView;
    @BindView(R.id.iv_one)
    ImageView iv_one;
    @BindView(R.id.iv_two)
    ImageView iv_two;
    @BindView(R.id.iv_three)
    ImageView iv_three;
    @BindView(R.id.iv_four)
    ImageView iv_four;
    private MyAdapter myAdapter;
    private PagingScrollHelper scrollHelper = new PagingScrollHelper();


    @Override
    protected int getLayout() {
        return R.layout.activity_viewpager;
    }

    @Override
    protected void initEventAndData() {
        HorizontalPageLayoutManager horizontalPageLayoutManager = new HorizontalPageLayoutManager(1, 3);
        recyclerView.setLayoutManager(horizontalPageLayoutManager);
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        scrollHelper.setUpRecycleView(recyclerView);
        scrollHelper.setOnPageChangeListener(this);
        scrollHelper.updateLayoutManger();
        scrollHelper.scrollToPosition(0);
    }


    @Override
    public void onPageChange(int index) {
        switch ((index + 1) % 4) {
            case 1:
                iv_one.setImageResource(R.drawable.shape_red_round);
                iv_two.setImageResource(R.drawable.shape_gray_round);
                iv_three.setImageResource(R.drawable.shape_gray_round);
                iv_four.setImageResource(R.drawable.shape_gray_round);
                break;
            case 2:
                iv_one.setImageResource(R.drawable.shape_gray_round);
                iv_two.setImageResource(R.drawable.shape_red_round);
                iv_three.setImageResource(R.drawable.shape_gray_round);
                iv_four.setImageResource(R.drawable.shape_gray_round);
                break;
            case 3:
                iv_one.setImageResource(R.drawable.shape_gray_round);
                iv_two.setImageResource(R.drawable.shape_gray_round);
                iv_three.setImageResource(R.drawable.shape_red_round);
                iv_four.setImageResource(R.drawable.shape_gray_round);
                break;
            case 0:
                iv_one.setImageResource(R.drawable.shape_gray_round);
                iv_two.setImageResource(R.drawable.shape_gray_round);
                iv_three.setImageResource(R.drawable.shape_gray_round);
                iv_four.setImageResource(R.drawable.shape_red_round);
                break;
        }
        Toast.makeText(this, "第" + (index + 1) + "页", Toast.LENGTH_SHORT).show();
    }
}
