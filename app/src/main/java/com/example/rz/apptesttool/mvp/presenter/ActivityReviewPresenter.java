package com.example.rz.apptesttool.mvp.presenter;

import android.util.Log;

import com.example.rz.apptesttool.TestToolApplication;
import com.example.rz.apptesttool.mvp.model.Criterion;
import com.example.rz.apptesttool.mvp.model.Review;
import com.example.rz.apptesttool.mvp.model.ReviewService;
import com.example.rz.apptesttool.mvp.model.ReviewServiceImpl;
import com.example.rz.apptesttool.mvp.view.ActivityReviewView;

import java.util.Set;

/**
 * Created by rz on 20.03.18.
 */

public class ActivityReviewPresenter extends AbstractPresenter {

    public static final String LOG_TAG = "AAAAAAAAAAAA";

    private ActivityReviewView view;

    private ReviewService reviewService;

    public ActivityReviewPresenter(ActivityReviewView view) {
        if (view == null) {
            throw new NullPointerException("ActivityReviewView must be not null");
        }
        this.view = view;
        // TODO may be Dagger 2
        this.reviewService = new ReviewServiceImpl(TestToolApplication.getBaseUrl(),
                TestToolApplication.getAppId());
        loadCriterions();
    }

    public void loadCriterions() {
        if (getView() != null) {
            getView().setLoading(true);
            reviewService.getCriteries(criterionsResponse -> {
                Set<Criterion> criterionSet = null;
                if (criterionsResponse.isSuccessfull()) {
                    criterionSet = criterionsResponse.getValue();
                    if (criterionSet != null) {
                        getView().updateCriterions(criterionSet);
                    }
                } else {
                    Log.d(LOG_TAG, String.valueOf(criterionsResponse.getError()));
                    getView().showError(ActivityReviewView.ERROR_CODE_CRITERIONS_LOAD);
                }
                getView().setLoading(false);
            });
        }
    }

    public void onSendClick() {
        Review review = view.getReview();
        getView().setLoading(true);
        reviewService.sendReview(review, voidIntegerResponse -> {
            if (voidIntegerResponse.isSuccessfull()) {
                view.close();
            } else {
                getView().setLoading(false);
                view.showError(ActivityReviewView.ERROR_CODE_SEND);
            }
        });
    }

    public void onCloseClick() {
        if (view != null) {
            view.close();
        }
    }

    public ActivityReviewView getView() {
        return view;
    }

    public ActivityReviewPresenter setView(ActivityReviewView view) {
        this.view = view;
        return this;
    }
}
