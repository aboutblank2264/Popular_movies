package com.aboutblank.popular_movies.utils;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class IntListTypeConverter {

    private static final Type type = new TypeToken<List<Integer>>() {}.getType();

    @TypeConverter
    public static List<Integer> stringToList(String json) {
        Gson gson = new Gson();

        return gson.fromJson(json, type);
    }

    @TypeConverter
    public static String listToString(List<Integer> list) {
        Gson gson = new Gson();

        return gson.toJson(list, type);
    }
}
