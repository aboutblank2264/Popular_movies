package com.aboutblank.popular_movies.utils;

import android.arch.persistence.room.TypeConverter;

import com.aboutblank.popular_movies.presentation.model.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class MovieListTypeConverter {
    static Gson gson = new Gson();

    @TypeConverter
    public static List<Movie> stringToList(String str) {
        if(str == null) {
            return Collections.emptyList();
        }

        Type movieListType = new TypeToken<List<Movie>>() {}.getType();

        return gson.fromJson(str, movieListType);
    }

    @TypeConverter
    public static String listToString(List<Movie> movies) {
        return gson.toJson(movies);
    }
}
