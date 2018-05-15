package com.example.rz.apptesttool.mvp.model;

import io.realm.RealmObject;

/**
 * Created by rz on 4/10/18.
 */

public class TimeInfo extends RealmObject {

    private String activity;

    /**
     * unix format
     */
    private long time;

    public TimeInfo() {
    }

    public TimeInfo(String activity, long time) {
        this.activity = activity;
        this.time = time;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeInfo timeInfo = (TimeInfo) o;

        if (time != timeInfo.time) return false;
        return activity != null ? activity.equals(timeInfo.activity) : timeInfo.activity == null;
    }

    @Override
    public int hashCode() {
        int result = activity != null ? activity.hashCode() : 0;
        result = 31 * result + (int) (time ^ (time >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "TimeInfo{" +
                "activity='" + activity + '\'' +
                ", time=" + time +
                '}';
    }
}
