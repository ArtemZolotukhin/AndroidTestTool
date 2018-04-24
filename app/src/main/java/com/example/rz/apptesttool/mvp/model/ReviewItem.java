package com.example.rz.apptesttool.mvp.model;

/**
 * Created by rz on 4/11/18.
 */

public class ReviewItem {

    private int id;

    private int value;

    public ReviewItem() {
    }

    public ReviewItem(int id, int value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public ReviewItem setId(int id) {
        this.id = id;
        return this;
    }

    public int getValue() {
        return value;
    }

    public ReviewItem setValue(int value) {
        this.value = value;
        return this;
    }
}
