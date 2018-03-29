package com.aboutblank.popular_movies.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.R;
import com.aboutblank.popular_movies.data.domain.MovieEntry;
import com.aboutblank.popular_movies.presentation.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieUtils {

    public static Movie entryToMovie(@NonNull MovieEntry entry) {
        return new Movie(entry.getTitle(),
                entry.getPosterPath(),
                entry.getBackdropPath(),
                entry.getReleaseDate(),
                entry.getOverview(),
                entry.getVoteAverage());
    }

    public static List<Movie> entryListToMovieList(@NonNull List<MovieEntry> entries) {
        List<Movie> movies = new ArrayList<>();

        for(MovieEntry entry : entries) {
            movies.add(entryToMovie(entry));
        }

        return movies;
    }
    
    public static Bundle movieToBundle(@NonNull Context context, @NonNull Movie movie) {
        Bundle bundle = new Bundle();

        bundle.putString(context.getString(R.string.bundle_image), movie.getPosterUrl());
        bundle.putString(context.getString(R.string.bundle_title), movie.getTitle());
        bundle.putString(context.getString(R.string.bundle_date), movie.getReleaseDate());
        bundle.putString(context.getString(R.string.bundle_description), movie.getOverview());
        bundle.putString(context.getString(R.string.bundle_votes), toPercentage(movie.getVote()));
        bundle.putString(context.getString(R.string.bundle_backdrop), String.valueOf(movie.getBackdrop()));

        return bundle;
    }

    private static String toPercentage(double vote) {
        return String.valueOf((int)(vote*10)) + "%";
    }
}
