package com.example.apipeliculas;

import static com.example.apipeliculas.R.layout;
import static com.example.apipeliculas.R.style;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.apipeliculas.presenter.MoviePresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MovieListActivity extends AppCompatActivity implements MovieInterface.view, OnMovieListener, SearchView.OnQueryTextListener {


    String nombrePelicula, nombrePeliculaBusqueda;
    int id;
    private RecyclerView recyclerView, recyclerView1;
    private MovieRecyclerView movieRecyclerView;
    TextView textViewPopular, textViewRecientes, textViewPuntuacion, textViewProximos, textViewUltimos, textView, textViewStarts, textViewVerTodo;
    SearchView search, search1;
    ImageView imageView;
    LinearLayout linearLayout;
    boolean aptoParaCargar = true;
    int previousTotal = 0, totalItemCount = 0, totalItemCount1 = 0;
    AdapterDatos adapterDatos, adapterDatos1, adapterDatosBusqueda;
    int page=1;
    boolean cargar = true;
    public List<MovieModel> mMovies1;
    int tipoBusqueda = 1;

    MovieInterface.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        presenter = new MoviePresenter(this);
        recyclerView1 = findViewById(R.id.recyclerview1);
        recyclerView1.setLayoutManager(layoutManager);
        textViewPopular = findViewById(R.id.textViewPopular);
        textViewRecientes = findViewById(R.id.textViewRecientes);
        textViewPuntuacion = findViewById(R.id.textViewMejorPuntuación);
        textViewProximos = findViewById(R.id.textViewProximos);
        textView = findViewById(R.id.prueba);
        imageView = findViewById(R.id.movie_image_view1);
        textViewStarts = findViewById(R.id.stars);
        linearLayout = findViewById(R.id.peli);
        textViewVerTodo = findViewById(R.id.verTodo);
        recyclerView1.setLayoutManager(layoutManager);
        search = (SearchView) findViewById(R.id.search);
//       nombrePelicula = "The Incredibles";
//              nombrePelicula = "jack";
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        id = 65;

        try {
            Bundle bundle = getIntent().getExtras();
            tipoBusqueda = bundle.getInt("tipoBusquedar");
            cargar = bundle.getBoolean("cargar");
            MovieModel movieModel = getIntent().getParcelableExtra("MovieElement");
            page = bundle.getInt("page");
            imprimir(tipoBusqueda);
            apariencia(tipoBusqueda);

        } catch (Exception e) {
        }

        if (cargar == true) {
            presenter.buscarPeliculasPopular();

        } else {

        }


//      presenter.obtenerPeliculas("" + nombrePelicula, "1");
//      presenter.buscarPeliculasDiscover();
        //   presenter.obtenerPeliculasId(id);


        int filtroBusqueda = 0;


        recyclerView1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dx < 0) {
                    int visibleItemCount1 = layoutManager.getChildCount();
                    int totalItemCount1 = layoutManager.getItemCount();
                    int pastVisitables1 = layoutManager.findLastVisibleItemPosition();
                    if (aptoParaCargar && pastVisitables1 == 2) {
                        if (page == 1) {

                        } else {
                            page -= 1;
                            imprimir(tipoBusqueda);
                            aptoParaCargar = false;
                        }
                    }


                }

                if (dx > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisitables = layoutManager.findFirstVisibleItemPosition();
//                    System.out.println("past " + pastVisitables + "." + visibleItemCount + "." + totalItemCount);
                    if (aptoParaCargar && pastVisitables == 17) {
                        if ((visibleItemCount + pastVisitables) >= totalItemCount
                                && pastVisitables >= 0) {
                            page += 1;
                            imprimir(tipoBusqueda);
                            aptoParaCargar = false;
                        }

                    }
                }


            }
        });
        textViewPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = 1;
                tipoBusqueda = 1;
                aptoParaCargar = true;
                apariencia(tipoBusqueda);
                presenter.buscarPeliculasPopular();
            }
        });

        textViewRecientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = 1;
                tipoBusqueda = 2;
                aptoParaCargar = true;
                apariencia(tipoBusqueda);
                presenter.buscarPeliculasUltimos();
            }
        });

        textViewPuntuacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = 1;
                tipoBusqueda = 3;
                aptoParaCargar = true;
                apariencia(tipoBusqueda);
                presenter.buscarPeliculasTop();
            }
        });

        textViewProximos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = 1;
                tipoBusqueda = 4;
                aptoParaCargar = true;
                apariencia(tipoBusqueda);
                presenter.buscarPeliculasProximos();
            }
        });


        textViewVerTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    public void apariencia(int tipoBusqueda) {
        if (tipoBusqueda == 1) {
            textViewPopular.setTextAppearance(MovieListActivity.this, style.active);
            textViewRecientes.setTextAppearance(MovieListActivity.this, style.normal);
            textViewPuntuacion.setTextAppearance(MovieListActivity.this, style.normal);
            textViewProximos.setTextAppearance(MovieListActivity.this, style.normal);
        } else if (tipoBusqueda == 2) {
            textViewPopular.setTextAppearance(MovieListActivity.this, style.normal);
            textViewRecientes.setTextAppearance(MovieListActivity.this, style.active);
            textViewPuntuacion.setTextAppearance(MovieListActivity.this, style.normal);
            textViewProximos.setTextAppearance(MovieListActivity.this, style.normal);
        } else if (tipoBusqueda == 3) {
            textViewPopular.setTextAppearance(MovieListActivity.this, style.normal);
            textViewRecientes.setTextAppearance(MovieListActivity.this, style.normal);
            textViewPuntuacion.setTextAppearance(MovieListActivity.this, style.active);
            textViewProximos.setTextAppearance(MovieListActivity.this, style.normal);
        } else if (tipoBusqueda == 4) {
            textViewPopular.setTextAppearance(MovieListActivity.this, style.normal);
            textViewRecientes.setTextAppearance(MovieListActivity.this, style.normal);
            textViewPuntuacion.setTextAppearance(MovieListActivity.this, style.normal);
            textViewProximos.setTextAppearance(MovieListActivity.this, style.active);
        } else if (tipoBusqueda == 5) {
            textViewPopular.setTextAppearance(MovieListActivity.this, style.normal);
            textViewRecientes.setTextAppearance(MovieListActivity.this, style.normal);
            textViewPuntuacion.setTextAppearance(MovieListActivity.this, style.normal);
            textViewProximos.setTextAppearance(MovieListActivity.this, style.normal);
        } else {
            Toast.makeText(MovieListActivity.this, "ERORRRRTipousua", Toast.LENGTH_SHORT).show();
        }
    }


    public void imprimir(int tipoBusqueda) {
        if (tipoBusqueda == 1) {
            presenter.buscarPeliculasPopularPage(page, tipoBusqueda);
        } else if (tipoBusqueda == 2) {
            presenter.buscarPeliculasPopularPage(page, tipoBusqueda);
        } else if (tipoBusqueda == 3) {
            presenter.buscarPeliculasPopularPage(page, tipoBusqueda);
        } else if (tipoBusqueda == 4) {
            presenter.buscarPeliculasPopularPage(page, tipoBusqueda);
        } else if (tipoBusqueda == 5) {

        } else {
            Toast.makeText(MovieListActivity.this, "ERORRRRTipousua", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void mostrarPeliculas(@NonNull List<MovieModel> movies) {
        for (MovieModel movie : movies) {
            try {
                aptoParaCargar = true;
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
        adapterDatos = new AdapterDatos(movies, this, new AdapterDatos.OnItemClickListener() {
            @Override
            public void onItemClick(MovieModel item) {
                moveToDescription(item);
            }
        });
        recyclerView1.setAdapter(adapterDatos);
        ultimaPelicula(movies);
    }



    @Override
    public void configureRecyclerView2(List<MovieModel> movies) {
        adapterDatos = new AdapterDatos(movies, this, new AdapterDatos.OnItemClickListener() {
            @Override
            public void onItemClick(MovieModel item) {
                aptoParaCargar = true;
                moveToDescription(item);
            }

        });
        adapterDatos.agregar(movies);
        ultimaPelicula(movies);
        recyclerView1.setAdapter(adapterDatos);

    }

    @Override
    public void configureRecyclerView1(List<MovieModel> movies) {
        adapterDatos = new AdapterDatos(movies, this, new AdapterDatos.OnItemClickListener() {
            @Override
            public void onItemClick(MovieModel item) {
                moveToDescription(item);
            }
        });
        recyclerView1.setAdapter(adapterDatos);
        ultimaPelicula(movies);

    }

    private void moveToDescription(MovieModel item) {
        Intent intent = new Intent(this, MovieDescription.class);
        intent.putExtra("page", page);
        intent.putExtra("MovieElement", item);
        intent.putExtra("tipoBusquedar", tipoBusqueda);
        startActivity(intent);
    }

    @Override
    public void recyclerBusqueda(List<MovieModel> movies) {
        adapterDatosBusqueda = new AdapterDatos(movies, this, new AdapterDatos.OnItemClickListener() {
            @Override
            public void onItemClick(MovieModel item) {
                moveToDescription(item);
            }
        });
        recyclerView1.setAdapter(adapterDatosBusqueda);

    }


    @Override
    public void errorCarga(String error) {
        Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
    }


    private void ConfigureRecyclerView(List<MovieModel> mMovies) {
        adapterDatos = new AdapterDatos(mMovies, this, new AdapterDatos.OnItemClickListener() {
            @Override
            public void onItemClick(MovieModel item) {
                moveToDescription(item);
            }
        });
        recyclerView1.setAdapter(adapterDatos);
    }

    public void ultimaPelicula(@NonNull List<MovieModel> mMovies) {

        String sCadena = mMovies.get(0).getRelease_date();
        String sSubCadena = sCadena.substring(0, 4);
        float voto = mMovies.get(0).getVote_average();
        String votoString = Float.toString(voto);
        textViewStarts.setText(votoString);


        textView.setText(mMovies.get(0).getTitle() + " (" + sSubCadena + ")");
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/"
                        + mMovies.get(0).getPoster_path())
                .into(imageView);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovieModel model = mMovies.get(0);
                moveToDescription(model);
            }
        });
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
        Toast.makeText(this, "escribiendo..." + tipoBusqueda, Toast.LENGTH_SHORT).show();
        if (s.isEmpty()) {
            presenter.buscarPeliculasPopular();
            apariencia(tipoBusqueda);
            Toast.makeText(this, "Ingresar Valor Valido" + tipoBusqueda, Toast.LENGTH_SHORT).show();
        } else {
            if (s.length() > 30) {
                Toast.makeText(this, "Ingresar Valor Valido", Toast.LENGTH_SHORT).show();
            } else {
                apariencia(tipoBusqueda);
                presenter.obtenerPeliculas("" + s, "1");
                adapterDatos.filtrado(s);
            }

        }
//        presenter.buscarPeliculas("" + s, "1");
//        adapterDatosBusqueda.filtrado(s);
        apariencia(tipoBusqueda);
        aptoParaCargar = true;
        return false;
    }
}