package com.example.moviesmania;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.moviesmania.DetailsFragment.BackdropFragment;

public class MovieDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        Movie clickedMovie = (Movie) intent.getSerializableExtra("movie");

        BackdropFragment backdropFragment = (BackdropFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_backdrop);

        if (backdropFragment != null) {
            String backdrop_path = clickedMovie.getBackdropPath();
            backdropFragment.setBackdrop(MovieAdapter.MOVIE_BASE_URL + backdrop_path);
        }
    }
}