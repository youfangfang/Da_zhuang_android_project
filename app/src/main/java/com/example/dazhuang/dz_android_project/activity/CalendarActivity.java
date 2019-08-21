package com.example.dazhuang.dz_android_project.activity;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dazhuang.dz_android_project.R;
import com.example.dazhuang.dz_android_project.base.BaseActivity;
import com.example.dazhuang.calendarview.picker.BasePicker;
import com.example.dazhuang.calendarview.picker.TimePicker;
import com.example.dazhuang.calendarview.widget.PickerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author fangyou
 * @time 2019/7/10
 */
public class CalendarActivity extends BaseActivity implements TimePicker.OnTimeSelectListener {
    @BindView(R.id.tv_time)
    TextView tv_time;
    public static final DateFormat sSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private TimePicker mTimePicker;

    @Override
    protected int getLayout() {
        return R.layout.activity_calendar;
    }

    @Override
    protected void initEventAndData() {
        reset();
    }


    @OnClick(R.id.btn_show)
    public void btn_show() {
        try {
            // 设置选中时间
            Date date = sSimpleDateFormat.parse(tv_time.getText().toString());
            mTimePicker.setSelectedDate(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mTimePicker.show();

    }


    private void reset() {
        long currentTime = System.currentTimeMillis();
        mTimePicker = new TimePicker.Builder(getContext(), 7, this)
                // 设置时间区间
                .setRangDate(currentTime, currentTime + (1000L * 60 * 60 * 24 * 180))
                .setInterceptor(new BasePicker.Interceptor() {
                    @Override
                    public void intercept(PickerView pickerView, LinearLayout.LayoutParams params) {
                        pickerView.setVisibleItemCount(3);
                    }
                })
                // 设置 Formatter
                .setFormatter(new TimePicker.DefaultFormatter() {
                    @Override
                    public CharSequence format(TimePicker picker, int type, int position, long num) {
                        return super.format(picker, type, position, num);
                    }
                }).create();
    }

    @Override
    public void onTimeSelect(TimePicker picker, Date date) {
        tv_time.setText(sSimpleDateFormat.format(date));
    }
}
