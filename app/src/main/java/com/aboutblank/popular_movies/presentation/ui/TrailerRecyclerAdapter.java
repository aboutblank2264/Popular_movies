package com.aboutblank.popular_movies.presentation.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboutblank.popular_movies.R;
import com.aboutblank.popular_movies.presentation.model.MovieVideo;
import com.aboutblank.popular_movies.utils.MovieUtils;

import java.util.List;

public class TrailerRecyclerAdapter extends RecyclerView.Adapter<TrailerViewHolder>
        implements TrailerViewHolder.ItemClickedListener {

    private List<MovieVideo> movieVideoList;
    private LayoutInflater inflater;

    TrailerRecyclerAdapter(@NonNull LayoutInflater inflater, @NonNull List<MovieVideo> movieVideos) {
        this.movieVideoList = movieVideos;
        this.inflater = inflater;
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.video_view_item, parent, false);

        return new TrailerViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {
        holder.setTitle(movieVideoList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return movieVideoList.size();
    }

    @Override
    public void onClicked(int position) {
        MovieVideo video = movieVideoList.get(position);

        //Create intent to go to youtube.
        MovieUtils.startYoutubeIntent(inflater.getContext(), video.getId());
    }
}
