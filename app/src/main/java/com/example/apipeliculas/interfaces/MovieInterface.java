package com.example.apipeliculas.interfaces;

import androidx.annotation.NonNull;

import com.example.apipeliculas.models.MovieModel;
import com.example.apipeliculas.response.MovieSearchResponse;

import java.util.List;

import retrofit2.Call;

public interface MovieInterface {

    interface view {
        void mostrarPeliculas(List<MovieModel> movies);

        void mostrarPeliculasId(MovieModel movies);

        void mostrarPeliculasBusqueda(List<MovieModel> movies);

        void ultimaPelicula(List<MovieModel> movies);

        void configureRecyclerViewMostrarPeliculas(List<MovieModel> movies);

        void configureRecyclerViewMostrarPeliculasPopular(List<MovieModel> movies);

        void recyclerBusqueda(List<MovieModel> movies);

        void errorCarga(String error);
    }

    interface presenter {

        void buscarPeliculas(String nombre, int page);

        void buscarPeliculasPopular();

        void buscarPeliculasPopularPage(int page, int tipoBusqueda);

        void mostrarPeliculasPopular(@NonNull Call<MovieSearchResponse> responseCall);

        void mostrarPeliculas(Call<MovieSearchResponse> responseCall);

        void mostrarPeliculasId(Call<MovieModel> responseCall);

    }

    interface model {
        void descargarPeliculas(String nombre, String page);

        void descargarPeliculasBusqueda(String nombre, int page);

        void descargarPeliculasDiscover();

        void descargarPeliculasPopular();

        void descargarPeliculasTipoPage(int page, int tipoBusqueda);
    }

}
