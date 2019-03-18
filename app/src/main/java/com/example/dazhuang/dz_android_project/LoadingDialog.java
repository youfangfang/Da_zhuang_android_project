package com.example.dazhuang.dz_android_project;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

public class LoadingDialog extends Dialog {
    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.LoadingDialog);
        setContentView(R.layout.dialog_loading);
    }
}
