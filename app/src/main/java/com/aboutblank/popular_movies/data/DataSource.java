package com.aboutblank.popular_movies.data;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.presentation.model.MovieReview;
import com.aboutblank.popular_movies.presentation.model.MovieVideo;
import com.aboutblank.popular_movies.presentation.model.Movie;

import java.util.List;
import java.util.Map;

/**
 * Interface for Data layer.
 * Contains callback to return data retrieved back to higher layers.
 */
public interface DataSource {

    interface LoadMovieDataCallBack {

        MovieDbRequest getRequest();

        void onDataLoaded(List<Movie> returnValue);

        void onDataNotAvailable(String error);
    }

    interface LoadGenreCallBack {
        String getLanguage();

        void onDataLoaded(Map<Integer, String> genres);

        void onDataNotAvailable(String error);
    }

    interface LoadMovieReviewCallBack {
        MovieDbRequest getRequest();

        void onDataLoaded(List<MovieReview> reviews);

        void onDataNotAvailable(String error);
    }

    interface LoadMovieVideosCallBack {
        MovieDbRequest getRequest();

        void onDataLoaded(List<MovieVideo> videos);

        void onDataNotAvailable(String error);
    }

    interface LoadListOfDataCallBack<T> {
        MovieDbRequest getRequest();

        void onDataLoaded(List<T> list);

        void onDataNotAvailable(String error);
    }

    void getHighestRatedMovies(@NonNull LoadMovieDataCallBack callBack);

    void getPopularMovies(@NonNull LoadMovieDataCallBack callBack);

    void getMovieReviews(@NonNull LoadMovieReviewCallBack callBack);

    void getMovieVideos(@NonNull LoadMovieVideosCallBack callBack);

    void getListOfGenres(@NonNull LoadGenreCallBack callBack);

    void getListOfData(@NonNull LoadListOfDataCallBack<?> callBack);
}
