package com.example.user.moviedb.api;

import com.example.user.moviedb.model.modelMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class interfaceapi {
    @GET("movie/popular")
    public Call<modelMovie>getPopularMovies(@Query("api_key") String apikey) {
        return null;
    }
}
