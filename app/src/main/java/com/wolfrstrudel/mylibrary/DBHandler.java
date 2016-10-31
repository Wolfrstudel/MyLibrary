package com.wolfrstrudel.mylibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Console;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper
{
    //Database version and name
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "collectionInfo";

    //Movie table
    public static final String TABLE_MOVIES = "movies";
    //Movie Columns
    private static final String MOVIE_KEY_ID = "id";
    private static final String MOVIE_KEY_TITLE = "title";
    private static final String MOVIE_KEY_YEAR = "year";
    private static final String MOVIE_KEY_BOXART = "boxart";
    private static final String MOVIE_KEY_DIRECTOR = "director";
    private static final String MOVIE_KEY_ACTORS = "actors";
    private static final String MOVIE_KEY_FORMATS = "formats";
    private static final String MOVIE_KEY_GENRES = "genres";

    public DBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_MOVIES + "(" + MOVIE_KEY_ID + " INTEGER PRIMARY KEY,"
                + MOVIE_KEY_TITLE + " TEXT," + MOVIE_KEY_YEAR + " TEXT," + MOVIE_KEY_BOXART + " BLOB,"
                + MOVIE_KEY_DIRECTOR + " TEXT," + MOVIE_KEY_ACTORS + " TEXT," + MOVIE_KEY_FORMATS + " TEXT,"
                + MOVIE_KEY_GENRES + " TEXT" + ")";
        db.execSQL(CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(db);
    }

    public void addMovie(Movie movie)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MOVIE_KEY_TITLE, movie.getTitle());
        values.put(MOVIE_KEY_YEAR, movie.getYear());
        values.put(MOVIE_KEY_BOXART, movie.getBoxArt());
        values.put(MOVIE_KEY_DIRECTOR, movie.getDirector());
        values.put(MOVIE_KEY_ACTORS, movie.getActors());
        values.put(MOVIE_KEY_FORMATS, movie.getFormat());
        values.put(MOVIE_KEY_GENRES, movie.getGenres());
        db.insert(TABLE_MOVIES, null, values);
    }

    public void deleteMovie(Movie movie)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = MOVIE_KEY_ID + " LIKE ?";
        String[] selectionArgs = { "" + movie.getId()};
        db.delete(TABLE_MOVIES, selection, selectionArgs);
    }
    public void deleteDatabase()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOVIES,null,null);
    }

    public int getMovieCount()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MOVIES, null);
        return cursor.getCount();
    }

    public ArrayList<Movie> getMovies()
    {
        ArrayList<Movie> movieList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_MOVIES, null );
        int i = 0;
        if(c.moveToFirst())
        {
            do
            {
                Movie movie = new Movie();
                movie.setId(c.getLong(c.getColumnIndex(MOVIE_KEY_ID)));
                movie.setTitle(c.getString(c.getColumnIndex(MOVIE_KEY_TITLE)));
                movie.setYear(c.getString(c.getColumnIndex(MOVIE_KEY_YEAR)));
                movie.setBoxArt(c.getString(c.getColumnIndex(MOVIE_KEY_BOXART)));
                movie.setDirector(c.getString(c.getColumnIndex(MOVIE_KEY_DIRECTOR)));
                movie.setActors(c.getString(c.getColumnIndex(MOVIE_KEY_ACTORS)));
                movie.setFormat(c.getString(c.getColumnIndex(MOVIE_KEY_FORMATS)));
                movie.setGenres(c.getString(c.getColumnIndex(MOVIE_KEY_GENRES)));
                movieList.add(movie);
                i++;
            }
            while(c.moveToNext());
        }
        return movieList;
    }
        /*public Movie getMovie(int position)
    {
        Movie movie = new Movie();

        //Position starts at 0 for a list, but SQLite rows start at 1
        movie.setId(position + 1);

        String[] movieColumns = new String[] {"title", "year", "boxart", "director",
                "actors", "format", "genres"};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor  = db.query(TABLE_MOVIES, movieColumns, MOVIE_KEY_ID + "=?",
                new String[]{movie.getId()+""}, null, null, null);

        cursor.moveToFirst();

        movie.setTitle(cursor.getString(cursor.getColumnIndex("title")));
        movie.setYear(cursor.getString(cursor.getColumnIndex("year")));
        movie.setBoxArt(null);
        movie.setDirector(cursor.getString(cursor.getColumnIndex("director")));
        movie.setActors(cursor.getString(cursor.getColumnIndex("actors")));
        movie.setFormat(cursor.getString(cursor.getColumnIndex("format")));
        movie.setGenres(cursor.getString(cursor.getColumnIndex("genres")));
        return movie;
    }*/
}
