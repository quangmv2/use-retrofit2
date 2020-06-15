package com.sict.loadcontentnews.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sict.loadcontentnews.R;
import com.sict.loadcontentnews.activity.news.NewsViewModel;
import com.sict.loadcontentnews.apiService.model.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

//
// Project: LoadContentNews
// Created by quangmv
// Time: 20:31
// Date: 2020-03-01.
// Copyright (c) 2020 SICT. All rights reserved.
//
public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM=0,VIEW_TYPE_LOADING=1;
    private Context context;
    private List<Post> posts = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private int totalItemCount, lastVisibleItem;
    private int visble = 5;
    private boolean loaded = true;


    public PostAdapter(Context context, RecyclerView recyclerView, Activity activity, final NewsViewModel viewModel) {
        this.context = context;
        final LinearLayoutManager linearLayout = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayout.getItemCount();
                lastVisibleItem = linearLayout.findLastVisibleItemPosition();
                if ( !(loaded) && viewModel.page_now < viewModel.total_page && totalItemCount <= (lastVisibleItem+2)) {
                    if (posts.size() != 0 && posts.get(posts.size()-1) == null) {
                        posts.remove(posts.size()-1);
                        PostAdapter.this.notifyItemRemoved(posts.size()-1);
                        viewModel.getPostsAPI(viewModel.category, ++viewModel.page_now, null, posts);
                    }
                    loaded = true;
                }
            }
        });
    }

    public void addPosts(List<Post> postss){
        for (Post postt: postss){
            this.posts.add(postt);
        }
        notifyDataSetChanged();
        setLoaded();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
        setLoaded();
    }

    public void setLoaded() {
        this.loaded = false;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM){
            View view = LayoutInflater.from(context).inflate(R.layout.item_list_post, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        } else if (viewType == VIEW_TYPE_LOADING){
            View view = LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            ViewHolder vholder = (ViewHolder) holder;
            Post post = posts.get(position);
//            vholder.imgAvt.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in__image));
//            vholder.linearLayout.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in_content));
            vholder.txtTitle.setText(post.getTitle());
            vholder.txtSumary.setText(post.getSummary());
            Picasso.get().load("http://ctsv.cit.udn.vn/uploads/images/posts/" + post.getImage())
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
                    .into(vholder.imgAvt);
        }
        else if (holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public int getItemViewType(int position) {
        return posts.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }

    public interface OnItemClickListener{
        void onClickItem(View view, Post post);
    }

    public void setOnItemClick(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder
    {

        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar)itemView.findViewById(R.id.progressBar_loading);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtTitle;
        private TextView txtSumary;
        private ImageView imgAvt;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtSumary = itemView.findViewById(R.id.txtSumary);
            imgAvt = itemView.findViewById(R.id.img);
            linearLayout = itemView.findViewById(R.id.content);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            PostAdapter.this.onItemClickListener.onClickItem(v, posts.get(getLayoutPosition()));
        }
    }

}
