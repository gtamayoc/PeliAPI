package com.example.apipeliculas.models;

import com.example.apipeliculas.interfaces.MovieApi;
import com.example.apipeliculas.interfaces.MovieInterface;
import com.example.apipeliculas.request.Service;
import com.example.apipeliculas.response.MovieSearchResponse;
import com.example.apipeliculas.utils.Credenciales;

import retrofit2.Call;

public class MoviesModel implements MovieInterface.model {

    MovieInterface.presenter presenter;

    public MoviesModel(MovieInterface.presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void descargarPeliculas(String nombre, String page) {
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieSearchResponse> responseCall;
        responseCall = movieApi
                .searchMovie(
                        Credenciales.API_KEY,
                        "" + nombre,
                        "" + page);

        this.presenter.mostrarPeliculas(responseCall);
    }
    
}