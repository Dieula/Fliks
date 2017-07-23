package com.example.eastcoastpawn.fliks.Adapers;

import com.example.eastcoastpawn.fliks.Moodels.Movie;
import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eastcoastpawn.fliks.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by East Coast Pawn on 7/20/2017.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie>{
    public static class ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Movie movie = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
        ivImage.setImageResource(0);


        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        String image;

        int orientation = convertView.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            image = movie.getPosterPath().toString();
            Picasso.with(getContext()).load(image).placeholder(R.drawable.player).transform(new RoundedCornersTransformation(14, 14)).into(ivImage);

            // ...
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            image = movie.getBackdrop_path().toString();
            Picasso.with(getContext()).load(image).placeholder(R.drawable.player).transform(new RoundedCornersTransformation(14, 14)).into(ivImage);
            // ...
        }


        return convertView;
    }    }