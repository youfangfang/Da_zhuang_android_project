package com.example.dazhuang.dz_android_project.activity;


import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dazhuang.dz_android_project.R;
import com.example.dazhuang.dz_android_project.base.BaseActivity;
import com.example.dazhuang.dz_android_project.utils.CodeUtils;
import com.example.dazhuang.dz_android_project.utils.RxCaptcha;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.dazhuang.dz_android_project.utils.RxCaptcha.TYPE.CHARS;

public class TestImageActivity extends BaseActivity {
    @BindView(R.id.et_test)
    EditText et_test;
    @BindView(R.id.image)
    ImageView image;
    private CodeUtils codeUtils;
    private String code;

    @Override
    protected int getLayout() {
        return R.layout.activity_test_image;
    }

    @Override
    protected void initEventAndData() {
        code="a1b2";
        RxCaptcha.build()
                .backColor(0xffffff)
                .codeLength(4)
                .fontSize(60)
                .lineNumber(2)
                .size(200, 70)
                .type(CHARS)
                .into(image,code);
//        code = RxCaptcha.build().getCode();
    }

    @OnClick(R.id.btn_submit)
    public void btn_submit() {
        String test = et_test.getText().toString().trim();
        if (TextUtils.isEmpty(test)) {
            Toast.makeText(getContext(), "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (test.equals(code)) {
            Toast.makeText(getContext(), "验证码:" + code, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "验证码错误", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.image)
    public void image() {
        code="b2c3";
        RxCaptcha.build()
                .backColor(0xffffff)
                .codeLength(4)
                .fontSize(60)
                .lineNumber(2)
                .size(200, 70)
                .type(CHARS)
                .into(image,code);
//        code = RxCaptcha.build().getCode();
    }
}
