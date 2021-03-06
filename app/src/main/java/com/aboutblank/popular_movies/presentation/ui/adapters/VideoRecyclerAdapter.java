package com.aboutblank.popular_movies.presentation.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboutblank.popular_movies.R;
import com.aboutblank.popular_movies.presentation.model.MovieVideo;
import com.aboutblank.popular_movies.utils.ImageUtils;
import com.aboutblank.popular_movies.utils.MovieUtils;

import java.util.List;

public class VideoRecyclerAdapter extends RecyclerView.Adapter<VideoViewHolder>
        implements VideoViewHolder.ItemClickedListener, IRecyclerAdapter<MovieVideo> {

    private List<MovieVideo> movieVideoList;
    private LayoutInflater inflater;

    public VideoRecyclerAdapter(@NonNull LayoutInflater inflater, @NonNull List<MovieVideo> movieVideos) {
        this.movieVideoList = movieVideos;
        this.inflater = inflater;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.video_view_item, parent, false);

        return new VideoViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        MovieVideo video = movieVideoList.get(position);
        ImageUtils.loadYoutubeIcon(inflater.getContext(),holder.getTrailerImageView(), video.getKey());
    }

    @Override
    public int getItemCount() {
        return movieVideoList.size();
    }

    @Override
    public void onClicked(int position) {
        MovieVideo video = movieVideoList.get(position);

        //Create intent to go to youtube.
        MovieUtils.startYoutubeIntent(inflater.getContext(), video.getKey());
    }

    @Override
    public void update(@NonNull List<MovieVideo> newList) {
        Log.d(VideoRecyclerAdapter.class.getSimpleName(), "Refreshing videos:");

        movieVideoList.clear();
        movieVideoList.addAll(newList);

        Log.d(VideoRecyclerAdapter.class.getSimpleName(), movieVideoList.toString());

        notifyDataSetChanged();
    }

    public static RecyclerView.ItemDecoration getItemDecoration(@NonNull Context context) {

        return null;
    }
}
