package com.example.user.moviedb.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.moviedb.DetailActivity;
import com.example.user.moviedb.R;
import com.example.user.moviedb.model.Result;

import java.util.ArrayList;
import java.util.List;

public class adapterMovie extends RecyclerView.Adapter<adapterMovie.MyViewHolder> {
    private Context c;
    private ArrayList<Result> listMovie;
    private String PARCEL_OBJECT = "parcel_object";

    public adapterMovie(Context c, List<Result> dataMovie) {
        this.c = c;
        this.listMovie = (ArrayList<Result>) dataMovie;
    }

    public ArrayList<Result> getDataMovie() {
        return listMovie;
    }

//    public void setDataMovie(ArrayList<Result> dataMovie) {
//        this.dataMovie = dataMovie;
//    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.movie_list_row, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final Result data = getDataMovie().get(position);

        holder.Title.setText(data.getTitle());
        holder.rincian.setText(data.getOverview());
        String vote = Double.toString(data.getVoteAverage());
        holder.rating.setText(vote);
        Glide.with(c).load("https://image.tmdb.org/t/p/w500/"+data
                .getPosterPath()).into(holder.imageMovie);
//        Log.e("TAG", "onBindViewHolder() returned: " + listMovie.get(position).getPosterPath());

        String poster = "https://image.tmdb.org/t/p/w500/" + listMovie.get(position).getPosterPath();

        Glide.with(c)
                .load(poster)
                .into(holder.imageMovie);
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c,DetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(PARCEL_OBJECT, data);
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Title, rincian, rating;
        ImageView imageMovie;
        CardView movie;
        Button detail;

        public MyViewHolder(View itemView){
            super(itemView);
            Title = itemView.findViewById(R.id.title);
            imageMovie = itemView.findViewById(R.id.imagemovie);
            rating = itemView.findViewById(R.id.rating);
//            movie = itemView.findViewById(R.id.cv_movie);
            rincian = itemView.findViewById(R.id.rincian);
            detail = itemView.findViewById(R.id.btndetail);
        }
    }
}

