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

    @Override
    public void descargarPeliculasBusqueda(String nombre, String page) {
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieSearchResponse> responseCall;
        responseCall = movieApi
                .searchMovie(
                        Credenciales.API_KEY,
                        "" + nombre,
                        "" + page);

        this.presenter.mostrarPeliculas(responseCall);
    }

    @Override
    public void descargarPeliculasDiscover() {
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieSearchResponse> responseCall;
        responseCall = movieApi
                .searchMovie1(Credenciales.API_KEY);

        this.presenter.mostrarPeliculas(responseCall);
    }

    @Override
    public void descargarPeliculasPopular() {
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieSearchResponse> responseCall;
        responseCall = movieApi
                .searchMovie2(Credenciales.API_KEY);

        this.presenter.mostrarPeliculas(responseCall);
    }

    @Override
    public void descargarPeliculasTop() {
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieSearchResponse> responseCall;
        responseCall = movieApi
                .searchMovie3(Credenciales.API_KEY);

        this.presenter.mostrarPeliculas(responseCall);
    }

    @Override
    public void descargarPeliculasUltimos() {
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieSearchResponse> responseCall;
        responseCall = movieApi
                .searchMovie6(Credenciales.API_KEY);
        this.presenter.mostrarPeliculas(responseCall);
    }


    @Override
    public void descargarPeliculasProximos() {
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieSearchResponse> responseCall;
        responseCall = movieApi
                .searchMovie4(Credenciales.API_KEY);

        this.presenter.mostrarPeliculas(responseCall);
    }


    @Override
    public void descargarPeliculasId(int id) {
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieModel> responseCall = movieApi.getMovie(id, Credenciales.API_KEY);
        this.presenter.mostrarPeliculasId(responseCall);
    }

}
