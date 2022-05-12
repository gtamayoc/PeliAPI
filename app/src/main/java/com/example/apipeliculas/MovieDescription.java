package com.example.apipeliculas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.apipeliculas.models.MovieModel;
import com.example.apipeliculas.utils.MovieFunctions;

import java.text.SimpleDateFormat;

public class MovieDescription extends AppCompatActivity {

    TextView title;
    ImageView imageView;
    ImageButton btnBack;
    TextView date;
    TextView description;
    TextView puntuacion;
    int page;
    boolean cargar;
    int tipoBusqueda;
    String busqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);
        title = findViewById(R.id.textView2);
        imageView = findViewById(R.id.movie_image_view);
        btnBack = findViewById(R.id.back);
        date = findViewById(R.id.date);
        description = findViewById(R.id.description);
        puntuacion = findViewById(R.id.stars);
        cargar = false;
        Context context = this;


        Bundle bundle = getIntent().getExtras();
        page = bundle.getInt("page");
        tipoBusqueda = bundle.getInt("tipoBusquedar");
        busqueda= bundle.getString("busqueda");
        MovieModel movieModel = getIntent().getParcelableExtra("MovieElement");
        title.setText(movieModel.getTitle());
        String sCadena = MovieFunctions.ordenarFecha(movieModel.getRelease_date());
        String overview = movieModel.getOverview();
        date.setText(sCadena);
        if(overview.isEmpty()){
            description.setText("Descripcion No Disponible");
        }else{
            description.setText(movieModel.getOverview());
        }

        Float voto = (Float) (movieModel.getVote_average());
        puntuacion.setText("" + voto);

        if (movieModel.getPoster_path() == null) {
            Glide.with(this)
                    .load(R.drawable.noimage)
                    .into(imageView);
        } else {

            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/"
                            + movieModel.getPoster_path())
                    .into(imageView);


        }



            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    moveToInicio();
                }
            });


        

    }
    public void moveToInicio(){
        Intent intent = new Intent(MovieDescription.this, MovieListActivity.class);
        intent.putExtra("cargar", cargar);
        intent.putExtra("page", page);
        intent.putExtra("tipoBusquedar", tipoBusqueda);
        intent.putExtra("busqueda", ""+busqueda);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
            moveToInicio();

    }
}