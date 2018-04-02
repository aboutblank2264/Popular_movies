package com.aboutblank.popular_movies.data.domain;

import com.aboutblank.popular_movies.presentation.model.MovieVideo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListOfMovieVideos {
    private String id;
    @SerializedName("results")
    private List<MovieVideo> videos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                "id='" + id + '\'' +
                ", videos=" + videos +
                '}';
    }
}
