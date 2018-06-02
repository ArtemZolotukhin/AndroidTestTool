package com.example.rz.apptesttool.mvp.model;

import com.example.rz.apptesttool.TestToolApplication;
import com.example.rz.apptesttool.mvp.model.retrofit.ReviewServ;
import com.example.rz.apptesttool.mvp.model.retrofit.pojo.ReviewForm;
import com.example.rz.apptesttool.mvp.service.ReviewToReviewFormConverter;
import com.example.rz.apptesttool.mvp.service.ReviewToReviewFormConverterImpl;

import java.util.HashSet;
import java.util.Set;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rz on 4/11/18.
 */

public class ReviewServiceImpl implements ReviewService {

    private Retrofit retrofit;

    private ReviewServ reviewServ;

    private ReviewToReviewFormConverter reviewToReviewFormConverter;

    public ReviewServiceImpl(String baseUrl, String appId) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        reviewServ = retrofit.create(ReviewServ.class);
        reviewToReviewFormConverter = new ReviewToReviewFormConverterImpl(appId);
    }

    @Override
    public void sendReview(Review review, Callback<Response<Void, Integer>> callback) {
        ReviewForm reviewForm = reviewToReviewFormConverter.convert(review);
        reviewServ.reviewSend(reviewForm)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reviewResponse -> {
                    if (reviewResponse.getCode() == 0) {
                        callback.call(Response.success(null));
                    } else {
                        //TODO normal error code
                        callback.call(Response.failure(404));
                    }
                }, throwable -> {
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
