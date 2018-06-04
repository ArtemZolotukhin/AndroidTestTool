package com.example.rz.apptesttool.mvp.model;

import android.content.Context;
import android.util.Log;

import com.example.rz.apptesttool.TestToolApplication;
import com.example.rz.apptesttool.mvp.model.providers.DeviceIdServiceProvider;
import com.example.rz.apptesttool.mvp.model.providers.RetrofitProvider;
import com.example.rz.apptesttool.mvp.model.retrofit.ReviewServ;
import com.example.rz.apptesttool.mvp.model.retrofit.pojo.ReviewForm;
import com.example.rz.apptesttool.mvp.service.ReviewToReviewFormConverter;
import com.example.rz.apptesttool.mvp.service.ReviewToReviewFormConverterImpl;

import java.util.HashSet;
import java.util.Set;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by rz on 4/11/18.
 */

public class ReviewServiceImpl implements ReviewService {

    public static final String LOG_TAG = "ReviewService";

    private Retrofit retrofit;

    private ReviewServ reviewServ;

    private ReviewToReviewFormConverter reviewToReviewFormConverter;

    private DeviceIdService deviceIdService;

    public ReviewServiceImpl(String baseUrl, String appId) {
        retrofit = RetrofitProvider.get(baseUrl);
        reviewServ = retrofit.create(ReviewServ.class);
        reviewToReviewFormConverter = new ReviewToReviewFormConverterImpl(appId);
        deviceIdService = DeviceIdServiceProvider.get();
    }

    @Override
    public void sendReview(Review review, Callback<Response<Void, Integer>> callback) {
        deviceIdService.getDeviceId(stringIntegerResponse -> {
            if (stringIntegerResponse.isSuccessfull()) {
                if (stringIntegerResponse.getError() == 0 || stringIntegerResponse.getError() == null) {
                    sendReview(review, callback, stringIntegerResponse.getValue());
                    return;
                }
            }
            //TODO normal error
            callback.call(Response.failure(1));
        });
    }

    private void sendReview(Review review, Callback<Response<Void, Integer>> callback, String deviceId) {
        ReviewForm reviewForm = reviewToReviewFormConverter.convert(review, deviceId);
        Log.d(LOG_TAG, "info: form to send: " + reviewForm.toString());
        reviewServ.reviewSend(reviewForm)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reviewResponse -> {
                    int code = reviewResponse.getCode();
                    if (code == 0) {
                        callback.call(Response.success(null));
                    } else {
                        Log.d(LOG_TAG, "Fail: Response with entry code = " + code);
                        //TODO normal error code
                        callback.call(Response.failure(404));
                    }
                }, throwable -> {
                    Log.d(LOG_TAG, "Fail: Response: throwable: " + throwable.getClass().getName()
                            + " " + throwable.getMessage());
                    //TODO normal error code
                    callback.call(Response.failure(1));
                });
    }

    @Override
    public void getCriteries(Callback<Response<Set<Criterion>, Integer>> callback) {
        //TODO Cache
        CriteriesForm criteriesForm = getCriteriesForm();
        reviewServ.categories(criteriesForm)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(criteriesResponse -> {
                    if (criteriesResponse != null) {
                        if (criteriesResponse.getCode() == 0) {
                            Set<Criterion> criteria = new HashSet<>();
                            criteria.addAll(criteriesResponse.getCriteries());
                            callback.call(Response.success(criteria));
                        } else {
                            callback.call(Response.failure(criteriesResponse.getCode()));
                        }
                    } else {
                        callback.call(Response.failure(1));
                    }
                }, throwable -> {
                    callback.call(Response.failure(1));
                });

    }


    public CriteriesForm getCriteriesForm() {
        return new CriteriesForm(TestToolApplication.getAppId());
    }
}
