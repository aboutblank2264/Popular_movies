package com.aboutblank.popular_movies.data.domain;

import com.aboutblank.popular_movies.presentation.model.MovieVideo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListOfMovieVideos {
    @SerializedName("id")
    private String movieId;
    @SerializedName("results")
    private List<MovieVideo> videos;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public List<MovieVideo> getVideos() {
        return videos;
    }

    public void setVideos(List<MovieVideo> videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "ListOfMovieVideos{" +
                "id='" + movieId + '\'' +
                ", videos=" + videos +
                '}';
    }
}
