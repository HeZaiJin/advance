package com.hezaijin.advance;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class advanceApp extends Application {
    private static advanceApp sApplication;


    public static advanceApp getInstance() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = (advanceApp) getApplicationContext();
        // 内存泄漏检测
        LeakCanary.install(this);
    }
}
