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
import com.example.apipeliculas.R;
import com.example.apipeliculas.models.MovieModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    private List<MovieModel> mMovies;
    private List<MovieModel> mMoviesOriginal;

    public AdapterDatos(List<MovieModel> mMovies) {
        this.mMovies = mMovies;
        mMoviesOriginal = new ArrayList<>();
        mMoviesOriginal.addAll(mMovies);
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

    public void filtrado( String busqueda){
        int longitud = busqueda.length();
        if( longitud == 0){
            mMovies.clear();
            mMovies.addAll(mMoviesOriginal);
        }else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<MovieModel> collection = mMovies.stream().filter(i-> i.getTitle()
                        .toLowerCase().contains(busqueda.toLowerCase())).collect(Collectors.toList());
                mMovies.addAll(collection);
            }else{
                for (MovieModel m: mMoviesOriginal) {
                    if(m.getTitle().toLowerCase().contains(busqueda.toLowerCase())){
                        mMovies.add(m);
                    }
                }
            }
        }

        notifyDataSetChanged();
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
        TextView calificacion;
        TextView releaseDate;
        TextView duration;
        ImageView imageView;
        RatingBar ratingBar;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.movie_image_view);
            calificacion = itemView.findViewById(R.id.stars);

        }

        public void asignarDatos(@NonNull MovieModel movieModel) {
            Float voto= (Float) (movieModel.getVote_average());
            calificacion.setText(""+voto);
            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500/"
                            + movieModel.getPoster_path())
                    .into(imageView);

        }
    }
}
