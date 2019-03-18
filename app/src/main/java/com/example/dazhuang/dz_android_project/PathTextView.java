package com.example.dazhuang.dz_android_project;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.BounceInterpolator;



public class PathTextView extends android.support.v7.widget.AppCompatTextView {
    private int mTop;
    public PathTextView(Context context) {
        super(context);
        init();

    }

    public PathTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private ValueAnimator animator;

    public void init() {
        animator = ValueAnimator.ofInt(0, 80, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int dx = (int) animation.getAnimatedValue();
                setTop(mTop - dx);
            }
        });
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(0);
        animator.setInterpolator(new BounceInterpolator());
        animator.setDuration(2000);
        animator.start();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mTop = top;
    }

}
