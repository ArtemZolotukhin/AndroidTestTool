package com.example.rz.apptesttool.mvp.model.retrofit.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by void on 6/3/18.
 */

public class SendDeviceInfoResponse {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("device_id")
    @Expose
    private String deviceId;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
