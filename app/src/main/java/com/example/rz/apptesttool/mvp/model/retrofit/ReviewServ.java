package com.example.rz.apptesttool.mvp.model.retrofit;

import com.example.rz.apptesttool.mvp.model.CriteriesForm;
import com.example.rz.apptesttool.mvp.model.retrofit.pojo.CriteriesResponse;
import com.example.rz.apptesttool.mvp.model.retrofit.pojo.ReviewForm;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by rz on 4/24/18.
 */

public interface ReviewServ {

    @POST("/review")
    Observable<ReviewResponse> reviewSend(@Body ReviewForm reviewForm);

    @POST("/review/criterions")
    Observable<CriteriesResponse> categories(@Body CriteriesForm criteriesForm);
}
