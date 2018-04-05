package com.aboutblank.popular_movies.data.local.domain;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.aboutblank.popular_movies.data.domain.MovieItem;
import com.aboutblank.popular_movies.utils.StringListTypeConverter;

import java.util.List;

@Entity(tableName = "movie_entity")
@TypeConverters(StringListTypeConverter.class)
public class MovieEntity {

    @PrimaryKey
    public int movieId;

    @Embedded
    public MovieItem movieItem;

    public boolean favorite;
    public List<String> reviews;
    public List<String> videoUrls;

    @Override
    public String toString() {
        return "MovieEntity{" +
                "movieId=" + movieId +
                ", movieItem=" + movieItem +
                ", favorite=" + favorite +
                ", reviews=" + reviews +
                ", videoUrls=" + videoUrls +
                '}';
    }
}
