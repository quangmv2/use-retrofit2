package com.sict.loadcontentnews.activity.detail;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sict.loadcontentnews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailActivity extends Fragment {

    private DetailViewModel detailViewModel;

    public DetailActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        return root;
    }



}
