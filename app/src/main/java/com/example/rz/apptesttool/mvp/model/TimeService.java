package com.example.rz.apptesttool.mvp.model;

/**
 * Created by void on 6/3/18.
 */

public interface TimeService {

    void send(TimeInfo timeInfo, Callback<Response<Void, Integer>> callback);

}
