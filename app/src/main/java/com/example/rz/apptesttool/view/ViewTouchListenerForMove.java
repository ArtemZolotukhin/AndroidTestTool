package com.example.rz.apptesttool.view;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

public class ViewTouchListenerForMove implements View.OnTouchListener {

    private Context context;

    /**
     * in milliseconds
     */
    public static final long DEFAULT_LONG_PRESS_TIMEOUT = 1200;

    private int lastAction;

    private float dX;
    private float dY;
    private int screenWight;
    private int screenHeight;
    private long longPressTimeout;

    public ViewTouchListenerForMove(Context context) {
        this(context, DEFAULT_LONG_PRESS_TIMEOUT);
    }

    /**
     *
     * @param longPressTimeout - in milliseconds
     */
    public ViewTouchListenerForMove(Context context, long longPressTimeout) {
        this.context = context;
        this.longPressTimeout = longPressTimeout;
        fillScreenSizes();
    }

    private void fillScreenSizes() {
        DisplayMetrics displayMetrics =
                context.getResources().getDisplayMetrics();
        screenWight = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
    }

    @Override
    public boolean onTouch(View view, MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dX = view.getX() - ev.getRawX();
                dY = view.getY() - ev.getRawY();
                lastAction = MotionEvent.ACTION_DOWN;
                break;
            case MotionEvent.ACTION_UP:
                if (lastAction == MotionEvent.ACTION_DOWN && (ev.getEventTime() - ev.getDownTime()) < this.longPressTimeout) {
                    view.callOnClick();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float newY = ev.getRawY() + dY;
                float newX = ev.getRawX() + dX;
                if (newY > 0 && newY < screenHeight - view.getHeight()) {
                    view.setY(newY);
                }
                if (newX > 0 && newX < screenWight - view.getWidth()) {
                    view.setX(newX);
                }
                lastAction = MotionEvent.ACTION_MOVE;
                break;
        }
        return true;
    }
}
