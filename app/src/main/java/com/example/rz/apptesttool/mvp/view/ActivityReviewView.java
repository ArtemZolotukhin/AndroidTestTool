package com.example.rz.apptesttool.mvp.view;

import com.example.rz.apptesttool.mvp.model.Criterion;
import com.example.rz.apptesttool.mvp.model.Review;

import java.util.Set;

/**
 * Created by rz on 20.03.18.
 */

public interface ActivityReviewView {

    int ERROR_CODE_LOAD = 1;

    void close();

    void setLoading(boolean isLoading);

    void updateCriterions(Set<Criterion> criterionSet);

    Review getReview();

    void showError(int errorCode);
}
