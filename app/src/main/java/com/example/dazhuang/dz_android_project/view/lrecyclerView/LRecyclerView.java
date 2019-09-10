package com.example.dazhuang.dz_android_project.view.lRecyclerView;

import android.content.Context;
import android.util.AttributeSet;

/**
 * @author fangyou
 * @time 2019/9/10
 */
public class LRecyclerView extends ARecyclerView {
    public LRecyclerView(Context context) {
        this(context, null);
    }

    public LRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void init(Context context) {
        showAsList();
    }

    @Override
    public void installHeader() {

    }

    @Override
    public void installFooter() {

    }
}
