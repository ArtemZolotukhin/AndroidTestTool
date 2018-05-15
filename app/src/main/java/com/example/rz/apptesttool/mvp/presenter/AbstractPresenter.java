package com.example.rz.apptesttool.mvp.presenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rz on 5/15/18.
 */

public abstract class AbstractPresenter {

    private Map<String, Object> uglyStorage = new HashMap<>();

    public void putToUglyStorage(String key, Object value) {
        uglyStorage.put(key, value);
    }

    public Object getFromUglyStorage(String key) {
        return uglyStorage.get(key);
    }
}
