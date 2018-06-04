package com.example.rz.apptesttool.mvp.model;

import android.util.Log;

import com.example.rz.apptesttool.mvp.model.providers.DeviceIdServiceProvider;
import com.example.rz.apptesttool.mvp.model.providers.RetrofitProvider;
import com.example.rz.apptesttool.mvp.model.retrofit.TimeServ;
import com.example.rz.apptesttool.mvp.model.retrofit.pojo.TimeForm;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by void on 6/3/18.
 */

public class TimeServiceImpl implements TimeService {

    public static final String LOG_TAG = "TimeService";

    private String baseUrl;

    private String appId;

    private TimeServ timeServ;

    private DeviceIdService deviceIdService;

    public TimeServiceImpl(String baseUrl, String appId) {
        this.baseUrl = baseUrl;
        this.appId = appId;
        timeServ = RetrofitProvider.get(baseUrl).create(TimeServ.class);
        deviceIdService = DeviceIdServiceProvider.get();
    }

    @Override
    public void send(TimeInfo timeInfo, Callback<Response<Void, Integer>> callback) {

        deviceIdService.getDeviceId(stringIntegerResponse -> {
            if (stringIntegerResponse.isSuccessfull()) {
                if (stringIntegerResponse.getError() == 0 || stringIntegerResponse.getError() == null) {
                    String deviceId = stringIntegerResponse.getValue();

                    send(timeInfo, callback, deviceId);

                } else {
                    //TODO normal error codes
                    callback.call(Response.failure(1));
                }
            } else {
                callback.call(Response.failure(1));
            }

        });

    }

    private void send(TimeInfo timeInfo, Callback<Response<Void, Integer>> callback, String deviceId) {
        timeServ.send(getForm(timeInfo, deviceId))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sendTimeResponse -> {
                    int code = sendTimeResponse.getCode();
                    if (code == 0) {
                        callback.call(Response.success(null, 0));
                    } else {
                        //TODO normal error codes
                        Log.d(LOG_TAG, "Fail: Response with entry code = " + code);
                        callback.call(Response.failure(1));
                    }
                }, throwable -> {
                    //TODO normal error codes
                    Log.d(LOG_TAG, "Fail: Response: throwable: " + throwable.getClass().getName()
                            + " " + throwable.getMessage());
                    callback.call(Response.failure(1));
                });
    }

    private TimeForm getForm(TimeInfo timeInfo, String deviceId) {
        TimeForm timeForm = new TimeForm();
        timeForm.setAppId(appId);
        timeForm.setDeviceId(deviceId);
        timeForm.setDisplayName(timeInfo.getActivity());
        timeForm.setTimeSeconds(String.valueOf(timeInfo.getTime() / 1000L));
        return timeForm;
    }
}
