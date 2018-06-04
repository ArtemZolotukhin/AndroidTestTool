package com.example.rz.apptesttool.mvp.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.rz.apptesttool.mvp.model.providers.RetrofitProvider;
import com.example.rz.apptesttool.mvp.model.retrofit.DeviceServ;
import com.example.rz.apptesttool.mvp.model.retrofit.pojo.SendDeviceInfoForm;

import java.util.UUID;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by void on 6/3/18.
 */

public class DeviceIdServiceImpl implements DeviceIdService {

    public static final String LOG_TAG = "DeviceIdService";

    public static final String PREFERENCES = "device_info";
    public static final String PARAM_DEVICE_UUID = "device_uuid";

    private Retrofit retrofit;

    private String appId;

    private String cachedDeviceId;

    private Context context;

    private DeviceServ deviceServ;

    public DeviceIdServiceImpl(String baseUrl, String appId, Context context) {
        this.retrofit = RetrofitProvider.get(baseUrl);
        this.deviceServ = this.retrofit.create(DeviceServ.class);
        this.appId = appId;
        this.context = context;
        this.cachedDeviceId = null;
    }

    @Override
    public void getDeviceId(Callback<Response<String, Integer>> callback) {
        if (this.cachedDeviceId == null) {
            SendDeviceInfoForm form = getSendDeviceInfoForm();
            Log.d(LOG_TAG, "info: form to send: " + form.toString());
            deviceServ.deviceIndoSend(form)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(sendDeviceInfoResponse -> {
                        int code = sendDeviceInfoResponse.getCode();
                        if (code == 0) {
                            this.cachedDeviceId = sendDeviceInfoResponse.getDeviceId();
                            callback.call(Response.success(this.cachedDeviceId, 0));
                        } else {
                            //TODO normall error code
                            Log.d(LOG_TAG, "Fail: Response with entry code = " + code);
                            callback.call(Response.failure(1));
                        }
                    }, throwable -> {
                        Log.d(LOG_TAG, "Fail: Response: throwable: " + throwable.getClass().getName()
                                + " " + throwable.getMessage());
                        //TODO normall error code
                        callback.call(Response.failure(1));
                    });
        } else {
            callback.call(Response.success(cachedDeviceId, 0));
        }
    }

    public String getUuid() {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        String uuid = sp.getString(PARAM_DEVICE_UUID, null);
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
            sp.edit().putString(PARAM_DEVICE_UUID, uuid).apply();
        }
        return uuid;
    }

    public SendDeviceInfoForm getSendDeviceInfoForm() {
        SendDeviceInfoForm f = new SendDeviceInfoForm();
        String version = "Android " + Build.VERSION.RELEASE;
        f.setOs(version);
        f.setAppId(appId);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        f.setDpi(String.valueOf(dm.densityDpi));
        f.setWidth(String.valueOf(dm.widthPixels));
        f.setHeight(String.valueOf(dm.heightPixels));
        f.setUuid(getUuid());
        return f;
    }
}
