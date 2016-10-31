package com.wolfrstrudel.mylibrary;

import android.os.Parcel;
import android.os.Parcelable;

//Creates a parcelable movie object
public class Movie implements Parcelable
{
    private long id;
    private String title;
    private String year;
    private String boxArt;
    private String director;
    private String actors;
    private String format;
    private String genres;

    public Movie()
    {

    }

    public Movie(String title, String year, String boxArt, String director, String actors, String format, String genres)
    {
        this.title = title;
        this.year = year;
        this.boxArt = boxArt;
        this.director = director;
        this.actors = actors;
        this.format = format;
        this.genres = genres;
    }

    //Turns the parcelable object into a movie
    public Movie(Parcel in) {
        id = in.readLong();
        title = in.readString();
        year = in.readString();
        director = in.readString();
        actors = in.readString();
        format = in.readString();
        genres = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //Turns the movie into a parcelable object for the database
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeString(year);
        parcel.writeString(director);
        parcel.writeString(actors);
        parcel.writeString(format);
        parcel.writeString(genres);
    }

    //Creates movie/s from the parcel
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public Movie createFromParcel(Parcel in)
        {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size)
        {
            return new Movie[size];
        }
    };

    //Setters and Getters
    public void setId(long id) {this.id = id;}

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public void setBoxArt(String boxArt)
    {
        this.boxArt = boxArt;
    }

    public void setDirector(String director)
    {
        this.director = director;
    }

    public void setActors(String actors)
    {
        this.actors = actors;
    }

    public void setGenres(String genres)
    {
        this.genres = genres;
    }

    public void setFormat(String format)
    {
        this.format = format;
    }

    public long getId() { return id; }

    public String getTitle()
    {
        return title;
    }

    public String getYear()
    {
        return year;
    }

    public String getBoxArt()
    {
        return boxArt;
    }

    public String getDirector()
    {
        return director;
    }

    public String getActors()
    {
        return actors;
    }

    public String getFormat()
    {
        return format;
    }

    public String getGenres()
    {
        return genres;
    }
}
