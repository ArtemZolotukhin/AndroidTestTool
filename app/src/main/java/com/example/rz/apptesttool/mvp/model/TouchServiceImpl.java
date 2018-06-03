package com.example.rz.apptesttool.mvp.model;

import com.example.rz.apptesttool.mvp.model.providers.DeviceIdServiceProvider;
import com.example.rz.apptesttool.mvp.model.providers.RetrofitProvider;
import com.example.rz.apptesttool.mvp.model.retrofit.TouchServ;
import com.example.rz.apptesttool.mvp.model.retrofit.pojo.SendTouchesForm;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by void on 6/3/18.
 */

public class TouchServiceImpl implements TouchService {

    private String appId;

    private TouchServ touchServ;

    private DeviceIdService deviceIdService;

    public TouchServiceImpl(String baseUrl, String appId) {
        this.appId = appId;
        touchServ = RetrofitProvider.get(baseUrl).create(TouchServ.class);
        deviceIdService = DeviceIdServiceProvider.get();
    }

    @Override
    public void send(List<TouchInfo> touches, Callback<Response<Void, Integer>> callback) {

        deviceIdService.getDeviceId(stringIntegerResponse -> {
            if (stringIntegerResponse.isSuccessfull()) {
                if (stringIntegerResponse.getError() == 0 || stringIntegerResponse.getError() == null) {
                    String deviceId = stringIntegerResponse.getValue();
                    touchServ.deviceIndoSend(getForm(touches, deviceId))
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(sendTouchResult -> {
                                if (sendTouchResult.getCode() == 0) {
                                    callback.call(Response.success(null, 0));
                                } else {
                                    //TODO normal error codes
                                    callback.call(Response.failure(1));
                                }
                            }, throwable -> {
                                callback.call(Response.failure(1));
                            });
                } else {
                    callback.call(Response.failure(1));
                }
            } else {
                callback.call(Response.failure(1));
            }

        });

    }

    private SendTouchesForm getForm(List<TouchInfo> touches, String deviceId) {
        SendTouchesForm form = new SendTouchesForm();
        form.setTouchMap(touches);
        form.setAppId(appId);
        form.setDeviceId(deviceId);
        return form;
    }
}
