package com.example.dazhuang.dz_android_project.view;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public interface IViewProvider<T> {
    @LayoutRes
    int resLayout();

    void onBindView(@NonNull View view, @Nullable T itemData);

    void updateView(@NonNull View itemView, boolean isSelected);
}
