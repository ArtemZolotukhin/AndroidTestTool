package com.example.rz.apptesttool.mvp.service;

import android.util.Log;

import com.example.rz.apptesttool.TestToolApplication;
import com.example.rz.apptesttool.mvp.model.Review;
import com.example.rz.apptesttool.mvp.model.ReviewItem;
import com.example.rz.apptesttool.mvp.model.retrofit.pojo.CriterionForm;
import com.example.rz.apptesttool.mvp.model.retrofit.pojo.ReviewForm;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rz on 4/24/18.
 */

public class ReviewToReviewFormConverterImpl implements ReviewToReviewFormConverter {

    private String appId;

    public ReviewToReviewFormConverterImpl(String appId) {
        this.appId = appId;
    }

    @Override
    public ReviewForm convert(Review review, String deviceId) {
        ReviewForm reviewForm = new ReviewForm();
        reviewForm.setAppId(appId);
        reviewForm.setDeviceId(deviceId);
        if (review == null) {
            return reviewForm;
        }
        reviewForm.setDisplayName(review.getDisplayName());
        reviewForm.setReview(review.getMessage());
        Set<CriterionForm> criterionForms = new HashSet<>();
        for (ReviewItem i : review.getReviewItemSet()) {
            if (i.isChecked()) {
                criterionForms.add(reviewItemToCriterionForm(i));
            }
        }
        reviewForm.setCriterions(criterionForms);
        Log.d("AAAAAAAAAAAAA", reviewForm.toString());
        return reviewForm;
    }

    private CriterionForm reviewItemToCriterionForm(ReviewItem reviewItem) {
        CriterionForm criterionForm = new CriterionForm();
        if (reviewItem != null) {
            criterionForm.setId((long) reviewItem.getId());
            criterionForm.setValue(reviewItem.getValue());
        }
        return criterionForm;
    }
}
