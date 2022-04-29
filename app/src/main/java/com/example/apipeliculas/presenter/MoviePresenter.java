package com.example.apipeliculas.presenter;

import android.util.Log;

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
    public void obtenerPeliculas(String nombre, String page) {
        model.descargarPeliculas(nombre, page);
    }

    @Override
    public void mostrarPeliculas(Call<MovieSearchResponse> responseCall) {
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {

                if (response.code() == 200) {
                    Log.e("TAG-ERRO", "the response " + response.body().toString());
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    try {
                        view.mostrarPeliculas(movies);
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

}
