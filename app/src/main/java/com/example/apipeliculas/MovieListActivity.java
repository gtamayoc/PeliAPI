package com.example.apipeliculas;

import static java.util.Objects.*;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apipeliculas.adaptadores.MovieRecyclerView;
import com.example.apipeliculas.adaptadores.OnMovieListener;
import com.example.apipeliculas.interfaces.MovieInterface;
import com.example.apipeliculas.models.MovieModel;
import com.example.apipeliculas.presenter.MoviePresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class MovieListActivity extends AppCompatActivity implements MovieInterface.view, OnMovieListener {


    String nombrePelicula;
    int id;
    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerView;


    MovieInterface.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MoviePresenter(this);
        recyclerView = findViewById(R.id.recyclerview);

        nombrePelicula = "go";
        id = 65;
        presenter.obtenerPeliculas("" + nombrePelicula, "2");

        presenter.obtenerPeliculasId(id);

    }


    @Override
    public void mostrarPeliculas( List<MovieModel> movies) {
        for (MovieModel movie : movies) {
            try {
                Log.v("POKEDEX", "name : " + movie.getTitle());
            } catch (Exception e) {
            }

        }
    }

    @Override
    public void mostrarPeliculasId(MovieModel movies) {
        List<MovieModel> movies1 = new ArrayList((Collection) movies);
            for(MovieModel movie : movies1) {
                try {
                    Log.v("POKEDEX", "name : " + movie.getTitle());
                } catch (Exception e) {
                }

            }


    }



    @Override
    public void errorCarga(String error) {
        Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
    }


    private void ConfigureRecyclerView(List<MovieModel> mMovies) {


    }

    @Override
    public void onMovieClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {

    }
}