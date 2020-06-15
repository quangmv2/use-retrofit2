package com.sict.loadcontentnews.apiService.model;

import com.google.gson.annotations.SerializedName;

//
// Project: LoadContentNews
// Created by quangmv
// Time: 19:54
// Date: 2020-03-01.
// Copyright (c) 2020 SICT. All rights reserved.
//
public class DataDemo {

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
