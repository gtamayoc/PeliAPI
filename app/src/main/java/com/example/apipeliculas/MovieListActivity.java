package com.example.apipeliculas;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apipeliculas.adaptadores.MovieRecyclerView;
import com.example.apipeliculas.adaptadores.OnMovieListener;
import com.example.apipeliculas.interfaces.MovieInterface;
import com.example.apipeliculas.models.MovieModel;
import com.example.apipeliculas.presenter.MoviePresenter;

import java.util.List;

public class MovieListActivity extends AppCompatActivity implements MovieInterface.view, OnMovieListener {


    String nombrePelicula;
    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerView;


    MovieInterface.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MoviePresenter(this);
        recyclerView = findViewById(R.id.recyclerview);

        nombrePelicula = "jack";
        presenter.obtenerPeliculas("" + nombrePelicula, "2");
    }

//    private void GetRetrofitResponseAccordingToID() {
//        MovieApi movieApi = Service.getMovieApi();
//        Call<MovieModel> responseCall = movieApi.getMovie(343611, Credenciales.API_KEY);
//
//        responseCall.enqueue(new Callback<MovieModel>() {
//            @Override
//            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
//                if (response.code() == 200) {
//                    MovieModel movie = response.body();
//                    Log.v("TAG", "the response : " + movie.getTitle());
//                } else {
//                    try {
//                        Log.v("TAG", "Error : " + response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieModel> call, Throwable t) {
//
//            }
//        });
//    }

    @Override
    public void mostrarPeliculas(List<MovieModel> movies) {
        for (MovieModel movie : movies) {
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