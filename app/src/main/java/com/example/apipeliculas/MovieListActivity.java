package com.example.apipeliculas;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apipeliculas.adaptadores.AdapterDatos;
import com.example.apipeliculas.adaptadores.MovieRecyclerView;
import com.example.apipeliculas.adaptadores.OnMovieListener;
import com.example.apipeliculas.interfaces.MovieInterface;
import com.example.apipeliculas.models.MovieModel;
import com.example.apipeliculas.presenter.MoviePresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        nombrePelicula = "The Incredibles";
        id = 65;
        presenter.obtenerPeliculas("" + nombrePelicula, "1");

        presenter.obtenerPeliculasId(id);


    }


    @Override
    public void mostrarPeliculas(@NonNull List<MovieModel> movies) {
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
        for (MovieModel movie : movies1) {
            try {
                Log.v("POKEDEX", "name : " + movie.getTitle());
            } catch (Exception e) {
            }

        }


    }

    @Override
    public void configureRecyclerView1(List<MovieModel> movies) {
        AdapterDatos adapterDatos = new AdapterDatos(movies);
        recyclerView.setAdapter(adapterDatos);
    }


    @Override
    public void errorCarga(String error) {
        Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
    }


    private void ConfigureRecyclerView(List<MovieModel> mMovies) {
        AdapterDatos adapterDatos = new AdapterDatos(mMovies);
        recyclerView.setAdapter(adapterDatos);
    }

    @Override
    public void onMovieClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {

    }
}