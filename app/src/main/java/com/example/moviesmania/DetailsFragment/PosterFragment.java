package com.example.moviesmania.DetailsFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.moviesmania.R;
import com.squareup.picasso.Picasso;

public class PosterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_poster, container, false);
    }

    public void setPoster(String url)
    {
        ImageView view = (ImageView) getView().findViewById(R.id.movie_poster);
        Picasso.get().load(url).into(view);
    }
}