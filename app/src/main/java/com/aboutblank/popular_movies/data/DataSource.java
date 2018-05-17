package com.aboutblank.popular_movies.data;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.presentation.model.DataType;
import com.aboutblank.popular_movies.presentation.model.Movie;
import com.aboutblank.popular_movies.presentation.model.MovieReview;
import com.aboutblank.popular_movies.presentation.model.MovieVideo;

import java.util.List;

/**
 * Interface for Data layer.
 * Contains callback to return data retrieved back to higher layers.
 */
public interface DataSource {

    interface LoadListOfDataCallBack<T> {
        MovieDbRequest getRequest();

        DataType getDataType();

        void onDataLoaded(List<T> list);

        void onDataNotAvailable(String error);
    }

    interface LoadGenreCallBack {
        String getLanguage();

        void onDataLoaded(SparseArray<String> genres);

        void onDataNotAvailable(String error);
    }

    interface LoadMovieCallback {
        int getMovieId();

        void onDataLoaded(Movie movieItem);

        void onDataNotAvailable(String error);
    }

    interface AddRemoveMovieFavoritesCallBack {
        int getMovieId();
        Boolean valueToUpdate();

        void onDataNotAvailable(String error);
    }

    interface CheckIfMovieIsFavoritedCallBack {
        int getMovieId();

        void onDataLoaded(boolean isFavorite);

        void onDataNotAvailable(String error);
    }

    void getHighestRatedMovies(@NonNull LoadListOfDataCallBack<Movie> callBack);

    void getPopularMovies(@NonNull LoadListOfDataCallBack<Movie> callBack);

    void getListOfData(@NonNull LoadListOfDataCallBack<?> callBack);

    void getMovie(@NonNull LoadMovieCallback callback);

    void getMovieReviews(@NonNull LoadListOfDataCallBack<MovieReview> callBack);

    void getMovieVideos(@NonNull LoadListOfDataCallBack<MovieVideo> callBack);

    void getListOfGenres(@NonNull LoadGenreCallBack callBack);

    void addMovieToFavorite(@NonNull AddRemoveMovieFavoritesCallBack callBack);

    void checkIfMovieIsFavorited(@NonNull CheckIfMovieIsFavoritedCallBack callBack);

    void invalidateCaches();
}
