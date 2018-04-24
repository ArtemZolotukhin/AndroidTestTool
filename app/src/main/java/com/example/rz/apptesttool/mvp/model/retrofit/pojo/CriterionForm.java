package com.example.rz.apptesttool.mvp.model.retrofit.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rz on 4/24/18.
 */

public class CriterionForm {
    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("value")
    @Expose
    private Integer value;

    public CriterionForm() {
    }

    public CriterionForm(Long id, Integer value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public CriterionForm setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getValue() {
        return value;
    }

    public CriterionForm setValue(Integer value) {
        this.value = value;
        return this;
    }
}
