package com.aboutblank.popular_movies.data.local.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.aboutblank.popular_movies.utils.StringListTypeConverter;

@Entity(tableName = "movie_entity")
@TypeConverters(StringListTypeConverter.class)
public class MovieEntity {

    @PrimaryKey
    public int movieId;

    public boolean favorite;

    public MovieEntity(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "getMovieId=" + movieId +
                ", favorite=" + favorite +
                '}';
    }
}
