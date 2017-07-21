package com.example.eastcoastpawn.fliks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.eastcoastpawn.fliks.Adapers.MovieArrayAdapter;
import com.example.eastcoastpawn.fliks.Moodels.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.entity.mime.Header;

public class MovieActivity extends AppCompatActivity {

    ArrayList<Movie>movies;
    MovieArrayAdapter movieAdapter;
    ListView IvItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        IvItems = (ListView) findViewById(R.id.IvMovies);
        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this, movies);
        IvItems.setAdapter(movieAdapter);

        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                JSONArray movieJsonResults = null;
                try{
                    movieJsonResults = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJsonResults));
                    movieAdapter.notifyDataSetChanged();
                    Log.d("DEBUG", movies.toString());
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
 public void onFaillure(int statusCode, Header[] headers,String responseString,Throwable throwable)
 {
     super.onFailure(statusCode, (cz.msebera.android.httpclient.Header[]) headers, responseString, throwable);
 }
        });
    }
}
