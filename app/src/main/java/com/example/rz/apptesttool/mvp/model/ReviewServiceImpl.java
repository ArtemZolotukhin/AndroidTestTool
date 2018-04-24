package com.example.rz.apptesttool.mvp.model;

import com.example.rz.apptesttool.mvp.model.retrofit.ReviewServ;
import com.example.rz.apptesttool.mvp.model.retrofit.pojo.ReviewForm;
import com.example.rz.apptesttool.mvp.service.ReviewToReviewFormConverter;
import com.example.rz.apptesttool.mvp.service.ReviewToReviewFormConverterImpl;

import java.util.Set;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rz on 4/11/18.
 */

public class ReviewServiceImpl implements ReviewService {

    private Retrofit retrofit;

    private ReviewServ reviewServ;

    private ReviewToReviewFormConverter reviewToReviewFormConverter;

    public ReviewServiceImpl(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        reviewServ = retrofit.create(ReviewServ.class);
        reviewToReviewFormConverter = new ReviewToReviewFormConverterImpl();
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
    public Set<Criterion> getCriteries(Callback<Response<Set<Criterion>, Integer>> callback) {



        return null;
    }


}
