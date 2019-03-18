package com.example.dazhuang.dz_android_project.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dazhuang.dz_android_project.PathTextView;
import com.example.dazhuang.dz_android_project.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class RefreshActivity extends AppCompatActivity {
    private ImageView imageView;
    public PathTextView tv_text;
    private AnimationDrawable frameAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        ButterKnife.bind(this);
        imageView = findViewById(R.id.refresh_header_imge);
        tv_text = findViewById(R.id.tv_text);
        // 通过逐帧动画的资源文件获得AnimationDrawable示例
        frameAnim = (AnimationDrawable) getResources().getDrawable(R.drawable.animation_head);
        // 把AnimationDrawable设置为ImageView的背景
        imageView.setBackgroundDrawable(frameAnim);

        start();
    }

    /**
     * 开始播放
     */
    protected void start() {
        if (frameAnim != null && !frameAnim.isRunning()) {
            frameAnim.start();
        }
    }

    /**
     * 停止播放
     */
    protected void stop() {
        if (frameAnim != null && frameAnim.isRunning()) {
            frameAnim.stop();
        }
    }

    @OnClick(R.id.btn_refresh)
    public void btn_refresh() {
        start();
    }
}
