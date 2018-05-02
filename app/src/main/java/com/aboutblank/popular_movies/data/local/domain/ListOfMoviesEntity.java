package com.aboutblank.popular_movies.data.local.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(tableName = "list_movie",
        primaryKeys = {"listId", "movieId"},
        foreignKeys = {
                @ForeignKey(entity = MovieEntity.class,
                        parentColumns = "movieId",
                        childColumns = "movieId")
        })
public class ListOfMoviesEntity {
    @NonNull
    public final String listId;
    public final int movieId;

    public ListOfMoviesEntity(@NonNull String listId, int movieId) {
        this.listId = listId;
        this.movieId = movieId;
    }
}
