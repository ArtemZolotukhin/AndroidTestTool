package com.example.rz.apptesttool.mvp.model;

/**
 * Created by void on 6/3/18.
 */

public interface DeviceIdService {

    /**
     *
     * @param callback contains Response<String, Integer> where String value is <bold>device_id</bold>,
     *                 and Integer value is <bold>error code</bold>
     */
    void getDeviceId(Callback<Response<String, Integer>> callback);

}
