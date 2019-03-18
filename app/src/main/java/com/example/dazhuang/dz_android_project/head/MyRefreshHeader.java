package com.example.dazhuang.dz_android_project.head;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dazhuang.dz_android_project.PathTextView;
import com.example.dazhuang.dz_android_project.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

public class MyRefreshHeader extends LinearLayout implements RefreshHeader {
    private ImageView imageView;
    private AnimationDrawable mAnimationDrawable;
    private PathTextView mTextView;
    Context mContext;

    public MyRefreshHeader(Context context) {
        super(context);
        this.mContext = context;
        initView(context);
    }


    private void initView(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.refresh_header_amin, this);
        mTextView = (PathTextView) view.findViewById(R.id.textview);
        imageView = (ImageView) view.findViewById(R.id.refresh_header_imge);
        mAnimationDrawable = (AnimationDrawable) ContextCompat.getDrawable(mContext, R.drawable.animation_head);
        imageView.setImageDrawable(mAnimationDrawable);

    }


    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int extendHeight) {

    }

    @Override
    public void onPulling(float percent, int offset, int height, int extendHeight) {

    }


    @Override
    public void onReleasing(float percent, int offset, int height, int extendHeight) {

    }

    @Override
    public void onReleased(RefreshLayout refreshLayout, int height, int extendHeight) {

    }


    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int extendHeight) {
        start();//开始动画
        mTextView.init();
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        stop();//停止动画
        return 50;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState) {




            case None:
            case PullDownToRefresh:
                mTextView.setText("下拉刷新");
//                mHeaderText.setText("下拉开始刷新");
//                mArrowView.setVisibility(VISIBLE);//显示下拉箭头
//                mProgressView.setVisibility(GONE);//隐藏动画
//                mArrowView.animate().rotation(0);//还原箭头方向
                break;
            case Refreshing:
                mTextView.setText("正在刷新...");
//                mHeaderText.setText("正在刷新");
//                start();
//                mProgressView.setVisibility(VISIBLE);//显示加载动画
//                mArrowView.setVisibility(GONE);//隐藏箭头
                break;
            case ReleaseToRefresh:
//                mTextView.setText("松开立即刷新");
//                mHeaderText.setText("释放立即刷新");
//                mArrowView.animate().rotation(180);//显示箭头改为朝上
                break;
            case RefreshFinish:
                mTextView.setText("刷新完成");
//                mHeaderText.setText("释放立即刷新");
//                mArrowView.animate().rotation(180);//显示箭头改为朝上
                break;
        }
    }


    /**
     * 开始播放
     */
    protected void start() {
        if (mAnimationDrawable != null && !mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }

    /**
     * 停止播放
     */
    protected void stop() {
        if (mAnimationDrawable != null && mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
    }
}
