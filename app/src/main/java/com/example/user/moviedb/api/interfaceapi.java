package com.example.user.moviedb.api;

import com.example.user.moviedb.model.modelMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface interfaceapi {
    @GET("movie/popular")
    Call<modelMovie> getPopularMovies(@Query("api_key") String apikey);
}
