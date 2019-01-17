package com.example.user.moviedb;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.moviedb.adapter.TrailerAdapter;
import com.example.user.moviedb.api.client;
import com.example.user.moviedb.api.interfaceapi;
import com.example.user.moviedb.model.Result;
import com.example.user.moviedb.model.ResultTrailer;
import com.example.user.moviedb.model.modelTrailer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    TextView nameOfMovie, plotSynopsis, userRating, releaseDate;
    ImageView imageView;
    private Toolbar toolbar;
    private Result item;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView recyclerViewTrailer;
    private TrailerAdapter adapterTrailer;
    private List<ResultTrailer> trailerList = new ArrayList<>();
    private String PARCEL_OBJECT = "parcel_object";
    private final AppCompatActivity activity = DetailActivity.this;
    String baseUrlImg = "https://image.tmdb.org/t/p/w500/";
    String title;
    private Context c;
    int movie_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView = (ImageView) findViewById(R.id.thumbnail_image_header);
        nameOfMovie = (TextView) findViewById(R.id.title);
        plotSynopsis = (TextView) findViewById(R.id.plotsynopsis);
        userRating = (TextView) findViewById(R.id.userrating);
        releaseDate = (TextView) findViewById(R.id.releasedate);

        initCollapsingToolbar();
//        loadTrailer();
        loadData();
//        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
//        collapsingToolbarLayout.setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
//        collapsingToolbarLayout.setCollapsedTitleTextColor(
//                ContextCompat.getColor(this, R.color.white));
//        collapsingToolbarLayout.setExpandedTitleColor(
//                ContextCompat.getColor(this, R.color.colorPrimary));

//        imgFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (isFavorite){
//                    deleteFavorite();
//                } else {
//                    addFavorite();
//                }
//
//                isFavorite = !isFavorite;
//                setFavorite();
//            }
//        });
    }

    private void loadTrailer() {
        loadData();
        Log.e("error", String.valueOf(movie_id));
        client client = new client();
        interfaceapi interfaceapi = client.getClient().create(com.example.user.moviedb.api.interfaceapi.class);
        Call<modelTrailer> call = interfaceapi.getTrailelMovie(movie_id, BuildConfig.THE_MOVIE_DB_API_TOKEN);
        call.enqueue(new Callback<modelTrailer>() {
            @Override
            public void onResponse(Call<modelTrailer> call, Response<modelTrailer> response) {
                trailerList = response.body().getResults();
                trailerList.get(0).getKey();
                mAdapter = new TrailerAdapter(c, trailerList);
//                recyclerViewTrailer.setAdapter(mAdapter);
                mLayoutManager = new LinearLayoutManager(c);
                recyclerViewTrailer.setLayoutManager(mLayoutManager);
            }

            @Override
            public void onFailure(Call<modelTrailer> call, Throwable t) {

            }
        });
    }

    private void loadData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            item = bundle.getParcelable(PARCEL_OBJECT);
//            loadDataSQLite();
            if (item != null) {
                Glide.with(this).load(baseUrlImg + item.getPosterPath()).into(imageView);
                plotSynopsis.setText(item.getOverview());
                title = item.getTitle();
                releaseDate.setText(item.getReleaseDate());
                nameOfMovie.setText(item.getOriginalTitle());
                String vote = Double.toString(item.getVoteAverage());
                userRating.setText(vote);
                movie_id = item.getId();
            }
        }
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout CollapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        CollapsingToolbarLayout.setTitle(" ");
        CollapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, R.color.colorPrimary));

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    nameOfMovie.setVisibility(View.GONE);
                    CollapsingToolbarLayout.setTitle(title);
                    isShow = true;

                } else if (isShow) {
                    nameOfMovie.setVisibility(View.VISIBLE);
                    CollapsingToolbarLayout.setTitle(" ");
                    isShow = false;

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

