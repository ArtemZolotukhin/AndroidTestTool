package com.example.rz.apptesttool.mvp.presenter;

import com.example.rz.apptesttool.mvp.model.Review;
import com.example.rz.apptesttool.mvp.model.ReviewService;
import com.example.rz.apptesttool.mvp.model.providers.ReviewServiceProvider;
import com.example.rz.apptesttool.mvp.view.ActivityReviewView;

/**
 * Created by rz on 20.03.18.
 */

public class ActivityReviewPresenter extends AbstractPresenter {

    private ActivityReviewView view;

    private ReviewService reviewService;

    private boolean isLoading;

    public ActivityReviewPresenter(ActivityReviewView view) {
        if (view == null) {
            throw new NullPointerException("ActivityReviewView must be not null");
        }
        this.view = view;
        this.isLoading = false;
        this.reviewService = ReviewServiceProvider.get();
        loadCriterions();
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        if (getView() != null) {
            getView().setLoading(loading);
        }
    }

    public void loadCriterions() {
        if (!isLoading && getView() != null) {
            setLoading(true);
            reviewService.getCriteries(criterionsResponse -> {
                setLoading(false);
                if (getView() != null) {
                    if (criterionsResponse.isSuccessfullAndValueNotNull()) {
                        getView().updateCriterions(criterionsResponse.getValue());
                    } else {
                        showError(ActivityReviewView.ERROR_CODE_CRITERIONS_LOAD);
                    }
                }
            });
        }
    }

    private void showError(int code) {
        getView().showError(code);
    }

    public void onSendClick() {
        if (!isLoading && getView() != null) {
            setLoading(true);
            Review review = view.getReview();
            reviewService.sendReview(review, voidIntegerResponse -> {
                setLoading(false);
                if (view != null) {
                    if (voidIntegerResponse.isSuccessfull()) {
                        view.showMessage(ActivityReviewView.MESSAGE_SUCCESS, true);
                    } else {
                        view.showError(ActivityReviewView.ERROR_CODE_SEND);
                    }
                }
            });
        }
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
