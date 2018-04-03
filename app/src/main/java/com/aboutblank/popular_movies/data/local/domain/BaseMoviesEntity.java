package com.aboutblank.popular_movies.data.local.domain;

import android.arch.persistence.room.PrimaryKey;

public abstract class BaseMoviesEntity {

    @PrimaryKey
    public int pageId;
    public String language;
    public String region;

    public BaseMoviesEntity(int pageId, String language, String region) {
        this.pageId = pageId;
        this.language = language;
        this.region = region;
    }
}
