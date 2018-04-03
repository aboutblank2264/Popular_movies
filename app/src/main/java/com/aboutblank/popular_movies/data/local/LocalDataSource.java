package com.aboutblank.popular_movies.data.local;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.data.DataSource;
import com.aboutblank.popular_movies.data.local.domain.HighestRatedMoviesEntity;
import com.aboutblank.popular_movies.data.local.domain.MovieEntity;
import com.aboutblank.popular_movies.data.local.domain.PopularMoviesEntity;

import java.util.List;

public interface LocalDataSource extends DataSource {

    String HIGHEST_LIST_PREFIX = "h_";
    String POPULAR_LIST_PREFIX = "p_";

    interface SavePopularMoviesCallback {
        PopularMoviesEntity getPopularMoviesEntity();
        List<MovieEntity> getMovies();

        void onDataSaveFailure(String error);
    }

    interface SaveHighestRatedMoviesCallback {
        HighestRatedMoviesEntity getHighestRatedMoviesEntity();
        List<MovieEntity> getMovies();

        void onDataSaveFailure(String error);
    }

    interface SaveReviewsToMovieCallback {
        int movieId();
        List<String> getReviews();

        void onDataSaveFailure(String error);
    }

    interface SaveVideosToMovieCallback {
        int movieId();
        List<String> getVideos();

        void onDataSaveFailure(String error);
    }

    void saveHighestRatedMovies(@NonNull LocalDataSource.SaveHighestRatedMoviesCallback callBack);

    void savePopularMovies(@NonNull LocalDataSource.SavePopularMoviesCallback callBack);

    void saveMovieReviews(@NonNull LocalDataSource.SaveReviewsToMovieCallback callBack);

    void saveMovieVideos(@NonNull LocalDataSource.SaveVideosToMovieCallback callBack);

    String convertPageToListId(int pageId, String prefix);
}
