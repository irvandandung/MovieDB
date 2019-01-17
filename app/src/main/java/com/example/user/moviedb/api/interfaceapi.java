package com.example.user.moviedb.api;

import com.example.user.moviedb.model.modelMovie;
import com.example.user.moviedb.model.modelTrailer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface interfaceapi {
    @GET("movie/popular")
    Call<modelMovie> getPopularMovies(@Query("api_key") String apikey);

    @GET("movie/top_rated")
    Call<modelMovie> getTopRatedMovies(@Query("api_key") String apikey);

    @GET("movie/now_playing")
    Call<modelMovie> getNowPlaying(@Query("api_key") String apikey);

    @GET("movie/{movie_id}/videos")
    Call<modelTrailer>getTrailelMovie(@Path("movie_id") int id, @Query("api_key") String apikey);
}
