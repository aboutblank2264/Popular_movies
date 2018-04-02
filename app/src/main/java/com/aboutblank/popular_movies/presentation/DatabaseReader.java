package com.aboutblank.popular_movies.presentation;

import android.content.Context;

public class DatabaseReader {

    private final Context context;

    public DatabaseReader(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}
