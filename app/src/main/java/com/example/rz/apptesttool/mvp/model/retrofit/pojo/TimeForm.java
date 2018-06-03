package com.example.rz.apptesttool.mvp.model.retrofit.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeForm {

    @SerializedName("app_id")
    @Expose
    private String appId;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("time_seconds")
    @Expose
    private String timeSeconds;

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTimeSeconds() {
        return timeSeconds;
    }

    public void setTimeSeconds(String timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

}