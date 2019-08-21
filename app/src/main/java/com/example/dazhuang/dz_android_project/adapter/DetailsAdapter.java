package com.example.dazhuang.dz_android_project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dazhuang.dz_android_project.MyAdapter;
import com.example.dazhuang.dz_android_project.R;
import com.example.dazhuang.dz_android_project.bean.DetailsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 */

public class DetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DetailsBean> mList;
    private Context mContext;
    private int height = 0;
    public static final int GOODS_FIRST = 0;
    public static final int DETAILS_SECOND = 1;
    private LayoutInflater mInflater;

    public DetailsAdapter(Context context, List<DetailsBean> mList) {
        this.mContext = context;
        this.mList = mList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = null;
        if (viewType == GOODS_FIRST) {
            view = mInflater.inflate(R.layout.goods_main_first, viewGroup, false);
            return new GoodsFirstHolder(view);
        } else if (viewType == DETAILS_SECOND) {
            view = mInflater.inflate(R.layout.details_main_second, viewGroup, false);
            return new DetailsSecondHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        DetailsBean detailsBean = mList.get(position);
        if (viewHolder instanceof GoodsFirstHolder) {
            final GoodsFirstHolder goodsFirstHolder = (GoodsFirstHolder) viewHolder;
            getMeasureHeight(goodsFirstHolder.goods_item, GOODS_FIRST);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            goodsFirstHolder.my_recyclerview.setLayoutManager(linearLayoutManager);
            List<String> msList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                msList.add(i + "");
            }
            com.example.dazhuang.dz_android_project.MyAdapter myAdapter = new MyAdapter(mContext, msList);
            goodsFirstHolder.my_recyclerview.setAdapter(myAdapter);
        }
        if (viewHolder instanceof DetailsSecondHolder) {
            final DetailsSecondHolder detailsSecondHolder = (DetailsSecondHolder) viewHolder;
            getMeasureHeight(detailsSecondHolder.details_item, DETAILS_SECOND);
            detailsSecondHolder.details_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "wert", Toast.LENGTH_SHORT).show();
                }
            });
//            detailsSecondHolder.details_item.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                @Override
//                public void onGlobalLayout() {
//                    if (listener != null) {
//                        if (height != 0) {
//                            height += detailsSecondHolder.details_item.getHeight();
//                            listener.setOnItemHeightListener(height, DETAILS_SECOND);
//                        } else {
//                            height = detailsSecondHolder.details_item.getHeight();
//                        }
//                    }
//                }
//            });
        }

//        int type = getItemViewType(position);
//        if (type == 1001) {
//            final LinearLayout item = holder.getView(R.id.item);
//            getMeasureHeight(item, type);
//        }
//        if (type == 1003) {
//            final LinearLayout item = holder.getView(R.id.item);
//            getMeasureHeight(item, type);
//        }
//        if (type == 1004) {
//            final LinearLayout item = holder.getView(R.id.item);
//            getMeasureHeight(item, type);
//        }

    }

    @Override
    public int getItemViewType(int position) {
        int type = mList.get(position).getType();
        if (type == 1) {
            return GOODS_FIRST;
        } else if (type == 2) {
            return DETAILS_SECOND;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    /**
     * 获取每个item的高度
     *
     * @param view item的跟布局
     * @param type 用于判断是那个item的高度
     */
    public void getMeasureHeight(final View view, final int type) {

        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (listener != null) {
                    if (type == DETAILS_SECOND) {
                        if (height != 0) {
                            height += view.getHeight();
                            listener.setOnItemHeightListener(height, type);
                        } else {
                            height = view.getHeight();
                        }
                    }
                    listener.setOnItemHeightListener(view.getHeight(), type);
                }
                return true;
            }
        });


//        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                if (listener != null) {
//                    if (type == DETAILS_SECOND) {
//                        if (height != 0) {
//                            height += view.getHeight();
//                            listener.setOnItemHeightListener(height, type);
//                        } else {
//                            height = view.getHeight();
//                        }
//                    }
//                    listener.setOnItemHeightListener(view.getHeight(), type);
//                }
//            }
//        });

    }


    public static class GoodsFirstHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_item)
        LinearLayout goods_item;
        @BindView(R.id.my_recyclerview)
        RecyclerView my_recyclerview;

        public GoodsFirstHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }

    public static class DetailsSecondHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.details_item)
        LinearLayout details_item;

        public DetailsSecondHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }


    public interface OnItemHeightListener {
        void setOnItemHeightListener(int height, int type);

    }

    private OnItemHeightListener listener;

    public void setListener(OnItemHeightListener listener) {
        this.listener = listener;
    }
}
