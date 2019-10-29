package com.example.dazhuang.dz_android_project.view;

import android.view.View;

public interface IPickerViewOperation {
    int getSelectedItemOffset();

    int getVisibleItemNumber();

    int getLineColor();

    void updateView(View itemView, boolean isSelected);
}
