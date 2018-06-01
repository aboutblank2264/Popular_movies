package com.aboutblank.popular_movies.presentation.model;

import android.support.annotation.IntDef;

@IntDef({DataType.POPULAR, DataType.HIGHEST_RATED, DataType.REVIEWS, DataType.VIDEOS, DataType.FAVORITED})
public @interface DataType {
    int POPULAR = 1;
    int HIGHEST_RATED = 2;
    int REVIEWS = 3;
    int VIDEOS = 4;
    int FAVORITED = 5;

}
