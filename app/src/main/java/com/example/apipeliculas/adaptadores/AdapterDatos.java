package com.example.apipeliculas.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apipeliculas.MovieListActivity;
import com.example.apipeliculas.R;
import com.example.apipeliculas.models.MovieModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    private List<MovieModel> mMovies;
    private List<MovieModel> mMoviesOriginal;
    Context contex;
    MovieListActivity movies;
    final AdapterDatos.OnItemClickListener listener;


    public interface OnItemClickListener{
        void onItemClick(MovieModel item);
    }

    public AdapterDatos(List<MovieModel> mMovies, Context contex, AdapterDatos.OnItemClickListener listener) {
        this.contex = contex;
        this.mMovies = mMovies;
        this.listener = listener;
        mMoviesOriginal = new ArrayList<>();
        mMoviesOriginal.addAll(mMovies);
    }


    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_api, null);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.cv.setAnimation(AnimationUtils.loadAnimation(contex, R.anim.recycler_anim));
        holder.asignarDatos(mMovies.get(position));
    }

    public void filtrado(@NonNull String busqueda) {
        int longitud = busqueda.length();
        if (longitud == 0) {
            mMovies.clear();
            mMovies.addAll(mMoviesOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<MovieModel> collection = mMovies.stream().filter(i -> i.getTitle()
                        .toLowerCase().contains(busqueda.toLowerCase())).collect(Collectors.toList());
                mMovies.clear();
                mMovies.addAll(collection);
            } else {
                for (MovieModel m : mMoviesOriginal) {
                    if (m.getTitle().toLowerCase().contains(busqueda.toLowerCase())) {
                        mMovies.add(m);
                    }
                }
            }
        }

        notifyDataSetChanged();
    }


    public void agregar(List<MovieModel> movies) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<MovieModel> collection = movies;
                mMovies.clear();
                mMovies.addAll(mMoviesOriginal);

            } else {
                mMovies.clear();
                for (MovieModel m : mMoviesOriginal) {
                        mMovies.add(m);
                }
            }

        notifyDataSetChanged();
    }


    public void filtrado2(String busqueda) {

        int longitud = busqueda.length();
        if (longitud == 0) {
            mMovies.clear();
            mMovies.addAll(mMoviesOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<MovieModel> collection = mMovies.stream().filter(i -> i.getTitle()
                        .toLowerCase().contains(busqueda.toLowerCase())).collect(Collectors.toList());
                mMovies.clear();
                mMovies.addAll(collection);
            } else {
                for (MovieModel m : mMoviesOriginal) {
                    if (m.getTitle().toLowerCase().contains(busqueda.toLowerCase())) {
                        mMovies.add(m);
                    }
                }
            }
        }

        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (mMovies != null) {
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
        CardView cv;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.movie_image_view);
            calificacion = itemView.findViewById(R.id.stars);
            title= itemView.findViewById(R.id.prueba);


        }



        public void asignarDatos(@NonNull MovieModel movieModel) {
            Float voto = (Float) (movieModel.getVote_average());
            calificacion.setText("" + voto);
            if(movieModel.getPoster_path() == null){
                Glide.with(itemView.getContext())
                        .load(R.drawable.noimage)
                        .into(imageView);
            }else{

                Glide.with(itemView.getContext())
                        .load("https://image.tmdb.org/t/p/w500/"
                                + movieModel.getPoster_path())
                        .into(imageView);
                System.out.println("cargando imagen");
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(movieModel);
                }
            });

        }

    }
}
