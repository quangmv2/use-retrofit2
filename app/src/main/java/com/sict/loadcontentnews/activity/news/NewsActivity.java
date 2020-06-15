package com.sict.loadcontentnews.activity.news;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.sict.loadcontentnews.R;
import com.sict.loadcontentnews.adapter.PostAdapter;
import com.sict.loadcontentnews.apiService.APIUtils;
import com.sict.loadcontentnews.apiService.DataClient;
import com.sict.loadcontentnews.apiService.model.Post;
import com.sict.loadcontentnews.apiService.model.ResPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsActivity extends Fragment implements PostAdapter.OnItemClickListener {

    private NewsViewModel newsViewModel;
    private List<Post> posts;
    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private ProgressBar progressBar;

    public NewsActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.list_post);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new PostAdapter(view.getContext(), recyclerView, getActivity(), newsViewModel);
        adapter.setOnItemClick(this);
        recyclerView.setAdapter(adapter);

        newsViewModel.getPosts().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> postss) {
                adapter.setPosts(postss);
            }
        });

        newsViewModel.getPostsAPI(56, 1, progressBar, posts);

    }

    @Override
    public void onClickItem(View view, Post post) {

    }
}
