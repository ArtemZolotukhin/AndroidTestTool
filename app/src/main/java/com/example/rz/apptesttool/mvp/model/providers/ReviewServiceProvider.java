package com.example.rz.apptesttool.mvp.model.providers;

import com.example.rz.apptesttool.TestToolApplication;
import com.example.rz.apptesttool.mvp.model.ReviewService;
import com.example.rz.apptesttool.mvp.model.ReviewServiceImpl;

/**
 * Created by void on 6/3/18.
 */

public class ReviewServiceProvider {

    public static ReviewService get() {
        return new ReviewServiceImpl(TestToolApplication.getBaseUrl(), TestToolApplication.getAppId());
    }

}
