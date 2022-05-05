package com.example.apipeliculas.interfaces;

import com.example.apipeliculas.models.MovieModel;
import com.example.apipeliculas.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    //busqueda de la pelicula
    //https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher
    @GET("3/search/movie")
    Call<MovieSearchResponse> searchMovie(@Query("api_key") String key , @Query("query") String query , @Query("page") String page);

    @GET("3/discover/movie")
    Call<MovieSearchResponse> searchMovie1(@Query("api_key") String key);

    @GET("3/movie/popular")
    Call<MovieSearchResponse> searchMovie2(@Query("api_key") String key);

    @GET("3/movie/top_rated")
    Call<MovieSearchResponse> searchMovie3(@Query("api_key") String key);

    @GET("3/movie/upcoming")
    Call<MovieSearchResponse> searchMovie4(@Query("api_key") String key);

    @GET("3/movie/latest")
    Call<MovieSearchResponse> searchMovie5(@Query("api_key") String key);

    @GET("3/movie/now_playing")
    Call<MovieSearchResponse> searchMovie6(@Query("api_key") String key);


    @GET("3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key
    );


}
