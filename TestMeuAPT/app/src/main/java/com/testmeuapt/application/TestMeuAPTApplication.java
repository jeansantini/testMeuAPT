package com.testmeuapt.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by jsantini on 22/09/17.
 */

public class TestMeuAPTApplication extends Application {

    private static Context mContext;
    private static TestMeuAPTApplication mInstance;

    public static Context getAppContext() {
        return mContext;
    }

    public static synchronized TestMeuAPTApplication getInstance() {
        return mInstance;
    }

    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mInstance = this;
    }
}