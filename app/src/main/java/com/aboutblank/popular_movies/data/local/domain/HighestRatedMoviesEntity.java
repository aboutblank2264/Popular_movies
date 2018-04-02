package com.aboutblank.popular_movies.data.local.domain;

import android.arch.persistence.room.Entity;

@Entity(tableName = "highest_rated")
public class HighestRatedMoviesEntity extends BaseMoviesEntity {
    public HighestRatedMoviesEntity(int pageId, String language, String region) {
        this.pageId = pageId;
        this.language = language;
        this.region = region;
    }
}
