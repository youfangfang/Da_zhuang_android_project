package com.example.dazhuang.dz_android_project.activity;

import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dazhuang.dz_android_project.R;
import com.example.dazhuang.dz_android_project.adapter.DownListAdapter;
import com.example.dazhuang.dz_android_project.base.BaseActivity;
import com.example.dazhuang.dz_android_project.utils.CommonUtil;
import com.example.dazhuang.dz_android_project.utils.HiddenAnimUtils;
import com.example.dazhuang.dz_android_project.utils.StatusBarUtil;
import com.example.dazhuang.dz_android_project.view.DragLayout1;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownListActivity extends BaseActivity {
    @BindView(R.id.input)
    EditText input;
    @BindView(R.id.tv_daohang)
    TextView tv_daohang;
    @BindView(R.id.ll_layout)
    LinearLayout ll_layout;

    @BindView(R.id.draglayout)
    DragLayout1 draglayout;
    @BindView(R.id.ib_bg)
    ImageButton ib_bg;
    /**
     * 屏幕的宽高
     */
    protected int mScreenWidth;
    protected int mScreenHeight;
    private ImageButton imageButton;
    private List<String> list = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_down_list;
    }

    @Override
    protected void initEventAndData() {
        StatusBarUtil.transparencyBar(this);
        initlist();
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (input.getText().toString().length() > 0) {
                    showListPopWindow();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    /**
     * 获取当前屏幕宽高
     */
    public void getScreenWidthAndHeight() {
        //获取当前屏幕宽高
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        mScreenWidth = metric.widthPixels;
        mScreenHeight = metric.heightPixels;
    }

    private void initlist() {
        list.add("海尔");
        list.add("格力");
        list.add("美的");
        list.add("讯飞");
        list.add("大疆");
        list.add("奎格");
    }

    private PopupWindow popupWindow;

    //显示提示
    public void showListPopWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_window, null);
        //初始化RecyclerView
        RecyclerView recyslerview = (RecyclerView) view.findViewById(R.id.recycler_view);
        TextView tv_close = (TextView) view.findViewById(R.id.tv_close);
        //创建LinearLayoutManager 对象 这里使用 LinearLayoutManager 是线性布局的意思
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyslerview.setLayoutManager(layoutmanager);
        //设置Adapter
        DownListAdapter adapter = new DownListAdapter(this, list);
        adapter.setOnItemClickListener(new DownListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                input.setText(list.get(position));
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }
        });
        recyslerview.setAdapter(adapter);

        //解决android7.0以上手机的适配问题
        popupWindow = new PopupWindow(view, input.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT) {
            @Override
            public void showAsDropDown(View anchor) {
                if (Build.VERSION.SDK_INT >= 24) {
                    Rect visibleFrame = new Rect();
                    anchor.getGlobalVisibleRect(visibleFrame);
                    int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
                    setHeight(height);
                }
                super.showAsDropDown(anchor);
            }

            @Override
            public void showAsDropDown(View anchor, int xoff, int yoff) {
                if (Build.VERSION.SDK_INT >= 24) {
                    Rect rect = new Rect();
                    anchor.getGlobalVisibleRect(rect);
                    int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
                    setHeight(h);
                }
                super.showAsDropDown(anchor, xoff, yoff);
            }
        };
        ColorDrawable dw = new ColorDrawable(0x10000000);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(input, 0, 0);
    }

    @OnClick(R.id.tv_shoye)
    public void tv_shoye() {
        Toast.makeText(DownListActivity.this, "首页", Toast.LENGTH_SHORT).show();
    }

    private boolean isOpen = false;

    @OnClick(R.id.tv_daohang)
    public void tv_daohang() {
        if (isOpen) {
            isOpen = false;
            ib_bg.setVisibility(View.GONE);
        } else {
            isOpen = true;
            ib_bg.setVisibility(View.VISIBLE);
        }
        HiddenAnimUtils.newInstance(DownListActivity.this, ll_layout, tv_daohang, 180).toggle();
    }
}

