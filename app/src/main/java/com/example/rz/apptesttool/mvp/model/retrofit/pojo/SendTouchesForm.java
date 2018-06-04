package com.example.rz.apptesttool.mvp.model.retrofit.pojo;

import com.example.rz.apptesttool.mvp.model.TouchInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by void on 6/3/18.
 */

public class SendTouchesForm {

    @SerializedName("app_id")
    @Expose
    private String appId;

    @SerializedName("device_id")
    @Expose
    private String deviceId;

    @SerializedName("touch_map")
    @Expose
    private List<TouchInfo> touchMap;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public List<TouchInfo> getTouchMap() {
        return touchMap;
    }

    public void setTouchMap(List<TouchInfo> touchMap) {
        this.touchMap = touchMap;
    }

    @Override
    public String toString() {
        return "SendTouchesForm{" +
                "appId='" + appId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", touchMap=" + touchMap +
                '}';
    }
}
