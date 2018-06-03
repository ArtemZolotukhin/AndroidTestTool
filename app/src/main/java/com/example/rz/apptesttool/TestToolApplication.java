package com.example.rz.apptesttool;

import android.app.Application;
import android.content.Context;
import android.view.WindowManager;

/**
 * Created by rz on 20.03.18.
 */

public class TestToolApplication extends Application {

    private ActivityLifecycleCallbacks activityLifecycleCallbacks;

    private boolean isTestMode;

    private static String baseUrl = "";

    private static String appId = "0";

    private static Context mContext;

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        TestToolApplication.appId = appId;
    }

    public static void setBaseUrl(String baseUrl) {
        TestToolApplication.baseUrl = baseUrl;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        activityLifecycleCallbacks = new TestToolActivityLifecycleCallbacks(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    public static Context getContext() {
        return mContext;
    }

    public ActivityLifecycleCallbacks getActivityLifecycleCallbacks() {
        return activityLifecycleCallbacks;
    }

    public void setActivityLifecycleCallbacks(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.activityLifecycleCallbacks = activityLifecycleCallbacks;
    }

    public boolean isTestMode() {
        return isTestMode;
    }

    public void setTestMode(boolean testMode) {
        isTestMode = testMode;
        if (isTestMode) {
            registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        } else {
            unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }
    }
}
