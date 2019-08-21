package com.example.dazhuang.dz_android_project.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.dazhuang.dz_android_project.R;
import com.example.dazhuang.dz_android_project.base.BaseActivity;
import com.example.dazhuang.dz_android_project.listener.SoftKeyBoardListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtherKeyboardActivity extends BaseActivity {
    @BindView(R.id.rl_speack)
    RelativeLayout rl_speack;
    @BindView(R.id.edit_view)
    EditText edit_view;

    @Override
    protected int getLayout() {
        return R.layout.activity_other_keyboard;
    }

    @Override
    protected void initEventAndData() {
        SoftKeyBoardListener.setListener(OtherKeyboardActivity.this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                rl_speack.setVisibility(View.VISIBLE);
            }

            @Override
            public void keyBoardHide(int height) {
                rl_speack.setVisibility(View.GONE);
            }
        });
    }
}
