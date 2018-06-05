package com.example.rz.apptesttool.mvp.model.providers;

import com.example.rz.apptesttool.TestToolApplication;
import com.example.rz.apptesttool.mvp.model.TouchService;
import com.example.rz.apptesttool.mvp.model.TouchServiceImpl;
import com.example.rz.apptesttool.mvp.model.retrofit.TouchServ;

/**
 * Created by void on 6/3/18.
 */

public class TouchServiceProvider {

    private static TouchService touchService;

    public static TouchService get() {
        if (touchService == null) {
            touchService = new TouchServiceImpl(TestToolApplication.getBaseUrl(), TestToolApplication.getAppId());
        }
        return touchService;
    }

}
