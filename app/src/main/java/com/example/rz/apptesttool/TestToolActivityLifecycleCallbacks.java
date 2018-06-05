package com.example.rz.apptesttool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.rz.apptesttool.mvp.model.StatisticRepositoryImpl;
import com.example.rz.apptesttool.mvp.model.TimeInfo;
import com.example.rz.apptesttool.mvp.model.TimeService;
import com.example.rz.apptesttool.mvp.model.TouchInfo;
import com.example.rz.apptesttool.mvp.model.providers.TimeServiceProvider;
import com.example.rz.apptesttool.mvp.view.ActivityReviewActivity;
import com.example.rz.apptesttool.view.ViewTouchListenerForMove;


/**
 * Created by rz on 20.03.18.
 */

public class TestToolActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    public static final String LOG_TAG = "TTALC";

    public static final String INTENT_IS_TEST_BUTTON_CREATED = "TestTool:isTestButtonExists";

    public static final String EXCLUDED_ACTIVITY = "com.example.rz.apptesttool.mvp.view.ActivityReviewActivity";

    private Context context;

    private long time1;
    private TimeInfo timeInfo;
    private long currentTime;

    private ViewTouchListenerForMove viewTouchListenerForMove;

    public TestToolActivityLifecycleCallbacks(Context context) {
        if (context == null) {
            throw new NullPointerException("Context must be not null!");
        }
        this.context = context;
        viewTouchListenerForMove = new ViewTouchListenerForMove(context);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        dropTestToolSettingsInActivity(activity);
        TimeInfo timeInfo = new TimeInfo();
        timeInfo.setActivity(activity.getClass().getName());
        timeInfo.setTime(0);
        StatisticRepositoryImpl repository = StatisticRepositoryImpl.getInstance(context);
        repository.putTimeInfo(timeInfo);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityResumed(Activity activity) {
        time1 = System.currentTimeMillis();
        StatisticRepositoryImpl statisticRepository = StatisticRepositoryImpl.getInstance(context);
        timeInfo = statisticRepository.getTimeInfoByActivity(activity.getClass().getName());
        currentTime = timeInfo.getTime();

        if (activity.getClass().getName().equals(EXCLUDED_ACTIVITY)) {
            return;
        }
        Intent intent = getOrCreateIntent(activity);

        boolean isButtonExists = intent.getBooleanExtra(INTENT_IS_TEST_BUTTON_CREATED, false);

        if (!isButtonExists) {
            activity.getWindow().getDecorView();
            FrameLayout frameLayout = new FrameLayout(activity);
            FrameLayout.LayoutParams rootParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            frameLayout.setLayoutParams(rootParams);
            FrameLayout.LayoutParams params = getFrameLayoutParamsForButton();

            Button btn = (Button) LayoutInflater.from(context).inflate(R.layout.cool_button, null);

            btn.setOnTouchListener(viewTouchListenerForMove);

            frameLayout.addView(btn, params);
            frameLayout.setOnTouchListener((view, motionEvent) -> {
                TouchInfo touchInfo = new TouchInfo(motionEvent.getRawX(), motionEvent.getRawY(), activity.getClass().getName());
                Log.d(LOG_TAG, touchInfo.toString());
                statisticRepository.put(touchInfo);
                return false;
            });
            btn.setOnClickListener(view -> {
                startActivityReview(activity);
            });
            activity.getWindow().addContentView(frameLayout, rootParams);
            intent.putExtra(INTENT_IS_TEST_BUTTON_CREATED, true);
        }
    }


    private void startActivityReview(Activity activity) {
        Intent intent = new Intent(context, ActivityReviewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(ActivityReviewActivity.INTENT_PARAM_ACTIVITY_CLASS_NAME, activity.getClass().getName());
        context.startActivity(intent);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        StatisticRepositoryImpl.getInstance(context).updateTimeInfo(currentTime + System.currentTimeMillis() - time1, timeInfo);
        TimeService timeService = TimeServiceProvider.get();
        TimeInfo timeInfo1 = new TimeInfo();
        timeInfo1.setTime(System.currentTimeMillis() - time1);
        timeInfo1.setActivity(activity.getClass().getName());
        timeService.send(timeInfo1, voidIntegerResponse -> {
            if(voidIntegerResponse.isSuccessfull()) {
                //чё-нить надо
            }
        });
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        TimeService timeService = TimeServiceProvider.get();
        TimeInfo timeInfo1 = new TimeInfo();
        timeInfo1.setTime(System.currentTimeMillis() - time1);
        timeInfo1.setActivity(activity.getClass().getName());
        timeService.send(timeInfo1, voidIntegerResponse -> {
            if(voidIntegerResponse.isSuccessfull()) {
                //чё-нить надо
            }
        });
    }

    private Intent getOrCreateIntent(Activity activity) {
        Intent intent = activity.getIntent();
        if (intent == null) {
            intent = new Intent();
            activity.setIntent(intent);
        }
        return intent;
    }

    private void dropTestToolSettingsInActivity(Activity activity) {
        Intent intent = activity.getIntent();
        if (intent != null) {
            intent.putExtra(INTENT_IS_TEST_BUTTON_CREATED, false);
        }
    }

    private FrameLayout.LayoutParams getFrameLayoutParamsForButton() {
        /** in dpi. And yes, it is hardcode... But no, it is suckless program*/
        float margin = 32.0f;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        int rawMargin = Math.round(margin * displayMetrics.widthPixels / (float) displayMetrics.densityDpi);
        params.setMargins(rawMargin, rawMargin, rawMargin, rawMargin);
        return params;
    }


}
