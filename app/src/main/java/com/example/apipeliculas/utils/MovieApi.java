package com.example.apipeliculas.utils;

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


    @GET("3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key
    );


}
