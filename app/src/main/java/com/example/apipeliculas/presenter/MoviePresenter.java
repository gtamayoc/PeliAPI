package com.example.apipeliculas.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.apipeliculas.interfaces.MovieInterface;
import com.example.apipeliculas.models.MovieModel;
import com.example.apipeliculas.models.MoviesModel;
import com.example.apipeliculas.response.MovieSearchResponse;

import java.io.IOException;
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
    public void obtenerPeliculas(String nombre, String page) {
        this.model.descargarPeliculas(nombre, page);
    }

    @Override
    public void buscarPeliculas(String nombre, String page) {
        this.model.descargarPeliculasBusqueda(nombre, page);
    }

    @Override
    public void buscarPeliculasDiscover() {
        this.model.descargarPeliculasDiscover();
    }

    @Override
    public void buscarPeliculasPopular() {
        this.model.descargarPeliculasPopular();
    }

    @Override
    public void buscarPeliculasTop() {
        this.model.descargarPeliculasTop();
    }

    @Override
    public void buscarPeliculasProximos() {
        this.model.descargarPeliculasProximos();
    }

    @Override
    public void buscarPeliculasUltimos() {
        this.model.descargarPeliculasUltimos();
    }

    @Override
    public void obtenerPeliculasId(int id) {
        this.model.descargarPeliculasId(id);
    }

    @Override
    public void mostrarPeliculas(@NonNull Call<MovieSearchResponse> responseCall) {
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {

                if (response.code() == 200) {
                    Log.e("TAG-ERRO", "the response " + response.body().toString());
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    try {
                        view.mostrarPeliculas(movies);
                        view.configureRecyclerView1(movies);
                    } catch (Exception e) {

                    }
                } else {
                    Log.e("TAG2-ERRO", "the response " + response.errorBody().toString());
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
    public void mostrarPeliculasBusqueda(@NonNull Call<MovieSearchResponse> responseCall) {
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {

                if (response.code() == 200) {
                    Log.e("TAG-ERRO", "the response " + response.body().toString());
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    try {
                        view.mostrarPeliculasBusqueda(movies);
                    } catch (Exception e) {

                    }
                } else {
                    Log.e("TAG2-ERRO", "the response " + response.errorBody().toString());
                    String respuesta = "hay un error en la descarga del recurso 2 : " + response.code();
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
                    Log.v("TAG", "the response : " + movie.getTitle());
                    try {
                        view.mostrarPeliculasId(movie);
                    } catch (Exception e) {

                    }
                } else {
                    try {
                        Log.v("TAG", "Error : " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });

    }

}
