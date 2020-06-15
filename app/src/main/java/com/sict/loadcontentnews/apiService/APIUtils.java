package com.sict.loadcontentnews.apiService;

//
// Project: LoadContentNews
// Created by quangmv
// Time: 19:56
// Date: 2020-02-28.
// Copyright (c) 2020 SICT. All rights reserved.
//
public class APIUtils {

    public static DataClient getData(){
        return RetrofitClient.getClientRetrofit(ConfigAPI.BASE_URL).create(DataClient.class);
    }

}
