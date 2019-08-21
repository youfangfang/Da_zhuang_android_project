package com.example.dazhuang.dz_android_project.view.guideView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dazhuang.dz_android_project.R;

/**
 * @author fangyou
 * @time 2019/6/14
 */
public class SimpleComponent implements Component {
    private String hintContext;
    private int anchor;
    private int fitPosition;
    private int XOffset;

    public void setAnchor(int anchor) {
        this.anchor = anchor;
    }

    public void setFitPosition(int fitPosition) {
        this.fitPosition = fitPosition;
    }

    public void setXOffset(int XOffset) {
        this.XOffset = XOffset;
    }

    public void setYOffset(int YOffset) {
        this.YOffset = YOffset;
    }

    private int YOffset;

    public void setHintContext(String hintContext) {
        this.hintContext = hintContext;
    }

    @Override
    public View getView(LayoutInflater inflater) {

        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.layout_one_category, null);
        TextView tv_text = (TextView) ll.findViewById(R.id.tv_text);
        tv_text.setText(hintContext);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        return ll;
    }

    @Override
    public int getAnchor() {
        return anchor;
    }

    @Override
    public int getFitPosition() {
        return fitPosition;
    }

    @Override
    public int getXOffset() {
        return XOffset;
    }

    @Override
    public int getYOffset() {
        return YOffset;
    }
}
