package com.example.shanshan.dialogbuilderdemo;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public class MyHandler extends Handler {
    private final WeakReference<MainActivity> activityWeakReference;

    public static final int UPDATETITLE=1;
    public static final int UPDATEMEG=2;
    public static final int UPDATESHOWBTN=3;
    public static final int UPDATESHOWONBTN=4;

    public MyHandler(MainActivity activityWeakReference) {
        this.activityWeakReference = new WeakReference<MainActivity>(activityWeakReference);
    }

    @Override
    public void handleMessage(Message msg) {
        MainActivity activity=activityWeakReference.get();
        if (activity==null){
            super.handleMessage(msg);
            return;
        }

        switch (msg.what){
            case UPDATETITLE:
                activity.updateMsg();
                break;
            case UPDATEMEG:
                activity.updateOneMsg();
                break;
            case UPDATESHOWBTN:
                activity.showTwoBtn();
                break;
            case UPDATESHOWONBTN:
                activity.showOneBtn();
                break;
        }
    }
}
