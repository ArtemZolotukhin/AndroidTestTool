package com.example.rz.apptesttool.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by rz on 4/10/18.
 */

public class TouchInfo extends RealmObject {


    @SerializedName("x")
    @Expose
    private float x;

    @SerializedName("y")
    @Expose
    private float y;

    @SerializedName("display_name")
    @Expose
    private String activity;

    public TouchInfo() {
    }

    public TouchInfo(float x, float y, String activity) {
        this.x = x;
        this.y = y;
        this.activity = activity;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TouchInfo touchInfo = (TouchInfo) o;

        if (Float.compare(touchInfo.x, x) != 0) return false;
        if (Float.compare(touchInfo.y, y) != 0) return false;
        return activity != null ? activity.equals(touchInfo.activity) : touchInfo.activity == null;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (activity != null ? activity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TouchInfo{" +
                "x=" + x +
                ", y=" + y +
                ", activity='" + activity + '\'' +
                '}';
    }
}
