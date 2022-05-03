package com.example.apipeliculas.interfaces;

import com.example.apipeliculas.models.MovieModel;
import com.example.apipeliculas.response.MovieSearchResponse;

import java.util.List;

import retrofit2.Call;

public interface MovieInterface {

    interface view{
        void mostrarPeliculas(List<MovieModel> movies);
        void mostrarPeliculasId(MovieModel movies);
        void mostrarPeliculasBusqueda(List<MovieModel> movies);
        void configureRecyclerView1(List<MovieModel> movies);
        void recyclerBusqueda(List<MovieModel> movies);
        void errorCarga(String error);

    }
    interface presenter{
        void obtenerPeliculas(String nombre, String page);
        void discoveryPeliculas(String nombre, String page);
        void buscarPeliculas(String nombre, String page);
        void obtenerPeliculasId(int id);
        void mostrarPeliculas(Call<MovieSearchResponse> responseCall);
        List<MovieModel> mostrarPeliculasBusqueda(Call<MovieSearchResponse> responseCall);
        void mostrarPeliculasId(Call<MovieModel> responseCall);

        List<MovieModel> discoveryPelicula(String s, String s1);
    }

    interface model{
        void descargarPeliculas(String nombre, String page);
        void descargarPeliculasDiscovery(String nombre, String page);
        void descargarPeliculasBusqueda(String nombre, String page);
        void descargarPeliculasId(int id);
    }

}
