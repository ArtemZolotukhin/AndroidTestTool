package com.example.rz.apptesttool.mvp.model.providers;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by void on 6/3/18.
 */

public class RetrofitProvider {

    private static Retrofit retrofit;

    public static Retrofit get(String baseUrl) {
       if (retrofit == null) {
           retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       }
       return retrofit;
    }

    private RetrofitProvider() {
    }

}
