package com.example.apipeliculas.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apipeliculas.MovieListActivity;
import com.example.apipeliculas.R;
import com.example.apipeliculas.models.MovieModel;

import java.util.List;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    private List<MovieModel> mMovies;

    public AdapterDatos(List<MovieModel> mMovies) {
        this.mMovies = mMovies;
    }


    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_api,null);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        if(mMovies !=null){
            return mMovies.size();
        }
        return 0;
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView title;
        TextView releaseDate;
        TextView duration;
        ImageView imageView;
        RatingBar ratingBar;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.movie_title);
            releaseDate = itemView.findViewById(R.id.movie_category);
            duration = itemView.findViewById(R.id.movie_duration);
            imageView = itemView.findViewById(R.id.movie_image_view);
            ratingBar = itemView.findViewById(R.id.ratings_bar);

        }


        public void asignarDatos(@NonNull MovieModel movieModel) {
            title.setText(movieModel.getTitle());
            releaseDate.setText(movieModel.getRelease_date());
            duration.setText(movieModel.getRuntime());
            int voto= (int) (movieModel.getVote_average()/2);
            ratingBar.setRating(voto);

            Glide.with(itemView.getContext())
                    .load(movieModel.getPoster_path())
                    .into((imageView));

        }
    }
}
