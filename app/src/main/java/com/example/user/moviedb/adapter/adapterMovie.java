package com.example.user.moviedb.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.moviedb.R;
import com.example.user.moviedb.model.modelMovie;

import java.util.List;

public class adapterMovie extends RecyclerView.Adapter<adapterMovie.MyViewHolder> {
    private List<modelMovie> moviesList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, rincian;
        ImageView gambarmovie;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            rincian = (TextView) view.findViewById(R.id.rincian);
            gambarmovie = (ImageView) view.findViewById(R.id.imagemovie);
        }
    }
    public adapterMovie(List<modelMovie> moviesList) {
        this.moviesList = moviesList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        modelMovie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.rincian.setText(movie.getOverview());
    }
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}

