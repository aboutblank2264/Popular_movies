package com.aboutblank.popular_movies.presentation.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboutblank.popular_movies.R;
import com.aboutblank.popular_movies.presentation.model.Movie;
import com.aboutblank.popular_movies.utils.ImageUtils;
import com.aboutblank.popular_movies.utils.MovieUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView reference:
 * https://developer.android.com/guide/topics/ui/layout/recyclerview.html
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>
        implements RecyclerViewHolder.ItemClickedListener {

    private List<Movie> movieList;
    private LayoutInflater inflater;

    public RecyclerViewAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
        this.movieList = new ArrayList<>();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new RecyclerViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        ImageUtils.loadImageInto(inflater.getContext(), holder.getImageView(), movie.getPosterUrl());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void update(@NonNull List<Movie> newList) {
        Log.d(RecyclerViewAdapter.class.getSimpleName(), "Refreshing listed movies:");

        movieList.clear();
        movieList.addAll(newList);

        Log.d(RecyclerViewAdapter.class.getSimpleName(), movieList.toString());

        notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        Movie movie = movieList.get(position);
        Intent intent = new Intent(inflater.getContext(), DetailsActivity.class);

        intent.putExtra(inflater.getContext().getString(R.string.bundle_movie), MovieUtils.movieToBundle(inflater.getContext(), movie));

        inflater.getContext().startActivity(intent);
    }
}
