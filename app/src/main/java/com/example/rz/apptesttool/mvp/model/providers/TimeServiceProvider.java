package com.example.rz.apptesttool.mvp.model.providers;

import com.example.rz.apptesttool.TestToolApplication;
import com.example.rz.apptesttool.mvp.model.TimeService;
import com.example.rz.apptesttool.mvp.model.TimeServiceImpl;

/**
 * Created by void on 6/3/18.
 */

public class TimeServiceProvider {

    private static TimeService timeService;

    public static TimeService get() {

        if (timeService == null) {
            timeService = new TimeServiceImpl(TestToolApplication.getBaseUrl(), TestToolApplication.getAppId());
        }

        return timeService;
    }
}
