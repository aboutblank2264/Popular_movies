package com.aboutblank.popular_movies.data.domain;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.utils.GenreListTypeConverter;

import java.util.List;

@Entity(tableName = "genres")
@TypeConverters(GenreListTypeConverter.class)
public class ListOfGenres {
    @NonNull
    @PrimaryKey
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