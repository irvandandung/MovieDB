package com.example.user.moviedb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.moviedb.adapter.adapterMovie;
import com.example.user.moviedb.api.client;
import com.example.user.moviedb.api.interfaceapi;
import com.example.user.moviedb.model.modelMovie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OneFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<modelMovie> movieList = new ArrayList<com.example.user.moviedb.model.modelMovie>();
    private RecyclerView recyclerView;
    private adapterMovie mAdapter;
    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_one, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view1);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new adapterMovie(movieList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

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

            }

            @Override
            public void onFailure(Call<modelMovie> call, Throwable t) {
                Log.d("Error", t.getMessage());
                Toast.makeText(getActivity(), "Error Fetching Data!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
