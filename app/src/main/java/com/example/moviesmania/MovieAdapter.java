package com.example.moviesmania;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;







public class MovieAdapter extends BaseAdapter {

    private static final String TAG = MovieAdapter.class.getSimpleName();

    private Context mContext;
    private ArrayList<Movie> list;
    public static final String MOVIE_BASE_URL="https://image.tmdb.org/t/p/w500";

    public MovieAdapter(Context context, ArrayList<Movie> movieList) {
        this.mContext = context;
        this.list = movieList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Movie getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * We are creating a  View manually.
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            // Inflate the layout for each item
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
        }

        // Get the ImageView from the layout
        imageView = convertView.findViewById(R.id.imageView);

        // Load data into the ImageView using Picasso
        Movie movie = getItem(position);
        Picasso.get().load(MOVIE_BASE_URL + movie.getPosterPath())
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .into(imageView);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie clickedMovie = getItem(position);
                Log.d("Pressed", String.valueOf(position));
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                // Pass the clicked movie data to the new activity
                intent.putExtra("movie", clickedMovie);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    public void setMovies(ArrayList<Movie> movieList) {
        this.list = movieList;
    }




}
