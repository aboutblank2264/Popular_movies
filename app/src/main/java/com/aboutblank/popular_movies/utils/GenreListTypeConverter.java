package com.aboutblank.popular_movies.utils;

import android.arch.persistence.room.TypeConverter;

import com.aboutblank.popular_movies.data.domain.Genre;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class GenreListTypeConverter {
    private static final Type type = new TypeToken<List<Genre>>() {}.getType();

    @TypeConverter
    public static List<Genre> stringToList(String json) {
        Gson gson = new Gson();

        return gson.fromJson(json, type);
    }

    @TypeConverter
    public static String listToString(List<Genre> list) {
        Gson gson = new Gson();

        return gson.toJson(list, type);
    }
}
