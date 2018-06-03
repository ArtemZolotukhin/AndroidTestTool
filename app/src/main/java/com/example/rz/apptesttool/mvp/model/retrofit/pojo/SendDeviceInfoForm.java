
package com.example.rz.apptesttool.mvp.model.retrofit.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendDeviceInfoForm {

    @SerializedName("app_id")
    @Expose
    private String appId;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("os")
    @Expose
    private String os;
    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("dpi")
    @Expose
    private String dpi;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    @Override
    public String toString() {
        return "SendDeviceInfoForm{" +
                "appId='" + appId + '\'' +
                ", uuid='" + uuid + '\'' +
                ", os='" + os + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", dpi='" + dpi + '\'' +
                '}';
    }
}
