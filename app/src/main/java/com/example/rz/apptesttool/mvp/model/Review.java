package com.example.rz.apptesttool.mvp.model;

import java.util.Set;

/**
 * Created by rz on 4/11/18.
 */

public class Review {

    private String displayName;

    private Set<ReviewItem> reviewItemSet;

    private String message;

    public Review(Set<ReviewItem> reviewItemSet, String message) {
        this.reviewItemSet = reviewItemSet;
        this.message = message;
    }

    public Review(String displayName, Set<ReviewItem> reviewItemSet, String message) {
        this.displayName = displayName;
        this.reviewItemSet = reviewItemSet;
        this.message = message;
    }

    public Review() {
    }

    public String getDisplayName() {
        return displayName;
    }

    public Review setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public Set<ReviewItem> getReviewItemSet() {
        return reviewItemSet;
    }

    public Review setReviewItemSet(Set<ReviewItem> reviewItemSet) {
        this.reviewItemSet = reviewItemSet;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Review setMessage(String message) {
        this.message = message;
        return this;
    }
}
