package com.example.rz.apptesttool.mvp.model.retrofit.pojo;

import com.example.rz.apptesttool.mvp.model.Criterion;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rz on 4/24/18.
 */

public class CriteriesResponse {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("criterions")
    @Expose
    private List<Criterion> criteries = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<Criterion> getCriteries() {
        return criteries;
    }

    public void setCriteries(List<Criterion> criteries) {
        this.criteries = criteries;
    }
}
