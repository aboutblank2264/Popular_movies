package com.aboutblank.popular_movies.data.local.domain;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class HighestListMovieJoin implements ListMovieJoin {

    @Embedded
    HighestRatedMoviesEntity highestRatedMoviesEntity;

    @Relation(parentColumn = "pageId", entityColumn = "highest_id")
    public List<MovieEntity> movies;
}
