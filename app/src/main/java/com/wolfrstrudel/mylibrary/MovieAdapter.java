package com.wolfrstrudel.mylibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter
{
    Context context;
    LayoutInflater inflater;
    DBHandler db;
    ArrayList<Movie> movies;

    public MovieAdapter(Context context, LayoutInflater inflater)
    {
        this.context = context;
        this.inflater = inflater;
        db = new DBHandler(context);
        movies = db.getMovies();
    }

    public void updateMovieList()
    {
        movies = db.getMovies();
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        //return db.getMovieCount();
        return movies.size();
    }

    @Override
    public Movie getItem(int position)
    {
        //return db.getMovie(position);
        return movies.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        ViewHolder holder;
        if(view == null)
        {
            view = inflater.inflate(R.layout.row_movie, viewGroup, false);
            holder = new ViewHolder();
            holder.titleTextView = (TextView) view.findViewById(R.id.movie_title_year);

            view.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        Movie movie = getItem(position);
        holder.titleTextView.setText(movie.getTitle() + " (" +  movie.getYear() + ")" );

        return view;
    }

    private static class ViewHolder
    {
        public TextView titleTextView;
    }
}
