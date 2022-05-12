package com.example.apipeliculas;

import static com.example.apipeliculas.R.layout;
import static com.example.apipeliculas.R.style;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import com.example.apipeliculas.utils.MovieInternet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MovieListActivity extends AppCompatActivity implements MovieInterface.view, OnMovieListener, SearchView.OnQueryTextListener {


    int id;
    int page = 1;
    int tipoBusqueda = 1;
    public List<MovieModel> mMovies1;
    private static final long TIEMPO_MINIMO = 900;
    private static final long TIEMPO_MINIMO2 = 3500;
    private long ultimoClick = 0;
    private RecyclerView recyclerView, recyclerView1;
    private MovieRecyclerView movieRecyclerView;
    boolean aptoParaCargar = true;
    boolean cargar = true;
    TextView textViewPopular, textViewRecientes, textViewPuntuacion, textViewProximos, textViewUltimos, textView, textViewStarts, textViewVerTodo;
    SearchView search, search1;
    ImageView imageView;
    LinearLayout linearLayout;
    AdapterDatos adapterDatos, adapterDatos1, adapterDatosBusqueda;
    String busqueda;
    ImageButton buttonPerfil;
    MovieInterface.presenter presenter;
    ImageView loading;
    Context context = this;

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
        buttonPerfil = findViewById(R.id.perfil);
        loading = findViewById(R.id.loading);


        id = 65;

        try {
            Bundle bundle = getIntent().getExtras();
            tipoBusqueda = bundle.getInt("tipoBusquedar");
            cargar = bundle.getBoolean("cargar");
            busqueda = bundle.getString("busqueda");
            MovieModel movieModel = getIntent().getParcelableExtra("MovieElement");
            page = bundle.getInt("page");
            apariencia(tipoBusqueda);
            imprimir(tipoBusqueda);
            if (tipoBusqueda == 5) {
                search.setQuery(busqueda, false);
            }

        } catch (Exception e) {
        }

        if (cargar) {
            presenter.buscarPeliculasPopular();
        } else {
            apariencia(tipoBusqueda);
            imprimir(tipoBusqueda);
        }

        recyclerView1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
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
                if (SystemClock.elapsedRealtime() - ultimoClick < TIEMPO_MINIMO) {
                    return;
                }
                search.setQuery("", false);
                ultimoClick = SystemClock.elapsedRealtime();
                page = 1;
                tipoBusqueda = 1;
                aptoParaCargar = true;
                apariencia(tipoBusqueda);
                imprimir(tipoBusqueda);
            }
        });

        textViewRecientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - ultimoClick < TIEMPO_MINIMO) {
                    return;
                }
                search.setQuery("", false);
                ultimoClick = SystemClock.elapsedRealtime();
                page = 1;
                tipoBusqueda = 2;
                aptoParaCargar = true;
                apariencia(tipoBusqueda);
                imprimir(tipoBusqueda);
            }
        });

        textViewPuntuacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - ultimoClick < TIEMPO_MINIMO) {
                    return;
                }
                search.setQuery("", false);
                ultimoClick = SystemClock.elapsedRealtime();
                page = 1;
                tipoBusqueda = 3;
                aptoParaCargar = true;
                apariencia(tipoBusqueda);
                imprimir(tipoBusqueda);
            }
        });

        textViewProximos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - ultimoClick < TIEMPO_MINIMO) {
                    return;
                }
                search.setQuery("", false);
                ultimoClick = SystemClock.elapsedRealtime();
                page = 1;
                tipoBusqueda = 4;
                aptoParaCargar = true;
                apariencia(tipoBusqueda);
                imprimir(tipoBusqueda);
            }
        });


        textViewVerTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buttonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - ultimoClick < TIEMPO_MINIMO) {
                    return;
                }
                ultimoClick = SystemClock.elapsedRealtime();
                moveToPerfil();
            }
        });

        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        ejecutar();


    }

    public void noClick(Long ultimoClick, Long TIEMPO_MINIMO) {
        if (SystemClock.elapsedRealtime() - ultimoClick < TIEMPO_MINIMO) {
            return;
        }
        ultimoClick = SystemClock.elapsedRealtime();
    }


    public void apariencia(int tipoBusqueda) {
        if (MovieInternet.OnLine(this)) {
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
                Toast.makeText(MovieListActivity.this, "Error Tipo de Usuario", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No tienes acceso a internet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
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
            presenter.buscarPeliculas("" + busqueda, page);
        } else {
            Toast.makeText(MovieListActivity.this, "Error Tipo de Usuario", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void mostrarPeliculas(@NonNull List<MovieModel> movies) {
        for (MovieModel movie : movies) {
            try {
                aptoParaCargar = true;
                Log.v("Pelicula", "name : " + movie.getTitle());
            } catch (Exception e) {
            }

        }
    }

    @Override
    public void mostrarPeliculasId(MovieModel movies) {
        List<MovieModel> movies1 = new ArrayList((Collection) movies);
        for (MovieModel movie : movies1) {
            try {
                Log.v("Pelicula", "name : " + movie.getTitle());
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

        ultimaPelicula(movies);
        recyclerView1.setAdapter(adapterDatos);
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
        adapterDatos.agregar(movies);
        recyclerView1.setAdapter(adapterDatos);
        ultimaPelicula(movies);
    }

    private void moveToDescription(MovieModel item) {
        Intent intent = new Intent(this, MovieDescription.class);
        intent.putExtra("page", page);
        intent.putExtra("MovieElement", item);
        intent.putExtra("tipoBusquedar", tipoBusqueda);
        intent.putExtra("busqueda", busqueda);
        startActivity(intent);
        if (tipoBusqueda != 5) {
            finish();
        }

    }

    private void moveToPerfil() {
        Intent intent = new Intent(this, MoviePerfil.class);
        startActivity(intent);
        finish();
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

    @SuppressLint("SetTextI18n")
    public void ultimaPelicula(@NonNull List<MovieModel> mMovies) {
        int i = 0;
        String sCadena = mMovies.get(i).getRelease_date();
        String sSubCadena = sCadena.substring(0, 4);
        float voto = mMovies.get(i).getVote_average();
        String votoString = Float.toString(voto);
        textViewStarts.setText(votoString);

        textView.setText(mMovies.get(i).getTitle() + " (" + sSubCadena + ")");

        if (mMovies.get(i).getPoster_path() == null) {
            Glide.with(this)
                    .load(R.drawable.noimage)
                    .into(imageView);
        } else {
            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/"
                            + mMovies.get(i).getPoster_path())
                    .into(imageView);
        }

        ejecutar();
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - ultimoClick < TIEMPO_MINIMO2) {
                    return;
                }
                if (MovieInternet.OnLine(context)) {
                    MovieModel model = mMovies.get(0);
                    moveToDescription(model);
                } else {
                    Toast.makeText(context, "No tienes acceso a internet", Toast.LENGTH_SHORT).show();
                }
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
    public void onBackPressed() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(MovieListActivity.this);
        alerta.setMessage("¿Estas Seguro Que Deseas salir de la Aplicación?")
                .setCancelable(false)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        finishAffinity();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog titulo = alerta.create();
        titulo.show();
    }


    private void ejecutar() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                metodoEjecutar();//llamamos nuestro metodo
                handler.postDelayed(this, 1000);//se ejecutara cada 1 segundos
            }
        }, 100);//empezara a ejecutarse después de 1 milisegundos
    }

    private void metodoEjecutar() {
        if (MovieInternet.OnLine(this)) {
            recyclerView1.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        } else {
            recyclerView1.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public boolean onQueryTextChange(@NonNull String s) {
        tipoBusqueda = 5;
        if (s.isEmpty()) {
            tipoBusqueda = 1;
            apariencia(tipoBusqueda);
            imprimir(tipoBusqueda);
        } else {
            if (s.length() > 30 || s.length() < 2) {
                if (s.length() > 30) {
                    Toast.makeText(context, "Cantidad de caracteres excedida", Toast.LENGTH_SHORT).show();
                }
            } else {
                page = 1;
                presenter.buscarPeliculas("" + s, page);
            }

        }
//        presenter.buscarPeliculas("" + s, "1");
//        adapterDatosBusqueda.filtrado(s);
        apariencia(tipoBusqueda);
        aptoParaCargar = true;
        busqueda = s;
        return true;
    }


}