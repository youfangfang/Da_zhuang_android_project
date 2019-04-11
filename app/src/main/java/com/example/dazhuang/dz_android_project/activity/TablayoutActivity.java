package com.example.dazhuang.dz_android_project.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dazhuang.dz_android_project.R;
import com.example.dazhuang.dz_android_project.adapter.TabLayoutAdapter;
import com.example.dazhuang.dz_android_project.fragment.TabFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TablayoutActivity extends AppCompatActivity {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> titleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        ButterKnife.bind(this);
        titleList.add("商品");
        titleList.add("详情");
        for (int i = 0; i < titleList.size(); i++) {
            fragmentList.add(new TabFragment());
        }
        TabLayoutAdapter myAdater = new TabLayoutAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewpager.setAdapter(myAdater);
        viewpager.setCurrentItem(0);
        viewpager.setOffscreenPageLimit(2);
        tablayout.setupWithViewPager(viewpager);
        for(int i=0;i<titleList.size();i++){
            tablayout.getTabAt(i).setCustomView(makeTabView(i));
        }
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.imageview).setFocusable(true);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.imageview).setFocusable(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    /**
     * 引入布局设置图标和标题
     * @param position
     * @return
     */
    private View makeTabView(int position){
        View tabView = LayoutInflater.from(this).inflate(R.layout.tab_text_icon,null);
        TextView textView = tabView.findViewById(R.id.textview);
        textView.setText(titleList.get(position));
//        imageView.setImageResource(pics[position]);

        return tabView;
    }

}
