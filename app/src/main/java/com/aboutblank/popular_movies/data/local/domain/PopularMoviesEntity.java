package com.aboutblank.popular_movies.data.local.domain;

import android.arch.persistence.room.Entity;

@Entity(tableName = "popular")
public class PopularMoviesEntity extends BaseMoviesEntity {
    public PopularMoviesEntity(int pageId, String language, String region) {
        super(pageId, language, region);
    }
}
