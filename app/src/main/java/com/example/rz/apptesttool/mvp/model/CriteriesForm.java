package com.example.rz.apptesttool.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rz on 4/24/18.
 */

public class CriteriesForm {

    @SerializedName("app_id")
    @Expose
    private String appId;

    public CriteriesForm() {
    }

    public CriteriesForm(String appId) {
        this.appId = appId;
    }

    public String getAppId() {
        return appId;
    }

    public CriteriesForm setAppId(String appId) {
        this.appId = appId;
        return this;
    }
}
