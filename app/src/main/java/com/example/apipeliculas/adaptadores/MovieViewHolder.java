package com.example.apipeliculas.adaptadores;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apipeliculas.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    TextView title, release_date, duration, calificacion;
    ImageView imageView;
    RatingBar ratingBar;

    OnMovieListener onMovieListener;

    public MovieViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);
        imageView = itemView.findViewById(R.id.movie_image_view);
        calificacion = itemView.findViewById(R.id.stars);

        itemView.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        onMovieListener.onMovieClick(getAdapterPosition());
    }
}
