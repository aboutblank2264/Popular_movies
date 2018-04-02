package com.aboutblank.popular_movies.data.remote;

import android.util.SparseArray;

import com.aboutblank.popular_movies.data.domain.Genre;

import java.util.List;

public class MappingUtils {

    public static SparseArray<String> ListGenreToMap(List<Genre> genres) {
        SparseArray<String> map = new SparseArray<>();

        for(Genre genre : genres) {
            map.put(genre.getId(), genre.getName());
        }

        return map;
    }
}
