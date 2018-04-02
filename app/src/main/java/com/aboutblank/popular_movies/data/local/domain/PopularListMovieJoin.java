package com.aboutblank.popular_movies.data.local.domain;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class PopularListMovieJoin implements ListMovieJoin {

    @Embedded
    PopularMoviesEntity popularMoviesEntity;

    @Relation(parentColumn = "pageId", entityColumn = "popular_id")
    public List<MovieEntity> movies;
}
