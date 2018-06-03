package com.example.rz.apptesttool.mvp.model.retrofit;

import com.example.rz.apptesttool.mvp.model.retrofit.pojo.SendTimeResponse;
import com.example.rz.apptesttool.mvp.model.retrofit.pojo.TimeForm;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by void on 6/3/18.
 */

public interface TimeServ {

    @POST("/time")
    Observable<SendTimeResponse> send(@Body TimeForm form);
}
