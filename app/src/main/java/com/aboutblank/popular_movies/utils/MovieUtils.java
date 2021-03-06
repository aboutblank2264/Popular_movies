package com.aboutblank.popular_movies.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.aboutblank.popular_movies.data.domain.MovieItem;
import com.aboutblank.popular_movies.presentation.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieUtils {

    private final static String YOUTUBE_APP_STRING = "vnd.youtube:";
    private final static String YOUTUBE_WEB_STRING = "https://www.youtube.com/watch?v=";

    public static Movie entryToMovie(@NonNull MovieItem entry) {
        return new Movie(
                String.valueOf(entry.getId()),
                entry.getTitle(),
                entry.getPosterPath(),
                entry.getBackdropPath(),
                entry.getReleaseDate(),
                entry.getOverview(),
                entry.getVoteAverage(),
                entry.getGenreIds());
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

    public static void startYoutubeIntent(@NonNull Context context, @NonNull String videoId) {
        Log.d("youtube url", YOUTUBE_WEB_STRING + videoId);

        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_APP_STRING + videoId)));
        } catch(ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_WEB_STRING + videoId)));
        }
    }
}
