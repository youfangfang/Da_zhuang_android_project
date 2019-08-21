package com.example.dazhuang.dz_android_project.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class AlarmService extends Service{

    boolean isStart = false;
    Handler handler = new Handler();

    Runnable alarmRunnable = new Runnable() {
        @Override
        public void run() {
            //得到ActivityManager
            ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
            //getRunningTasks会返回一个List，List的大小等于传入的参数。
            //get(0)可获得List中的第一个元素，即栈顶的task
            ActivityManager.RunningTaskInfo info = activityManager.getRunningTasks(1).get(0);
            //得到当前栈顶的类名，按照需求，也可以得到完整的类名和包名
            String shortClassName = info.topActivity.getShortClassName(); //类名
            Toast.makeText(getApplicationContext(), "当前运行的程序为"+shortClassName, Toast.LENGTH_LONG).show();
        }
    };
    @Override
    public int onStartCommand(Intent intent, int flag, int startId) {
        super.onStartCommand(intent, flag, startId);
        if(!isStart) {
            isStart = true;
            //启动alarmRunnable
            handler.postDelayed(alarmRunnable, 1000);
            stopSelf();
        }
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
