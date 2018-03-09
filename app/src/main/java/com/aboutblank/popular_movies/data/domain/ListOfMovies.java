package com.aboutblank.popular_movies.data.domain;

import com.aboutblank.popular_movies.presentation.model.Movie;

import java.util.Collections;
import java.util.List;

public class ListOfMovies {
    int pageId = 0;
    List<Movie> movies = Collections.emptyList();

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "ListOfMovies{" +
                "pageId=" + pageId +
                ", movies=" + movies +
                '}';
    }
}
