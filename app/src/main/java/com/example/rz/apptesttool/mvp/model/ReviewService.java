package com.example.rz.apptesttool.mvp.model;

import java.util.Set;

/**
 * Created by rz on 4/11/18.
 */

public interface ReviewService {

    void sendReview(Review review, Callback<Response<Void, Integer>> callback);

    void getCriteries(Callback<Response<Set<Criterion>, Integer>> callback);

}
