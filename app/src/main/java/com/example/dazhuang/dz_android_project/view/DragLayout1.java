package com.example.dazhuang.dz_android_project.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dazhuang.dz_android_project.R;
import com.example.dazhuang.dz_android_project.activity.DownListActivity;
import com.example.dazhuang.dz_android_project.utils.HiddenAnimUtils;
import com.scwang.smartrefresh.layout.util.DensityUtil;

/**
 * Created by Administrator on 2017/12/26 0026.
 */

public class DragLayout1 extends FrameLayout {
    private String TAG = "DragLayout1";
    private ViewDragHelper mViewDragHelper;
    private View mDragView;
    private Context mContext;
    private OnClickListener mOnClickListener;
    private int newTop = 1350;

    public DragLayout1(Context context) {
        this(context, null);
    }

    public DragLayout1(final Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {

            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == mDragView;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
//                if(left <0){
//                    //限制左边界
//                    left = 0;
//                }else if (left > (getMeasuredWidth() - child.getMeasuredWidth())){
//                    //限制右边界
                left = getMeasuredWidth() - child.getMeasuredWidth();
//                }
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                if (top < 0) {
                    //限制上边界
                    top = 0;
                } else if (top > (getMeasuredHeight() - child.getMeasuredHeight())) {
                    //限制下边界
                    top = getMeasuredHeight() - child.getMeasuredHeight();
                }
                Log.e(TAG, "clampViewPositionVertical: top:" + top + "getMeasuredHeight():" + getMeasuredHeight() + "child.getMeasuredHeight():" + child.getMeasuredHeight());
                newTop = top;
                return top;
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                return getMeasuredWidth() - child.getMeasuredWidth();
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                return getMeasuredHeight() - child.getMeasuredHeight();
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
//                if (releasedChild == mDragView){
//                    mViewDragHelper.smoothSlideViewTo(releasedChild,getMeasuredWidth() - releasedChild.getMeasuredWidth(),releasedChild.getTop());
//                    ViewCompat.postInvalidateOnAnimation(DragLayout1.this);
//                }
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG, "onLayout: left:" + left + "top:" + top + "right:" + right + "bottom:" + bottom);
//        mDragView.layout(getWidth() - mDragView.getWidth(), getHeight() - mDragView.getHeight(), getWidth(), getHeight());
        mDragView.layout(getWidth() - mDragView.getWidth(), newTop, getWidth(), getHeight());
        Log.e(TAG, "mDragView.layout: getWidth() - mDragView.getWidth():" + (getWidth() - mDragView.getWidth()) + "getHeight() - mDragView.getHeight():" + (getHeight() - mDragView.getHeight()) + "getWidth:" + getWidth() + "getHeight:" + getHeight());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(DragLayout1.this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView = LayoutInflater.from(mContext).inflate(R.layout.kefu, null);
//        final TextView daohan = (TextView) mDragView.findViewById(R.id.tv_daohang);
//        final LinearLayout ll_layout = (LinearLayout) mDragView.findViewById(R.id.ll_layout);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, DensityUtil.dp2px(52));
        this.addView(mDragView, params);
//        daohan.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mOnClickListener.onClick(v);
//            }
//        });
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }
}
