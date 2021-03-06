package com.example.eastcoastpawn.fliks.Moodels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by East Coast Pawn on 7/20/2017.
 */

public class Movie {


    public String getPosterPath(){
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);

    }

    public String getBackdrop_path() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdrop_path);
    }

    public String getOriginalTitle()
    {
        return originalTitle;
    }
    public String getOverview()

    {
        return overview;
    }
    String posterPath;
    String originalTitle;
    String overview;
    String backdrop_path;

    public Movie(JSONObject jsonObject) throws JSONException
    {
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backdrop_path = jsonObject.getString("backdrop_path");
    }
    public static ArrayList<Movie> fromJSONArray (JSONArray array)
    {
        ArrayList<Movie> results = new ArrayList<>();
        for (int x= 0; x < array.length(); x++)
        {
            try
            {
                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}