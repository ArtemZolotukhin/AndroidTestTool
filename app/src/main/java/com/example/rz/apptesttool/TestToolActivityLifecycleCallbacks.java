package com.example.rz.apptesttool;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.rz.apptesttool.mvp.view.ActivityReviewActivity;
import com.example.rz.apptesttool.view.MoveButton;


/**
 * Created by rz on 20.03.18.
 */

public class TestToolActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    public static final String LOG_TAG = "TestToolDebug";

    public static final String INTENT_IS_TEST_BUTTON_CREATED = "TestTool:isTestButtonExists";

    //TODO rewrite on annotation based... may be :)
    public static final String EXCLUDED_ACTIVITY = "com.example.rz.apptesttool.mvp.view.ActivityReviewActivity";

    private Context context;

    private boolean buttonIsMove;
    private boolean buttonInFocus;

    public TestToolActivityLifecycleCallbacks(Context context) {
        if (context == null) {
            throw new NullPointerException("Context must be not null!");
        }
        this.context = context;
        buttonIsMove = false;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        dropTestToolSettingsInActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (activity.getClass().getName().equals(EXCLUDED_ACTIVITY)) {
            return;
        }
        Intent intent = getOrCreateIntent(activity);

        boolean isButtonExists = intent.getBooleanExtra(INTENT_IS_TEST_BUTTON_CREATED, false);

        if (!isButtonExists) {
            activity.getWindow().getDecorView();
            //TODO normal
            FrameLayout frameLayout = new FrameLayout(activity);
            FrameLayout.LayoutParams rootParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            frameLayout.setLayoutParams(rootParams);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.BOTTOM | Gravity.RIGHT;

            Button btn = new Button(context);
            btn.setText("X");
//            btn.setOnLongClickListener(view -> {
//                view.startDrag(null,
//                        new View.DragShadowBuilder(view), null, 0);
//                return true;
//            });
//            btn.setOnDragListener((view, dragEvent) -> {
//                switch (dragEvent.getAction()) {
//                    case DragEvent.ACTION_DRAG_STARTED:
//                        view.setVisibility(View.INVISIBLE);
//                        break;
//                    case DragEvent.ACTION_DRAG_LOCATION:
//                        view.setTranslationX(dragEvent.getX());
//                        view.setTranslationY(dragEvent.getY());
//                    case DragEvent.ACTION_DRAG_ENDED:
//                        view.setVisibility(View.VISIBLE);
//                        break;
//
//                }
//                return true;
//            });
            btn.setOnLongClickListener(view -> {
                buttonIsMove = true;
                return true;
            });
            //Я хочу умереть...
            btn.setOnTouchListener((view, motionEvent) -> {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        return true;
                    case MotionEvent.ACTION_MOVE:
                           btn.setX(motionEvent.getRawX() - btn.getWidth() / 2);
                            btn.setY(motionEvent.getRawY() - btn.getHeight() / 2);
                            buttonIsMove = true;
                        return true;
                    case MotionEvent.ACTION_UP:
                        if (buttonIsMove) {
                            buttonIsMove = false;
                            return true;
                        } else {
                            return false;
                        }
                    default:
                        return false;
                }
            });
            btn.setOnClickListener(view -> {
                startActivityReview(activity);
            });
            activity.getWindow().addContentView(btn, params);
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

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

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




}
