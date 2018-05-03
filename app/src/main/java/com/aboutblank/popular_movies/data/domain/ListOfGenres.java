package com.aboutblank.popular_movies.data.domain;


import android.support.annotation.NonNull;

import java.util.List;

public class ListOfGenres {
    private String language;
    private List<Genre> genres;

    public ListOfGenres(@NonNull String language, List<Genre> genres) {
        this.language = language;
        this.genres = genres;
    }

    @NonNull
    public String getLanguage() {
        return language;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "ListOfGenres{" +
                "language='" + language + '\'' +
                ", genres=" + genres +
                '}';
    }
}