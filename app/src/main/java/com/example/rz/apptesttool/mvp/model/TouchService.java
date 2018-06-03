package com.example.rz.apptesttool.mvp.model;

import java.util.List;

/**
 * Created by void on 6/3/18.
 */

public interface TouchService {

    void send(List<TouchInfo> touches, Callback<Response<Void, Integer>> callback);

}
