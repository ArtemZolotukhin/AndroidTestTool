package com.example.rz.apptesttool.mvp.model.retrofit;


/**
 * Created by rz on 4/24/18.
 */

public class ReviewResponse {

    private Integer code;

    public ReviewResponse(Integer code) {
        this.code = code;
    }

    public ReviewResponse() {
    }

    public Integer getCode() {
        return code;
    }

    public ReviewResponse setCode(Integer code) {
        this.code = code;
        return this;
    }
}
