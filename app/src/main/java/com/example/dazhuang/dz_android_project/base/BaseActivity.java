package com.example.dazhuang.dz_android_project.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;
import android.content.pm.ActivityInfo;

import com.example.dazhuang.dz_android_project.utils.AntiHijackingUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    protected Activity mContext;
    private boolean needAlarm = false;
    private Unbinder mUnBinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        initEventAndData();


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断程序进入后台是否是用户自身造成的（触摸返回键或HOME键），是则无需弹出警示。
        if ((keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) && event.getRepeatCount() == 0) {
            needAlarm = true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 白名单
        needAlarm = AntiHijackingUtil.checkActivity(getApplicationContext());
        // 系统桌面
        boolean isHome = AntiHijackingUtil.isHome(getApplicationContext());
        // 锁屏操作
        boolean isReflectScreen = AntiHijackingUtil.isReflectScreen(getApplicationContext());
        // 判断程序是否当前显示
        if (!needAlarm && !isHome && !isReflectScreen) {
            Toast.makeText(mContext, "已切换至后台运行,请确认登陆环境是否安全！", Toast.LENGTH_LONG).show();
        }

    }

    public Activity getContext() {
        return mContext;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();
}
