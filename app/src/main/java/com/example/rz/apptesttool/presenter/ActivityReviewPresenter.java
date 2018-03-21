package com.example.rz.apptesttool.presenter;

import com.example.rz.apptesttool.view.ActivityReviewView;

/**
 * Created by rz on 20.03.18.
 */

public class ActivityReviewPresenter {

    private ActivityReviewView view;

    public ActivityReviewPresenter(ActivityReviewView view) {
        if (view == null) {
            throw new NullPointerException("ActivityReviewView must be not null");
        }
        this.view = view;
    }

    public void onCloseClick() {
        view.close();
    }

}
