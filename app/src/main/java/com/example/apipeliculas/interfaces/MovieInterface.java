package com.example.apipeliculas.interfaces;

import com.example.apipeliculas.models.MovieModel;
import com.example.apipeliculas.response.MovieSearchResponse;

import java.util.List;

import retrofit2.Call;

public interface MovieInterface {

    interface view{
        void mostrarPeliculas(List<MovieModel> movies);
        void errorCarga(String error);
    }
    interface presenter{
        void obtenerPeliculas(String nombre, String page);
        void mostrarPeliculas(Call<MovieSearchResponse> responseCall);
    }

    interface model{
        void descargarPeliculas(String nombre, String page);
    }

}
