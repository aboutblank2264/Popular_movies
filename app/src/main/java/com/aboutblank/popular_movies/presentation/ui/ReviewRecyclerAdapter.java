package com.aboutblank.popular_movies.presentation.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboutblank.popular_movies.R;
import com.aboutblank.popular_movies.presentation.model.MovieReview;
import com.aboutblank.popular_movies.utils.MovieUtils;

import java.util.List;

public class ReviewRecyclerAdapter extends RecyclerView.Adapter<ReviewViewHolder>
        implements ReviewViewHolder.ItemClickedListener, AbstractRecyclerAdapter<MovieReview>  {

    private List<MovieReview> movieReviewList;
    private LayoutInflater inflater;

    public ReviewRecyclerAdapter(@NonNull LayoutInflater inflater, @NonNull List<MovieReview> movieReviewList) {
        this.movieReviewList = movieReviewList;
        this.inflater = inflater;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.review_view_item, parent, false);

        return new ReviewViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        //TODO SPANS
        holder.setReviewText(movieReviewList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return movieReviewList.size();
    }

    @Override
    public void onClicked(int position) {
        MovieUtils.startWebIntent(inflater.getContext(), movieReviewList.get(position).getUrl());
    }

    @Override
    public void update(@NonNull List<MovieReview> newList) {
        Log.d(ReviewRecyclerAdapter.class.getSimpleName(), "Refreshing reviews:");

        movieReviewList.clear();
        movieReviewList.addAll(newList);

        Log.d(ReviewRecyclerAdapter.class.getSimpleName(), movieReviewList.toString());

        notifyDataSetChanged();
    }
}
