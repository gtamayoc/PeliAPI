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
    public void buscarPeliculas(String nombre, int page) {
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
    public void buscarPeliculasPopularPage(int page, int tipoBusqueda) {
        this.model.descargarPeliculasTipoPage(page, tipoBusqueda);
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
    public void mostrarPeliculasPopular(@NonNull Call<MovieSearchResponse> responseCall) {
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {

                if (response.code() == 200) {
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    try {
                        view.mostrarPeliculas(movies);
                        view.configureRecyclerView2(movies);
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
                        view.configureRecyclerView1(movies);
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
    public void mostrarPeliculasBusqueda(@NonNull Call<MovieSearchResponse> responseCall) {
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {

                if (response.code() == 200) {
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    try {
                        view.mostrarPeliculasBusqueda(movies);
                    } catch (Exception e) {

                    }
                } else {
                    String respuesta = "hay un error en la descarga del recurso : " + response.code();
                    view.errorCarga(respuesta);
                }

            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                Log.e("error", "the response " + t.getMessage());
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
