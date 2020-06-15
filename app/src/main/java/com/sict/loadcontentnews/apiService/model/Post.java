package com.sict.loadcontentnews.apiService.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//
// Project: LoadContentNews
// Created by quangmv
// Time: 18:17
// Date: 2020-03-01.
// Copyright (c) 2020 SICT. All rights reserved.
//
public class Post {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("unsigned_title")
    @Expose
    private String unsignedTitle;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("view")
    @Expose
    private Integer view;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("name_author")
    @Expose
    private String nameAuthor;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("highlight")
    @Expose
    private Integer highlight;
    @SerializedName("active")
    @Expose
    private Integer active;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnsignedTitle() {
        return unsignedTitle;
    }

    public void setUnsignedTitle(String unsignedTitle) {
        this.unsignedTitle = unsignedTitle;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getHighlight() {
        return highlight;
    }

    public void setHighlight(Integer highlight) {
        this.highlight = highlight;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


}
