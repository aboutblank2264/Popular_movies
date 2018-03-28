package com.aboutblank.popular_movies.data.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class ListOfMovies {
    @SerializedName("page")
    private int pageId = 0;
    @SerializedName("total_results")
    private int totalResults = 0;
    @SerializedName("results")
    private List<MovieEntry> movies = Collections.emptyList();

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

    public List<MovieEntry> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieEntry> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "ListOfMovies{" +
                "pageId=" + pageId +
                ", totalResults=" + totalResults +
                ", movies=" + movies +
                '}';
    }
}
