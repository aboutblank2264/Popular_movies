package com.aboutblank.popular_movies.data.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class ListOfMovieItems {
    @SerializedName("page")
    private int pageId = 0;
    @SerializedName("total_results")
    private int totalResults = 0;
    @SerializedName("results")
    private List<MovieItem> movies = Collections.emptyList();

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<MovieItem> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieItem> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "ListOfMovieItems{" +
                "pageId=" + pageId +
                ", totalResults=" + totalResults +
                ", movies=" + movies +
                '}';
    }
}
