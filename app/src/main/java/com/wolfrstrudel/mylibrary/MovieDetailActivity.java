package com.wolfrstrudel.mylibrary;

/**
 * Created by Wolfrstrudel on 10/28/2016.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MovieDetailActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    TextView directorTextView, actorsTextView, formatTextView;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_moviedetails);
        setSupportActionBar(toolbar);

        //Get the selected movie from the movie list
        Intent intent = getIntent();
        movie = intent.getParcelableExtra("movie");

        //Set the title to the title of the movie and its year
        getSupportActionBar().setTitle(movie.getTitle() + " (" + movie.getYear() + ")");

        //This is to test out the boxart
        if(movie.getTitle().equals("Jurassic Park"))
        {
            ImageView boxartView = (ImageView) findViewById(R.id.boxart_image);
            boxartView.setImageResource(R.drawable.jurassic_park);
            boxartView.setVisibility(View.VISIBLE);
        }

        //Creates the director, actors, and format list views.
        createListView(movie.getDirector(),(LinearLayout) findViewById(R.id.listview_directors));
        createListView(movie.getActors(),(LinearLayout) findViewById(R.id.listview_actors));
        createListView(movie.getFormat(),(LinearLayout) findViewById(R.id.listview_format));
    }

    //Creates the options in the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_movie_details, menu);
        return true;
    }

    //Decides what happens when one of the options in the toolbar is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        deleteOptionClicked();
        return true;
    }

    //Creates a listview from a list of elemenents in a string separated by a comma
    private void createListView(String unseparatedList, LinearLayout view)
    {
        String[] items = unseparatedList.split(",");
        for(int i = 0; i < items.length; i++)
        {
            String detail = items[i];
            TextView vi = (TextView) this.getLayoutInflater().inflate(R.layout.details, null);
            vi.setText(detail);
            view.addView(vi);
        }
    }

    private void deleteOptionClicked()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this movie?")
                .setCancelable(false)
                .setTitle("Confirm")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHandler dbHandler = new DBHandler(getBaseContext());
                        dbHandler.deleteMovie(movie);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {

    }
}
