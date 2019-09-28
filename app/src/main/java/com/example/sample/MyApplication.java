package com.example.sample;

import android.app.Application;

import com.droidnet.DroidNet;


public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        DroidNet.init(this);
        mInstance = this;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        DroidNet.getInstance().removeAllInternetConnectivityChangeListeners();
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

}
