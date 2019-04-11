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
import com.example.dazhuang.dz_android_project.view.guideView.Guide;
import com.example.dazhuang.dz_android_project.view.guideView.GuideBuilder;
import com.example.dazhuang.dz_android_project.view.guideView.MutiComponent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<String> mList;
    private int showTimes = 0;

    public GuideAdapter(Context mContext, List<String> mList) {
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
        if (i == 7 && showTimes == 0) {
            final View finalView = topViewHolder.rl_item;
            finalView.post(new Runnable() {
                @Override
                public void run() {
                    showGuideView(finalView);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void showGuideView(View targetView) {
        showTimes++;
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(targetView)
                .setAlpha(150)
                .setHighTargetCorner(20)
                .setHighTargetPadding(10)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
                Toast.makeText(mContext, "引导层被点击了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDismiss() {
                Toast.makeText(mContext, "引导层guanbi", Toast.LENGTH_SHORT).show();
            }
        });

        builder.addComponent(new MutiComponent());
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
