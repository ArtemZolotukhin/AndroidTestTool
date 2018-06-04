package com.example.rz.apptesttool.mvp.model.retrofit.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Set;

/**
 * Created by rz on 20.03.18.
 */

public class ReviewForm {

    @SerializedName("app_id")
    @Expose
    private String appId;

    @SerializedName("device_id")
    @Expose
    private String deviceId;

    @SerializedName("display_name")
    @Expose
    private String displayName;

    @SerializedName("review")
    @Expose
    private String review;


    @SerializedName("criterions")
    @Expose
    private Set<CriterionForm> criterions;

    public ReviewForm() {
    }

    public ReviewForm(String appId, String deviceId, String displayName, String review, Set<CriterionForm> criterions) {
        this.appId = appId;
        this.deviceId = deviceId;
        this.displayName = displayName;
        this.review = review;
        this.criterions = criterions;
    }

    public String getAppId() {
        return appId;
    }

    public ReviewForm setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ReviewForm setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public String getReview() {
        return review;
    }

    public ReviewForm setReview(String review) {
        this.review = review;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Set<CriterionForm> getCriterions() {
        return criterions;
    }

    public ReviewForm setCriterions(Set<CriterionForm> criterions) {
        this.criterions = criterions;
        return this;
    }

    @Override
    public String toString() {
        return "ReviewForm{" +
                "appId='" + appId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", displayName='" + displayName + '\'' +
                ", review='" + review + '\'' +
                ", criterions=" + criterions +
                '}';
    }
}
