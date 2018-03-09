package com.aboutblank.popular_movies.presentation.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboutblank.popular_movies.R;
import com.aboutblank.popular_movies.presentation.model.Movie;
import com.aboutblank.popular_movies.utils.ImageUtils;

import java.util.List;

/**
 * RecyclerView reference:
 * https://developer.android.com/guide/topics/ui/layout/recyclerview.html
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<Movie> movieList;
    private LayoutInflater inflater;


    public RecyclerViewAdapter(LayoutInflater inflater, List<Movie> movieList) {
        this.inflater = inflater;
        this.movieList = movieList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        ImageUtils.loadImageInto(inflater.getContext(), holder.getImageView(), movie.getPosterPath());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void update(@NonNull List<Movie> newList) {
        movieList.clear();
        movieList.addAll(newList);
        notifyDataSetChanged();
    }
}
