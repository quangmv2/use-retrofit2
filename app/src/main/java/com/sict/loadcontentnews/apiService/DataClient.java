package com.sict.loadcontentnews.apiService;

import com.sict.loadcontentnews.apiService.model.DataDemo;
import com.sict.loadcontentnews.apiService.model.Post;
import com.sict.loadcontentnews.apiService.model.ResPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

//
// Project: LoadContentNews
// Created by quangmv
// Time: 19:57
// Date: 2020-02-28.
// Copyright (c) 2020 SICT. All rights reserved.
//
public interface DataClient {

    @GET("list-post")
    Call<ResPost> getPostsCategory(@Query("category") int category, @Query("page") int page);

}
