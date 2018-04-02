package com.aboutblank.popular_movies.data.local.domain;

import android.arch.persistence.room.PrimaryKey;

public abstract class BaseMoviesEntity {
    
    @PrimaryKey(autoGenerate = true)
    public long pageId;
    public String language;
    public String region;
}
