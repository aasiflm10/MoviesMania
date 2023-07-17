package com.example.moviesmania;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.moviesmania.Database.DB.MyDbHandler;
import com.example.moviesmania.utilities.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static public ProgressBar mProgressBar;
    String popularMoviesURL;
    String topRatedMoviesURL;

    ArrayList<Movie> mPopularList;
    ArrayList<Movie> mTopRatedList;

    private GridView mGridView;
    private MovieAdapter mMoviesAdapter;

    private String myApiKey = "4282f706fe4db3c262c8da64cbe6c42c";
    public class FetchMovies extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            popularMoviesURL = "https://api.themoviedb.org/3/movie/popular?api_key="+myApiKey;
            topRatedMoviesURL = "https://api.themoviedb.org/3/movie/top_rated?api_key="+myApiKey;

            mPopularList = new ArrayList<>();
            mTopRatedList = new ArrayList<>();

            try {
                if(NetworkUtils.networkStatus(MainActivity.this))
                {
                    mPopularList = NetworkUtils.fetchData(popularMoviesURL);
                    mTopRatedList = NetworkUtils.fetchData(topRatedMoviesURL);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                }

//                for (Movie m : mPopularList)
//                {
//                    Log.d("mPopular list ", m.getTitle());
//                }
//
//                for (Movie m : mTopRatedList)
//                {
//                    Log.d("mPopular list ", m.getTitle());
//                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            mProgressBar.setVisibility(View.INVISIBLE);

            mMoviesAdapter.setMovies(mPopularList);
            mMoviesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.INVISIBLE);

        MyDbHandler db = new MyDbHandler(MainActivity.this);


        mGridView = findViewById(R.id.gridView);
        mMoviesAdapter = new MovieAdapter(this, new ArrayList<>());
        mGridView.setAdapter(mMoviesAdapter);

        FetchMovies fetchMovies = new FetchMovies();
        fetchMovies.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.top_movies) {
            refreshList(mTopRatedList);
            return true;
        }

        if (id == R.id.pop_movies) {
            refreshList(mPopularList);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void refreshList(ArrayList<Movie> list) {
        mMoviesAdapter.setMovies(list);
        mMoviesAdapter.notifyDataSetChanged();
    }

    

}