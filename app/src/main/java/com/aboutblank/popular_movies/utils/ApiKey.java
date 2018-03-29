package com.aboutblank.popular_movies.utils;

import com.aboutblank.popular_movies.BuildConfig;

public class ApiKey {
    private final static String API_KEY = BuildConfig.API_KEY;

    public static String apiKey() {
        return API_KEY;
    }
}
