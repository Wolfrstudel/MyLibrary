package com.wolfrstrudel.mylibrary;

/**
 * Created by Wolfrstrudel on 10/28/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MovieListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView movieListView;
    MovieAdapter mAdapter;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        //Setup the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_movielist);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Movies");

        //Setup the database
        movieListView = (ListView) findViewById(R.id.listview_movie);
        db = new DBHandler(this);

        //Will be removed when database is working correctly
        db.deleteDatabase();
        addMovies(db);

        //Sets up the movie layout adapter for each row in the list
        mAdapter = new MovieAdapter(this, getLayoutInflater());
        movieListView.setAdapter(mAdapter);
        movieListView.setOnItemClickListener(this);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mAdapter.updateMovieList();
    }
    //Creates the options in the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_movie_list, menu);
        return true;
    }

    //Decides what happens when one of the options in the toolbar is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //Need to check which item was clicked. IE this is when the add button is clicked
        Intent addMovieIntent = new Intent(this, MovieAddActivity.class);
        startActivity(addMovieIntent);
        return true;
    }

    //Figures out which movie was clicked in the list
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
    {
        Movie movie = mAdapter.getItem(position);
        Intent movieDetailIntent = new Intent(this, MovieDetailActivity.class);

        movieDetailIntent.putExtra("movie", movie);

        startActivity(movieDetailIntent);
    }

    private void addMovies(DBHandler db)
    {

        db.addMovie(new Movie("Jurassic Park", "1993", null, "Steven Spielberg",
                "Sam Neil,Laura Dern,Jeff Goldblum,Richard Attenborough,Bob Peck,Martin Ferrero,B.D. Wong,Samuel L. Jackson,Wayne Knight,Joseph Mazzello,Ariana Richards","DVD", "Adventure"));
        db.addMovie(new Movie("Titanic", "1997", null, "James Cameron", "Leonardo DiCaprio", "Tape", null));
        db.addMovie(new Movie("The Incredibles", "2004", null, "Brad Bird", "Craig T. Nelson", "DVD", null));
        db.addMovie(new Movie("Finding Nemo", "2003", null, "Andrew Stanton", "Ellen Degeneres", "DVD", null));
        db.addMovie(new Movie("Zootopia", "2016", null, "Byron Howard", "Jason Batemen", "DVD", null));
        db.addMovie(new Movie("Captain America: The First Avenger", "2011", null, "Joe Johnston", "Chris Evans", "DVD", null));
        db.addMovie(new Movie("Back to the Future", "1985", null, "Robert Zemeckis", "Michael J. Fox", "Tape", null));
        db.addMovie(new Movie("Iron Man", "2008", null, "Shane Black", "Robert Downey Jr.", "DVD", null));
        db.addMovie(new Movie("Dr. Strange", "2016", null, "Scott Derrickson", "Benedict Cumberbatch", "DVD", null));
        db.addMovie(new Movie("Guardians of the Galaxy", "2014", null, "James Gunn", "Chris Pratt", "DVD", null));
        db.addMovie(new Movie("Up", "2009", null, "Brad Bird", "Ed Asner", "DVD", null));
        db.addMovie(new Movie("Thor", "2011", null,"Kenneth Branagh", "Chris Hemsworth,NataliePortman", "DVD", null));
    }
}

