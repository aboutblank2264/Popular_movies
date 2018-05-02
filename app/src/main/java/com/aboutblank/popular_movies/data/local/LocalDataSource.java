package com.aboutblank.popular_movies.data.local;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.aboutblank.popular_movies.data.DataSource;
import com.aboutblank.popular_movies.data.local.domain.HighestRatedMoviesEntity;
import com.aboutblank.popular_movies.data.local.domain.MovieEntity;
import com.aboutblank.popular_movies.data.local.domain.PopularMoviesEntity;

import java.util.List;

public interface LocalDataSource extends DataSource {

    String HIGHEST_LIST_PREFIX = "h_";
    String POPULAR_LIST_PREFIX = "p_";

    interface SaveMovieCallback {
        MovieEntity getMovieEntity();

        void onDataSaveFailure(String error);
    }

    interface SaveGenresCallBack {
        String getLanguage();
        SparseArray<String> getGenres();

        void onDataSaveFailure(String error);
    }

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
        int getMovieId();
        List<String> getReviews();

        void onDataSaveFailure(String error);
    }

    interface SaveVideosToMovieCallback {
        int getMovieId();
        List<String> getVideos();

        void onDataSaveFailure(String error);
    }

    void saveMovie(@NonNull LocalDataSource.SaveMovieCallback callback);

    void saveGenres(@NonNull LocalDataSource.SaveGenresCallBack callBack);

    void saveHighestRatedMovies(@NonNull LocalDataSource.SaveHighestRatedMoviesCallback callBack);

    void savePopularMovies(@NonNull LocalDataSource.SavePopularMoviesCallback callBack);

    void saveMovieReviews(@NonNull LocalDataSource.SaveReviewsToMovieCallback callBack);

    void saveMovieVideos(@NonNull LocalDataSource.SaveVideosToMovieCallback callBack);

    String convertPageToListId(int pageId, String prefix);

    void close();
}
