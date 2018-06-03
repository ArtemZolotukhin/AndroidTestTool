package com.example.rz.apptesttool.mvp.model.providers;

import android.content.Context;

import com.example.rz.apptesttool.TestToolApplication;

/**
 * Created by void on 6/3/18.
 */

public class ContextProvider {

    public static Context get(){
        return TestToolApplication.getContext();
    }

}
