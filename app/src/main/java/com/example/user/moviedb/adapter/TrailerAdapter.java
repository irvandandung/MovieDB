package com.example.user.moviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.moviedb.R;
import com.example.user.moviedb.model.ResultTrailer;

import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.MyViewHolder> {
    private Context c;
    private ArrayList<ResultTrailer> listTrailer;
    private String PARCEL_OBJECT = "parcel_object";

    public TrailerAdapter(Context c, List<ResultTrailer> dataTrailer) {
        this.c = c;
        this.listTrailer = (ArrayList<ResultTrailer>) dataTrailer;
    }

    public ArrayList<ResultTrailer> getDataTrailer() {
        return listTrailer;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(c).inflate(R.layout.content_trailer, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final ResultTrailer data = getDataTrailer().get(i);
        myViewHolder.titletrailer.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return listTrailer.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView titletrailer;
        public ImageView thumbnail;

        public MyViewHolder(View view){
            super(view);
            titletrailer = view.findViewById(R.id.lk);
            thumbnail = view.findViewById(R.id.imageTrailer);

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        ResultTrailer clickedDataItem = listTrailer.get(pos);
                        String videoId = listTrailer.get(pos).getKey();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+videoId));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("VIDEO_ID", videoId);
                        c.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
