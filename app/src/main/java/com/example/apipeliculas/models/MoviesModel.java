package com.example.apipeliculas.models;

import android.content.Context;
import android.util.Log;

import com.example.apipeliculas.interfaces.MovieApi;
import com.example.apipeliculas.interfaces.MovieInterface;
import com.example.apipeliculas.request.Service;
import com.example.apipeliculas.response.MovieSearchResponse;
import com.example.apipeliculas.utils.Credenciales;
import com.example.apipeliculas.utils.MovieInternet;

import retrofit2.Call;

public class MoviesModel implements MovieInterface.model {

    MovieInterface.presenter presenter;
    Context context;
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
                        "" + page,
                        "es");

        this.presenter.mostrarPeliculas(responseCall);
    }

    @Override
    public void descargarPeliculasBusqueda(String nombre, int page) {
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieSearchResponse> responseCall;
        responseCall = movieApi
                .searchMovieP(
                        Credenciales.API_KEY,
                        "" + nombre,
                        "" + page,
                        "es");

        this.presenter.mostrarPeliculas(responseCall);
    }

    @Override
    public void descargarPeliculasDiscover() {
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieSearchResponse> responseCall;
        responseCall = movieApi
                .searchMovie1(Credenciales.API_KEY,
                        "es");

        this.presenter.mostrarPeliculas(responseCall);
    }

    @Override
    public void descargarPeliculasPopular() {
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieSearchResponse> responseCall;
        responseCall = movieApi
                .searchMovie2(Credenciales.API_KEY,
                        "es");

        this.presenter.mostrarPeliculas(responseCall);
    }

    @Override
    public void descargarPeliculasTipoPage(int page, int tipoBusqueda) {
            MovieApi movieApi = Service.getMovieApi();
            Call<MovieSearchResponse> responseCall;
            String page1 = String.valueOf(page);

            if (tipoBusqueda == 1) {
                responseCall = movieApi
                        .searchMovie21(Credenciales.API_KEY, page1,
                                "es");
                this.presenter.mostrarPeliculasPopular(responseCall);
            } else if (tipoBusqueda == 2) {
                responseCall = movieApi
                        .searchMovie61(Credenciales.API_KEY, page1,
                                "es");
                this.presenter.mostrarPeliculasPopular(responseCall);
            } else if (tipoBusqueda == 3) {
                Log.e("TAG-ERRO4", "the response ");
                responseCall = movieApi
                        .searchMovie31(Credenciales.API_KEY, page1,
                                "es");
                this.presenter.mostrarPeliculasPopular(responseCall);
            } else if (tipoBusqueda == 4) {
                responseCall = movieApi
                        .searchMovie41(Credenciales.API_KEY, page1,
                                "es");
                this.presenter.mostrarPeliculasPopular(responseCall);
            } else if (tipoBusqueda == 5) {

            } else {
                Log.e("tipe", "the response ");
            }

    }


}
