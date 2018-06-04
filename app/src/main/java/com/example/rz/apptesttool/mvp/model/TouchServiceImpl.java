package com.example.rz.apptesttool.mvp.model;

import android.util.Log;

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

    public static final String LOG_TAG = "TouchService";

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
                    send(touches, callback, deviceId);
                } else {
                    callback.call(Response.failure(1));
                }
            } else {
                callback.call(Response.failure(1));
            }

        });

    }

    public void send(List<TouchInfo> touches, Callback<Response<Void, Integer>> callback, String deviceId) {
        SendTouchesForm form = getForm(touches, deviceId);
        Log.d(LOG_TAG, "info: form to send: " + form.toString());
        touchServ.send(form)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sendTouchResult -> {
                    int code = sendTouchResult.getCode();
                    if (code == 0) {
                        callback.call(Response.success(null, 0));
                    } else {
                        Log.d(LOG_TAG, "Fail: Response with entry code = " + code);
                        callback.call(Response.failure(1));
                    }
                }, throwable -> {
                    Log.d(LOG_TAG, "Fail: Response: throwable: " + throwable.getClass().getName());
                    callback.call(Response.failure(1));
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
