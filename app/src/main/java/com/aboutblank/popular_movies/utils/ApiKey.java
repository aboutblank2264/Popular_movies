package com.aboutblank.popular_movies.utils;

import com.aboutblank.popular_movies.BuildConfig;

import java.io.IOException;

public class ApiKey {
    private final static String API_KEY = BuildConfig.API_KEY;

    public static String apiKey() throws IOException {
        return API_KEY;
    }
}
