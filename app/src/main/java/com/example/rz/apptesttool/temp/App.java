package com.example.rz.apptesttool.temp;

import com.example.rz.apptesttool.TestToolApplication;

/**
 * Created by rz on 20.03.18.
 */

public class App extends TestToolApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        setTestMode(true);
        setBaseUrl("http://localhost:8080");
    }
}
