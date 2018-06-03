package com.example.rz.apptesttool.mvp.service;

import com.example.rz.apptesttool.mvp.model.Review;
import com.example.rz.apptesttool.mvp.model.retrofit.pojo.ReviewForm;

/**
 * Created by rz on 4/24/18.
 */

public interface ReviewToReviewFormConverter {

    ReviewForm convert(Review review, String deviceId);

}
