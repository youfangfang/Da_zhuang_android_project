package com.example.dazhuang.dz_android_project.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.example.dazhuang.dz_android_project.R;
import com.example.dazhuang.dz_android_project.bean.DetailsBean;

public class NewDetailsAdapter extends ListBaseAdapter<DetailsBean> {

    private int layoutID;
    private int height = 0;

    public NewDetailsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        int type = mDataList.get(position).getType();
        if (type == 1) {
//            商品
            layoutID = R.layout.goods_main_first;
            return 1001;
        } else if (type == 2) {
//            详情
            layoutID = R.layout.details_main_second;
            return 1002;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getLayoutId() {
        return layoutID;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == 1001) {
            final LinearLayout item = holder.getView(R.id.goods_item);
            getMeasureHeight(item, type);
        }
        if (type == 1002) {
            final LinearLayout item = holder.getView(R.id.details_item);
            getMeasureHeight(item, type);
        }

    }

    /**
     * 获取每个item的高度
     *
     * @param view item的跟布局
     * @param type 用于判断是那个item的高度
     */
    public void getMeasureHeight(final View view, final int type) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (listener != null) {
                    if (type == 1002) {
                        if (height != 0) {
                            height += view.getHeight();
                            listener.setOnItemHeightListener(height, type);
                        } else {
                            height = view.getHeight();
                        }
                    } else {
                        listener.setOnItemHeightListener(view.getHeight(), type);
                    }

                }
            }
        });
    }

    public interface OnItemHeightListener {
        void setOnItemHeightListener(int height, int type);
    }

    private OnItemHeightListener listener;

    public void setListener(OnItemHeightListener listener) {
        this.listener = listener;
    }
}
