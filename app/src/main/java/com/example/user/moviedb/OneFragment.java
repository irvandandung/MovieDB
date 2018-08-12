package com.example.user.moviedb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.moviedb.adapter.adapterMovie;
import com.example.user.moviedb.api.client;
import com.example.user.moviedb.api.interfaceapi;
import com.example.user.moviedb.model.Result;
import com.example.user.moviedb.model.modelMovie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OneFragment extends Fragment{
    private RecyclerView mRecyclerView;
    ProgressBar progressBar;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Result> movieList = new ArrayList<Result>();
    private adapterMovie mAdapter;
    String whoops;
    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_one, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view1);
        whoops = String.format(getResources().getString(R.string.whoops));


//        mRecyclerView.setHasFixedSize(true);
//
//
//        mLayoutManager = new LinearLayoutManager(getContext());
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//
//        mRecyclerView.setAdapter(mAdapter);

        loadJson();
        return v;

    }

    private void loadJson() {
        client client = new client();
        interfaceapi interfaceapi = client.getClient().create(com.example.user.moviedb.api.interfaceapi.class);
        Call <modelMovie> call = interfaceapi.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
        call.enqueue(new Callback<modelMovie>() {
            @Override
            public void onResponse(Call<modelMovie> call, Response<modelMovie> response) {
                progressBar.setVisibility(View.GONE);
                movieList = response.body().getResults();
                mAdapter = new adapterMovie(getContext(),movieList);
                mRecyclerView.setAdapter(mAdapter);
                mLayoutManager = new LinearLayoutManager(getContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<modelMovie> call, Throwable t) {
                Toast.makeText(getContext(), whoops, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
