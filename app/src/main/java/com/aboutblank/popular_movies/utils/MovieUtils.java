package com.aboutblank.popular_movies.utils;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.data.domain.MovieItem;
import com.aboutblank.popular_movies.presentation.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieUtils {

    public static Movie entryToMovie(@NonNull MovieItem entry) {
        return new Movie(entry.getTitle(),
                entry.getPosterPath(),
                entry.getBackdropPath(),
                entry.getReleaseDate(),
                entry.getOverview(),
                entry.getVoteAverage());
    }

    public static List<Movie> entryListToMovieList(@NonNull List<MovieItem> entries) {
        List<Movie> movies = new ArrayList<>();

        for(MovieItem entry : entries) {
            movies.add(entryToMovie(entry));
        }

        return movies;
    }
    
    public static String toPercentage(double vote) {
        return String.valueOf((int) (vote * 10)) + "%";
    }
}
