package com.example.apipeliculas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.apipeliculas.models.MovieModel;

public class MovieDescription extends AppCompatActivity {

    TextView title;
    ImageView imageView;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);
        title = findViewById(R.id.textView2);
        imageView = findViewById(R.id.movie_image_view);
        btnBack = findViewById(R.id.back);

        MovieModel movieModel=getIntent().getParcelableExtra("MovieElement");
        title.setText(movieModel.getTitle());

        if(movieModel.getPoster_path() == null){
            Glide.with(this)
                    .load(R.drawable.noimage)
                    .into(imageView);
        }else{

            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/"
                            + movieModel.getPoster_path())
                    .into(imageView);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDescription.this,MovieListActivity.class);
                startActivity(intent);
            }
        });

    }
}