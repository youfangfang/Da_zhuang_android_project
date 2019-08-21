package com.example.dazhuang.dz_android_project.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dazhuang.dz_android_project.R;
import com.example.dazhuang.dz_android_project.view.guideView.Component;
import com.example.dazhuang.dz_android_project.view.guideView.Guide;
import com.example.dazhuang.dz_android_project.view.guideView.GuideBuilder;
import com.example.dazhuang.dz_android_project.view.guideView.MutiComponent;
import com.example.dazhuang.dz_android_project.view.guideView.SimpleComponent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author fangyou
 * @time 2019/6/14
 */
public class RightGudeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<String> mList;
    private int showTimes = 0;

    public void setOnShowDuide(boolean onShowDuide) {
        this.onShowDuide = onShowDuide;
    }

    private boolean onShowDuide = false;

    public RightGudeAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_item, viewGroup, false);
        TopViewHolder holder = new TopViewHolder(itemView);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final TopViewHolder topViewHolder = (TopViewHolder) viewHolder;
        String s = mList.get(i);
        topViewHolder.tv_title.setText(s);
        if (onShowDuide) {
            if (i == 3 && showTimes == 0) {
                final View finalView = topViewHolder.rl_item;
                finalView.post(new Runnable() {
                    @Override
                    public void run() {
                        showGuideView(finalView);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void showGuideView(View targetView) {
        showTimes++;
        onShowDuide = false;
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(targetView)
                .setAlpha(150)
                .setHighTargetGraphStyle(Component.CIRCLE)
                .setHighTargetCorner(10)
                .setHighTargetPadding(10)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
            }
        });
        SimpleComponent simpleComponent = new SimpleComponent();
        simpleComponent.setHintContext("点击查看该分类下的商品～");
        simpleComponent.setAnchor(Component.ANCHOR_TOP);
        simpleComponent.setFitPosition(Component.FIT_CENTER);
        simpleComponent.setXOffset(0);
        simpleComponent.setYOffset(-10);
        builder.addComponent(simpleComponent);
        Guide guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(true);
        guide.show((Activity) mContext);
    }


    public static class TopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rl_item)
        RelativeLayout rl_item;
        @BindView(R.id.tv_title)
        TextView tv_title;


        public TopViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }
}
