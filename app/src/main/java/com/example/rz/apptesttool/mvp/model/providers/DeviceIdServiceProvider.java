package com.example.rz.apptesttool.mvp.model.providers;

import com.example.rz.apptesttool.TestToolApplication;
import com.example.rz.apptesttool.mvp.model.DeviceIdService;
import com.example.rz.apptesttool.mvp.model.DeviceIdServiceImpl;

/**
 * Created by void on 6/3/18.
 */

public class DeviceIdServiceProvider {

    private static DeviceIdService deviceIdService;

    public static DeviceIdService get() {
        if (deviceIdService == null) {
            deviceIdService = new DeviceIdServiceImpl(TestToolApplication.getBaseUrl(),
                    TestToolApplication.getAppId(), ContextProvider.get());
        }
        return deviceIdService;
    }

}
