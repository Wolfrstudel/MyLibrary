package com.wolfrstrudel.mylibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class MovieAddActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_add);

        createYearSpinner();
        createGenreSpinner();
        //createField((LinearLayout)findViewById(R.id.addMovieDirectors));
        //createField((LinearLayout)findViewById(R.id.addMovieActors));
    }

    private void createYearSpinner()
    {
        ArrayList<String> years = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int firstFilmYear = 1888;
        for(int i = currentYear; i >= firstFilmYear; i--)
        {
            years.add(i+"");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        Spinner yearSelector = (Spinner) findViewById(R.id.addMovieYear);
        yearSelector.setAdapter(adapter);
    }

    private void createGenreSpinner()
    {
        ArrayList<String> genres = new ArrayList<>(Arrays.asList("Action", "Adventure", "Comedy",
                "Crime and Gangster", "Drama", "Epic/Historical", "Horror", "Musical/Dance",
                "Science Fiction", "War", "Western"));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genres);
        Spinner genreSelector = (Spinner) findViewById(R.id.addMovieGenres);
        genreSelector.setAdapter(adapter);
    }
    private void createField(final LinearLayout li)
    {
        final LinearLayout newView = (LinearLayout) this.getLayoutInflater().inflate(R.layout.field, null);
        Button addButton = (Button) newView.findViewById(R.id.addField);
        Button remButton = (Button) newView.findViewById(R.id.removeField);
        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                createField(li);
                ViewSwitcher switcher = (ViewSwitcher) v.getParent();
                switcher.showNext();
            }
        });

        remButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                li.removeView(newView);
            }
        });
        li.addView(newView);
    }
}
