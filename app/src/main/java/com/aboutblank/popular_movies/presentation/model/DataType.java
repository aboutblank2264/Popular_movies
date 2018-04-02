package com.aboutblank.popular_movies.presentation.model;

public enum DataType {
    POPULAR(1), HIGHEST_RATED(2), REVIEWS(3), VIDEOS(4);

    private int type;

    DataType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
