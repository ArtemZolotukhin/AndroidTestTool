package com.example.rz.apptesttool.mvp.model.providers;

import com.example.rz.apptesttool.TestToolApplication;
import com.example.rz.apptesttool.mvp.model.TimeService;
import com.example.rz.apptesttool.mvp.model.TimeServiceImpl;

/**
 * Created by void on 6/3/18.
 */

public class TimeServiceProvider {

    public static TimeService get() {
        return new TimeServiceImpl(TestToolApplication.getBaseUrl(), TestToolApplication.getAppId());
    }
}
