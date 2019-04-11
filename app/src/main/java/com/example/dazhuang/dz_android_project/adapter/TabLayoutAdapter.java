package com.example.dazhuang.dz_android_project.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fangyou
 */
public class TabLayoutAdapter extends FragmentPagerAdapter {

    private List<String> titleList = new ArrayList<>();

    private List<Fragment> fragmentList;


    public TabLayoutAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> list) {
        super(fm);
        titleList = list;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titleList.get(position);
    }
}