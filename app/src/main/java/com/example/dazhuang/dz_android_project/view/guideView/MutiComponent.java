package com.example.dazhuang.dz_android_project.view.guideView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dazhuang.dz_android_project.R;


/**
 * Created by binIoter on 16/6/17.
 */
public class MutiComponent implements Component {

  @Override
  public View getView(LayoutInflater inflater) {
    LinearLayout ll = new LinearLayout(inflater.getContext());
    LinearLayout.LayoutParams param =
        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    ll.setOrientation(LinearLayout.VERTICAL);
    ll.setLayoutParams(param);
    TextView textView = new TextView(inflater.getContext());
    textView.setText("点击更多，切换自己长购买的分类哦~");
    textView.setTextColor(inflater.getContext().getResources().getColor(R.color.white));
    textView.setTextSize(16);
    ll.removeAllViews();
    ll.addView(textView);
    ll.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(view.getContext(), "引导层被点击了", Toast.LENGTH_SHORT).show();
      }
    });
    return ll;
  }

  @Override
  public int getAnchor() {
    return Component.ANCHOR_TOP;
  }

  @Override
  public int getFitPosition() {
    return Component.FIT_CENTER;
  }

  @Override
  public int getXOffset() {
    return -200;
  }

  @Override
  public int getYOffset() {
    return 0;
  }
}
