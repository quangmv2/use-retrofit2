package com.sict.loadcontentnews.activity.news;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sict.loadcontentnews.R;
import com.sict.loadcontentnews.apiService.APIUtils;
import com.sict.loadcontentnews.apiService.DataClient;
import com.sict.loadcontentnews.apiService.model.DataDemo;
import com.sict.loadcontentnews.apiService.model.Post;
import com.sict.loadcontentnews.apiService.model.ResPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//
// Project: LoadContentNews
// Created by quangmv
// Time: 22:49
// Date: 2020-02-29.
// Copyright (c) 2020 SICT. All rights reserved.
//
public class NewsViewModel extends AndroidViewModel {

    private MutableLiveData<List<Post>> posts;
    public int page_now = 0;
    public int total_page = -1;
    public int category = 0;
    public int total = -1;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        posts = new MutableLiveData<>();

    }


    public void getPostsAPI(int category, int page, final ProgressBar progressBar, final List<Post> postsp){
        if (progressBar!= null) progressBar.setVisibility(View.VISIBLE);
        DataClient dataClient = APIUtils.getData();
        Call<ResPost> callback = dataClient.getPostsCategory(category, page);
        this.page_now = page++;
        this.category = category;
        callback.enqueue(new Callback<ResPost>() {
            @Override
            public void onResponse(Call<ResPost> call, Response<ResPost> response) {
                System.out.println(response + "1");
                total_page = response.body().getLastPage();
                total = response.body().getTotal();
                List<Post> data = response.body().getData();
                if (page_now != total_page) data.add(null);
                if (page_now == 1) setPosts(data);
                 else addPost(data, postsp);
                if (progressBar!= null) progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResPost> call, Throwable t) {
                System.out.println(t.getMessage());
                t.printStackTrace();
                if (progressBar!= null) progressBar.setVisibility(View.GONE);
            }
        });
        System.out.println(page);
    }

    public LiveData<List<Post>> getPosts() {
        return posts;
    }

    public void addPost(List<Post> posts, List<Post> postsp){
        for (Post post: posts){
            postsp.add(post);
        }
        setPosts(postsp);

    }

    public void setPosts(List<Post> posts) {
        this.posts.setValue(posts);
    }

}
