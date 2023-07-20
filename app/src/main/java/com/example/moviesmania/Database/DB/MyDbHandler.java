package com.example.moviesmania.Database.DB;

import static java.lang.String.valueOf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.moviesmania.Database.Params.Params;
import com.example.moviesmania.Movie;

import java.util.ArrayList;

public class MyDbHandler extends SQLiteOpenHelper {

    public MyDbHandler(Context context)
    {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + Params.TABLE_NAME + "(" +

                Params.KEY_ID + " INTEGER PRIMARY KEY, " +
                Params.KEY_TITLE + " TEXT, " +
                Params.KEY_BACKDROP_PATH + " TEXT, " +
                Params.KEY_OVERVIEW + " TEXT, " +
                Params.KEY_RELEASE_DATE + " TEXT, " +
                Params.KEY_POSTER_PATH + " TEXT, " +
                Params.KEY_POPULARITY + " TEXT)";

        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing table
        db.execSQL("DROP TABLE IF EXISTS " + Params.TABLE_NAME);

        // Create a new table
        onCreate(db);
    }

    public void addMovie(Movie movie)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_ID, movie.getId());
        values.put(Params.KEY_TITLE, movie.getTitle());
        values.put(Params.KEY_BACKDROP_PATH, movie.getBackdropPath());
        values.put(Params.KEY_OVERVIEW, movie.getOverview());
        values.put(Params.KEY_RELEASE_DATE, movie.getReleaseDate());
        values.put(Params.KEY_POSTER_PATH, movie.getPosterPath());
        values.put(Params.KEY_POPULARITY, movie.getPopularity());

        // Check if the movie already exists in the database
        Cursor cursor = db.query(Params.TABLE_NAME,
                null,
                Params.KEY_ID + "=?",
                new String[]{String.valueOf(movie.getId())},
                null,
                null,
                null);

        if (cursor.getCount() > 0) {
            // Movie already exists, update the entry
            db.update(Params.TABLE_NAME, values, Params.KEY_ID + "=?", new String[]{String.valueOf(movie.getId())});
        } else {
            // Movie does not exist, insert it as a new entry
            db.insert(Params.TABLE_NAME, null, values);
        }

        cursor.close();
        db.close();

        ArrayList<Movie> list = getAllMovies();
        Log.d("dbAasif" , String.valueOf(list.size()));
        for(int i = 0 ; i < list.size(); ++i)
        {
            Log.d("dbAasif", list.get(i).getTitle());
        }



    }

    public void removeMovie(int movieId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME, Params.KEY_ID + "=?", new String[]{String.valueOf(movieId)});
        db.close();

        ArrayList<Movie> list = getAllMovies();
        Log.d("dbAasif" , String.valueOf(list.size()));
        for(int i = 0 ; i < list.size(); ++i)
        {
            Log.d("dbAasif", list.get(i).getTitle());
        }


    }

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> movieList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + Params.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);
        
        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setId(cursor.getInt(cursor.getColumnIndex(Params.KEY_ID)));
                movie.setTitle(cursor.getString(cursor.getColumnIndex(Params.KEY_TITLE)));
                movie.setBackdropPath(cursor.getString(cursor.getColumnIndex(Params.KEY_BACKDROP_PATH)));
                movie.setOverview(cursor.getString(cursor.getColumnIndex(Params.KEY_OVERVIEW)));
                movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(Params.KEY_RELEASE_DATE)));
                movie.setPosterPath(cursor.getString(cursor.getColumnIndex(Params.KEY_POSTER_PATH)));
                movie.setPopularity(cursor.getDouble(cursor.getColumnIndex(Params.KEY_POPULARITY)));

                movieList.add(movie);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return movieList;
    }
}
