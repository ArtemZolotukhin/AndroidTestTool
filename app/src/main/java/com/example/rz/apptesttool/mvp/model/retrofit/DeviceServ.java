package com.example.rz.apptesttool.mvp.model.retrofit;

import com.example.rz.apptesttool.mvp.model.retrofit.pojo.SendDeviceInfoForm;
import com.example.rz.apptesttool.mvp.model.retrofit.pojo.SendDeviceInfoResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by void on 6/3/18.
 */

public interface DeviceServ {
    @POST("/device")
    Observable<SendDeviceInfoResponse> deviceIndoSend(@Body SendDeviceInfoForm sendDeviceInfoForm);
}
