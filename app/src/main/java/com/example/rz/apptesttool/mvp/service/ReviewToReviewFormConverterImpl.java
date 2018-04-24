package com.example.rz.apptesttool.mvp.service;

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


    @Override
    public ReviewForm convert(Review review) {
        //TODO appId
        ReviewForm reviewForm = new ReviewForm();
        if (review == null) {
            return reviewForm;
        }
        reviewForm.setDisplayName(review.getDisplayName());
        reviewForm.setReview(review.getMessage());
        Set<CriterionForm> criterionForms = new HashSet<>();
        for (ReviewItem i : review.getReviewItemSet()) {
            criterionForms.add(reviewItemToCriterionForm(i));
        }
        reviewForm.setAppId(TestToolApplication.getAppId());
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
