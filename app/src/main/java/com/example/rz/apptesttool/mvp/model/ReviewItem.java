package com.example.rz.apptesttool.mvp.model;

/**
 * Created by rz on 4/11/18.
 */

public class ReviewItem {

    private int id;

    private int value;

    private String name;

    private int minValue;

    private int maxValue;

    private boolean checked;

    public ReviewItem() {
    }

    public ReviewItem(int id, int value, String name, int minValue, int maxValue, boolean checked) {
        this.id = id;
        this.value = value;
        this.name = name;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.checked = checked;
    }

    public ReviewItem(int id, int value) {
        this.id = id;
        this.value = value;
    }

    public ReviewItem(Integer id, Integer minValue, String name) {
        this(id, minValue);
        this.name = name;
    }

    public ReviewItem(int id, int value, String name, int minValue, int maxValue) {
        this.id = id;
        this.value = value;
        this.name = name;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
