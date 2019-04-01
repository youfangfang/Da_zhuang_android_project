package com.example.dazhuang.dz_android_project.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dazhuang.dz_android_project.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DownListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mList;
    private Context mContext;

    public DownListAdapter(Context context, List<String> mList) {
        this.mContext = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.companyitem, viewGroup, false);
        TopViewHolder holder = new TopViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final TopViewHolder topViewHolder = (TopViewHolder) viewHolder;
        if (i == mList.size() - 1) {
            topViewHolder.tv_close.setVisibility(View.VISIBLE);
        } else {
            topViewHolder.tv_close.setVisibility(View.GONE);
        }
        String s = mList.get(i);
        topViewHolder.company_item.setText(s);
        topViewHolder.tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnCloseClickListener != null) {
                    mOnCloseClickListener.onCloseClick();
                }
            }
        });
        topViewHolder.company_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class TopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.company_item)
        TextView company_item;
        @BindView(R.id.tv_close)
        TextView tv_close;


        public TopViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnCloseClickListener mOnCloseClickListener;

    public void setOnItemClickListener(OnCloseClickListener listener) {
        this.mOnCloseClickListener = listener;
    }

    public interface OnCloseClickListener {
        void onCloseClick();
    }
}
