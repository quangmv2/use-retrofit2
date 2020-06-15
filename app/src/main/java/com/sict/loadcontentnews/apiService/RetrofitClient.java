package com.sict.loadcontentnews.apiService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

//
// Project: LoadContentNews
// Created by quangmv
// Time: 19:45
// Date: 2020-02-28.
// Copyright (c) 2020 SICT. All rights reserved.
//
public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClientRetrofit(String baseUrl){

        OkHttpClient mBuildder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request.Builder builder = originalRequest.newBuilder().header("Accept","application/json");
                        Request newRequest = builder.build();
                        return chain.proceed(newRequest);
                    }
                })
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .build();
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(mBuildder).
                addConverterFactory(ScalarsConverterFactory.create()).
                addConverterFactory(GsonConverterFactory.create(gson)).build();
        return retrofit;
    }

}
