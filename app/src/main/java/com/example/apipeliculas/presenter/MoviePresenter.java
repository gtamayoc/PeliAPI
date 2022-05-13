package com.example.apipeliculas.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.apipeliculas.interfaces.MovieInterface;
import com.example.apipeliculas.models.MovieModel;
import com.example.apipeliculas.models.MoviesModel;
import com.example.apipeliculas.response.MovieSearchResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter implements MovieInterface.presenter {

    MovieInterface.model model;
    MovieInterface.view view;


    public MoviePresenter(MovieInterface.view view) {
        this.model = new MoviesModel(this);
        this.view = view;
    }


    @Override
    public void buscarPeliculas(String nombre, int page) {
        this.model.descargarPeliculasBusqueda(nombre, page);
    }

    @Override
    public void buscarPeliculasPopular() {
        this.model.descargarPeliculasPopular();
    }

    @Override
    public void buscarPeliculasPopularPage(int page, int tipoBusqueda) {
        this.model.descargarPeliculasTipoPage(page, tipoBusqueda);
    }

    @Override
    public void mostrarPeliculasPopular(@NonNull Call<MovieSearchResponse> responseCall) {
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {

                if (response.code() == 200) {
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    try {
                        view.mostrarPeliculas(movies);
                        view.configureRecyclerViewMostrarPeliculasPopular(movies);
                    } catch (Exception e) {

                    }
                } else {
                    String respuesta = "hay un error en la descarga del recurso : " + response.code();
                    view.errorCarga(respuesta);
                }

            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                String respuesta = "hay un error en la descarga del recurso : " + t.getMessage();
                view.errorCarga(respuesta);
                Log.e("TAG2-ERRO4", "the response " + t.getMessage());
            }
        });
    }


    @Override
    public void mostrarPeliculas(@NonNull Call<MovieSearchResponse> responseCall) {
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {

                if (response.code() == 200) {
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    try {
                        view.mostrarPeliculas(movies);
                        view.configureRecyclerViewMostrarPeliculas(movies);
                    } catch (Exception e) {

                    }
                } else {
                    String respuesta = "hay un error en la descarga del recurso : " + response.code();
                    view.errorCarga(respuesta);
                }

            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                Log.e("TAG2-ERRO2", "the response " + t.getMessage());
            }
        });
    }


    @Override
    public void mostrarPeliculasId(@NonNull Call<MovieModel> responseCall) {

        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.code() == 200) {
                    MovieModel movie = response.body();
                    try {
                        view.mostrarPeliculasId(movie);
                    } catch (Exception e) {

                    }
                } else {
                    String respuesta = "hay un error en la descarga del recurso : " + response.code();
                    view.errorCarga(respuesta);
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });

    }

}
