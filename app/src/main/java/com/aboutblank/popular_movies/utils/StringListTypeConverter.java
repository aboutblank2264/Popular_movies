package com.aboutblank.popular_movies.utils;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class StringListTypeConverter {

    private static final Type type = new TypeToken<List<String>>() {}.getType();

    @TypeConverter
    public static List<String> stringToList(String json) {
        Gson gson = new Gson();

        return gson.fromJson(json, type);
    }

    @TypeConverter
    public static String listToString(List<String> list) {
        Gson gson = new Gson();

        return gson.toJson(list, type);
    }
}
