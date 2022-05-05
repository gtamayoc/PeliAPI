package com.example.apipeliculas;

import static com.example.apipeliculas.R.*;
import static com.example.apipeliculas.R.color.*;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apipeliculas.adaptadores.AdapterDatos;
import com.example.apipeliculas.adaptadores.MovieRecyclerView;
import com.example.apipeliculas.adaptadores.OnMovieListener;
import com.example.apipeliculas.interfaces.MovieInterface;
import com.example.apipeliculas.models.MovieModel;
import com.example.apipeliculas.models.MoviesModel;
import com.example.apipeliculas.presenter.MoviePresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MovieListActivity extends AppCompatActivity implements MovieInterface.view, OnMovieListener, SearchView.OnQueryTextListener {


    String nombrePelicula,nombrePeliculaBusqueda;
    int id;
    private RecyclerView recyclerView, recyclerView1;
    private MovieRecyclerView movieRecyclerView;
    TextView textViewPopular,textViewRecientes,textViewPuntuacion, textViewProximos, textViewUltimos,textView;
    SearchView search, search1;
    ImageView imageView;

    AdapterDatos adapterDatos,adapterDatos1,adapterDatosBusqueda;

    MovieInterface.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        presenter = new MoviePresenter(this);
        recyclerView1 = findViewById(R.id.recyclerview1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        textViewPopular = findViewById(R.id.textViewPopular);
        textViewRecientes = findViewById(R.id.textViewRecientes);
        textViewPuntuacion = findViewById(R.id.textViewMejorPuntuación);
        textViewProximos= findViewById(R.id.textViewProximos);
        textView = findViewById(R.id.prueba);
        imageView = findViewById(R.id.movie_image_view1);


//       nombrePelicula = "The Incredibles";
        nombrePelicula = "the home";
        id = 65;
//        presenter.buscarPeliculasPopular();
        presenter.obtenerPeliculas("" + nombrePelicula, "1");
//        presenter.buscarPeliculasDiscover();
        presenter.obtenerPeliculasId(id);
        search = findViewById(R.id.search);
        search.setOnQueryTextListener(this);



        textViewPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewPopular.setTextAppearance(MovieListActivity.this, style.active);
                textViewRecientes.setTextAppearance(MovieListActivity.this, style.normal);
                textViewPuntuacion.setTextAppearance(MovieListActivity.this, style.normal);
                textViewProximos.setTextAppearance(MovieListActivity.this, style.normal);
                presenter.buscarPeliculasPopular();
            }
        });

        textViewRecientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewPopular.setTextAppearance(MovieListActivity.this, style.normal);
                textViewRecientes.setTextAppearance(MovieListActivity.this, style.active);
                textViewPuntuacion.setTextAppearance(MovieListActivity.this, style.normal);
                textViewProximos.setTextAppearance(MovieListActivity.this, style.normal);
                presenter.buscarPeliculasUltimos();
            }
        });

        textViewPuntuacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewPopular.setTextAppearance(MovieListActivity.this, style.normal);
                textViewRecientes.setTextAppearance(MovieListActivity.this, style.normal);
                textViewPuntuacion.setTextAppearance(MovieListActivity.this, style.active);
                textViewProximos.setTextAppearance(MovieListActivity.this, style.normal);
                presenter.buscarPeliculasTop();
            }
        });

        textViewProximos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewPopular.setTextAppearance(MovieListActivity.this, style.normal);
                textViewRecientes.setTextAppearance(MovieListActivity.this, style.normal);
                textViewPuntuacion.setTextAppearance(MovieListActivity.this, style.normal);
                textViewProximos.setTextAppearance(MovieListActivity.this, style.active);
                presenter.buscarPeliculasProximos();
            }
        });



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
    public void mostrarPeliculasBusqueda(List<MovieModel> movies) {
        adapterDatos = new AdapterDatos(movies,this);
        recyclerView1.setAdapter(adapterDatos);
        ultimaPelicula(movies);
    }

    @Override
    public void configureRecyclerView1(List<MovieModel> movies) {
        adapterDatos = new AdapterDatos(movies,this);
        recyclerView1.setAdapter(adapterDatos);
        ultimaPelicula(movies);

    }

    @Override
    public void recyclerBusqueda(List<MovieModel> movies) {
        adapterDatosBusqueda = new AdapterDatos(movies,this);
        recyclerView1.setAdapter(adapterDatosBusqueda);
    }


    @Override
    public void errorCarga(String error) {
        Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
    }


    private void ConfigureRecyclerView(List<MovieModel> mMovies) {
        adapterDatos = new AdapterDatos(mMovies,this);
        recyclerView1.setAdapter(adapterDatos);
    }

    public void ultimaPelicula(@NonNull List<MovieModel> mMovies) {
        String sCadena = mMovies.get(0).getRelease_date();
        String sSubCadena = sCadena.substring(0,4);

            textView.setText(mMovies.get(0).getTitle()+" ("+sSubCadena+")");
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/"
                        + mMovies.get(0).getPoster_path())
                .into(imageView);
    }

    @Override
    public void onMovieClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(@NonNull String s) {
        if (s.isEmpty()){
            presenter.buscarPeliculasPopular();
        }else{
            if(s.length() > 30){
                Toast.makeText(this, "Ingresar Valor Valido", Toast.LENGTH_SHORT).show();
            }else{
                presenter.obtenerPeliculas("" + s, "1");
                adapterDatos.filtrado(s);
            }

        }
//        presenter.buscarPeliculas("" + s, "1");
//        adapterDatosBusqueda.filtrado(s);
        return false;
    }
}