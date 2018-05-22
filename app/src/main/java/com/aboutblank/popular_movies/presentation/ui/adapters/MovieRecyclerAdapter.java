package com.aboutblank.popular_movies.presentation.ui.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aboutblank.popular_movies.R;
import com.aboutblank.popular_movies.presentation.model.Movie;
import com.aboutblank.popular_movies.presentation.ui.DetailsActivity;
import com.aboutblank.popular_movies.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecyclerView reference:
 * https://developer.android.com/guide/topics/ui/layout/recyclerview.html
 */
public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder>
        implements MovieRecyclerAdapter.MovieViewHolder.ItemClickedListener, AbstractRecyclerAdapter<Movie> {

    private List<Movie> movieList;
    private LayoutInflater inflater;

    public MovieRecyclerAdapter(LayoutInflater inflater) {
        this.movieList = new ArrayList<>();
        this.inflater = inflater;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new MovieViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        ImageUtils.loadImageInto(inflater.getContext(), holder.getImageView(), movie.getPosterUrl());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    @Override
    public void onClicked(int position) {
        Movie movie = movieList.get(position);
        Intent intent = new Intent(inflater.getContext(), DetailsActivity.class);

        intent.putExtra(inflater.getContext().getString(R.string.bundle_movie), movie);

        inflater.getContext().startActivity(intent);
    }

    @Override
    public void update(@NonNull List<Movie> newList) {
        Log.d(MovieRecyclerAdapter.class.getSimpleName(), "Refreshing listed movies:");

        movieList.clear();
        movieList.addAll(newList);

        Log.d(MovieRecyclerAdapter.class.getSimpleName(), movieList.toString());

        notifyDataSetChanged();
    }

    class MovieViewHolder extends AbstractViewHolder {
        @BindView(R.id.item_image)
        ImageView imageView;

        MovieViewHolder(View view, ItemClickedListener itemClickedListener) {
            super(view, itemClickedListener);
            ButterKnife.bind(this, view);
        }

        ImageView getImageView() {
            return imageView;
        }
    }
}
