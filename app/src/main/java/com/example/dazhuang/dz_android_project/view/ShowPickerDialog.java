package com.example.dazhuang.dz_android_project.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.dazhuang.dz_android_project.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fangyou
 * @time 2019/9/17
 */
public class ShowPickerDialog extends Dialog {
    private ScrollPickerView mScrollPickerView;
    private Context context;

    public ShowPickerDialog(@NonNull Context context) {
        super(context, R.style.otherDialog);
        setContentView(R.layout.dialog_picker);
        this.context = context;
        mScrollPickerView = findViewById(R.id.scroll_picker_view);
        mScrollPickerView.setLayoutManager(new LinearLayoutManager(context));
        initData();
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }

    private void initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String itemData = "item: " + i;
            list.add(itemData);
        }

        ScrollPickerAdapter.ScrollPickerAdapterBuilder<String> builder =
                new ScrollPickerAdapter.ScrollPickerAdapterBuilder<String>(context)
                        .setDataList(list)
                        .selectedItemOffset(2)
                        .visibleItemNumber(3)
                        .setDivideLineColor("#E5E5E5")
                        .setItemViewProvider(null)
                        .setOnClickListener(new ScrollPickerAdapter.OnClickListener() {
                            @Override
                            public void onSelectedItemClicked(View v) {
                                String text = (String) v.getTag();
                                if (text != null) {
                                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
        ScrollPickerAdapter mScrollPickerAdapter = builder.build();
        mScrollPickerView.setAdapter(mScrollPickerAdapter);
    }
}
