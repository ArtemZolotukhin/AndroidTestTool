package com.example.rz.apptesttool;

import android.app.Application;
import android.view.WindowManager;

/**
 * Created by rz on 20.03.18.
 */

public class TestToolApplication extends Application {

    private ActivityLifecycleCallbacks activityLifecycleCallbacks;

    private boolean isTestMode;

    @Override
    public void onCreate() {
        super.onCreate();
        activityLifecycleCallbacks = new TestToolActivityLifecycleCallbacks(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
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
