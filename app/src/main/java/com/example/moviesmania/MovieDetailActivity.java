package com.example.moviesmania;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.moviesmania.Database.DB.MyDbHandler;
import com.example.moviesmania.DetailsFragment.BackdropFragment;
import com.example.moviesmania.DetailsFragment.OverviewFragment;
import com.example.moviesmania.DetailsFragment.PopularityFragment;
import com.example.moviesmania.DetailsFragment.PosterFragment;

public class MovieDetailActivity extends AppCompatActivity {


    Movie movie = new Movie();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        Movie clickedMovie = (Movie) intent.getSerializableExtra("movie");

        movie = clickedMovie;
        BackdropFragment backdropFragment = (BackdropFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_backdrop);

        if (backdropFragment != null) {
            String backdrop_path = clickedMovie.getBackdropPath();
            backdropFragment.setBackdrop(MovieAdapter.MOVIE_BASE_URL + backdrop_path);
        }

        PosterFragment posterFragment = (PosterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_poster);

        if (posterFragment != null) {
            String poster_path = clickedMovie.getPosterPath();
            posterFragment.setPoster(MovieAdapter.MOVIE_BASE_URL + poster_path);
        }

        PopularityFragment popularityFragment = (PopularityFragment) getSupportFragmentManager().findFragmentById(R.id.movie_details);

        if (popularityFragment != null) {
            TextView title = findViewById(R.id.movie_title);
            title.setText(clickedMovie.getTitle());

        }

        OverviewFragment overviewFragment = (OverviewFragment) getSupportFragmentManager().findFragmentById(R.id.overview_fragment);
        if (overviewFragment != null) {
            TextView content = findViewById(R.id.movie_content);
            content.setText(clickedMovie.getOverview());
        }

        Button markFavourite = findViewById(R.id.button_mark_as_favorite);

        markFavourite.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.d("button", "fav button clicked");
                    }
                }
        );


    }
//
//    public void addMovieToFavourites(Movie movie)
//    {
//        MyDbHandler dbHandler = new MyDbHandler(MovieDetailActivity.this);
//        dbHandler.addMovie(movie);
//    }

    public Movie getClickedMovie()
    {
        return movie;
    }
}