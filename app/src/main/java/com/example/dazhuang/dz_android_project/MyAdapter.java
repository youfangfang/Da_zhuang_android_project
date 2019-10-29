package com.example.dazhuang.dz_android_project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<String> mList;

    public MyAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.my_item, viewGroup, false);
        TopViewHolder holder = new TopViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final TopViewHolder topViewHolder = (TopViewHolder) viewHolder;
        String s = mList.get(i);
        topViewHolder.tv_text.setText(s);
        topViewHolder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mValueChangeListener != null) {
                   mValueChangeListener.onItemDeleteClick(topViewHolder.getAdapterPosition());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class TopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_text)
        TextView tv_text;
        @BindView(R.id.tv_delete)
        TextView tv_delete;

        public TopViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }

    private ValueChangeListener mValueChangeListener;

    public interface ValueChangeListener {

        void onItemDeleteClick(int position);

    }

    public void setOnValueChangeListener(ValueChangeListener listener) {
        this.mValueChangeListener = listener;
    }
}
