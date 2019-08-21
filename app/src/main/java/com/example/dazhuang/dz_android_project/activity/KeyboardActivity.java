package com.example.dazhuang.dz_android_project.activity;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.dazhuang.dz_android_project.R;
import com.example.dazhuang.dz_android_project.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KeyboardActivity extends BaseActivity {
    @BindView(R.id.edit_view)
    EditText edit_view;
    @BindView(R.id.rl_root)
    RelativeLayout rl_root;
    private boolean mIsSoftKeyBoardShowing = false;
    private View view;

    @Override
    protected int getLayout() {
        return R.layout.activity_keyboard;
    }

    @Override
    protected void initEventAndData() {
        //监听视图树的布局改变(弹出/隐藏软键盘会触发)
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new KeyboardOnGlobalChangeListener());
    }


    private class KeyboardOnGlobalChangeListener implements ViewTreeObserver.OnGlobalLayoutListener {

        private int getScreenHeight() {
            return ((WindowManager) getSystemService(Context.WINDOW_SERVICE))
                    .getDefaultDisplay().getHeight();
        }

        private int getScreenWidth() {
            return ((WindowManager) getSystemService(Context.WINDOW_SERVICE))
                    .getDefaultDisplay().getWidth();
        }

        @Override
        public void onGlobalLayout() {
            Rect rect = new Rect();
            // 获取当前页面窗口的显示范围
            getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            int screenHeight = getScreenHeight();
            int keyboardHeight = screenHeight - rect.bottom; // 输入法的高度
            if (Math.abs(keyboardHeight) > screenHeight / 5) {
                mIsSoftKeyBoardShowing = true; // 超过屏幕五分之一则表示弹出了输入法
                showKeyboardTopPopupWindow(mIsSoftKeyBoardShowing, keyboardHeight);
            } else {
                mIsSoftKeyBoardShowing = false;
                showKeyboardTopPopupWindow(mIsSoftKeyBoardShowing, keyboardHeight);
            }
        }
    }

    private void showKeyboardTopPopupWindow(boolean mIsSoftKeyBoardShowing, int y) {
        if (mIsSoftKeyBoardShowing) {
            if (view == null) {//第一次显示的时候创建  只创建一次
                view = View.inflate(this, R.layout.item, null);
                RelativeLayout.LayoutParams loginlayoutParams = new RelativeLayout.LayoutParams(-1, -2);
                loginlayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                loginlayoutParams.topMargin = 1000;
                View viewById = view.findViewById(R.id.btn_speak);
                viewById.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(KeyboardActivity.this);
                        builder.setMessage("我美不美");
                        builder.create().show();
                    }
                });
                rl_root.addView(view, loginlayoutParams);
            }
            view.setVisibility(View.VISIBLE);
        } else {
            if (view != null) {
                view.setVisibility(View.GONE);
            }
        }
    }


}
