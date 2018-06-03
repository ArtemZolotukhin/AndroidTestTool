package com.example.rz.apptesttool.mvp.model.retrofit;

import com.example.rz.apptesttool.mvp.model.retrofit.pojo.SendTouchResult;
import com.example.rz.apptesttool.mvp.model.retrofit.pojo.SendTouchesForm;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by void on 6/3/18.
 */

public interface TouchServ {

    @POST("/touch_map")
    Observable<SendTouchResult> send(@Body SendTouchesForm form);
}
