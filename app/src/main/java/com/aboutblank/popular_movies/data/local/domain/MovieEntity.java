package com.aboutblank.popular_movies.data.local.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.aboutblank.popular_movies.data.domain.MovieItem;

@Entity(tableName = "movie_entity")
public class MovieEntity {

    @PrimaryKey
    public int movieId;
    @ColumnInfo(name = "popular_id")
    public int popularId;
    @ColumnInfo(name = "highest_id")
    public int highestRatedId;

    @Embedded
    public MovieItem movieItem;

    public MovieItem getMovieItem() {
        return movieItem;
    }
}
