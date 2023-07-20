package com.example.moviesmania.DetailsFragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.moviesmania.Database.DB.MyDbHandler;
import com.example.moviesmania.Movie;
import com.example.moviesmania.MovieDetailActivity;
import com.example.moviesmania.R;


public class PopularityFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_popularity, container, false);

        Button trailerButton = rootView.findViewById(R.id.trailer_button);
        trailerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click event here
                openTrailer();
            }
        });

//        Button markFavourite = rootView.findViewById(R.id.button_mark_as_favorite);
//
//        markFavourite.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Log.d("button", "fav button clicked");
//                    }
//                }
//        );



        return rootView;
    }

    private void openTrailer() {
        // Perform action when the trailer button is clicked
        // For example, open a YouTube link
        String youtubeUrl = "https://www.youtube.com/watch?v=zkGG5C0hmTM";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
        startActivity(intent);
    }


    public void addMovieToFavourites(Movie movie)
    {
        MyDbHandler dbHandler = new MyDbHandler(getContext());
        dbHandler.addMovie(movie);
    }


}