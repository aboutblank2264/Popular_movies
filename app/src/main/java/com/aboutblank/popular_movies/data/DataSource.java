package com.aboutblank.popular_movies.data;

import android.support.annotation.NonNull;

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

    interface CallBack {
        void onDataNotAvailable(String error);
    }

    interface LoadListOfDataCallBack<T> extends CallBack {
        MovieDbRequest getRequest();

        DataType getDataType();

        void onDataLoaded(List<T> list);
    }

    interface LoadMovieCallBack extends CallBack {
        int getMovieId();

        void onDataLoaded(Movie movieItem);
    }

    interface AddRemoveMovieFavoritesCallBack extends CallBack {
        int getMovieId();
        boolean valueToUpdate();
    }

    interface CheckIfMovieIsFavoritedCallBack extends CallBack {
        int getMovieId();

        void onDataLoaded(boolean isFavorite);
    }

    interface GetDataForFavoritedMoviesCallBack extends CallBack {
        List<Integer> getMovieIds();

        void setMovieIds(List<Integer> movieIds);

        void onDataLoaded(List<Movie> movies);
    }

    void getHighestRatedMovies(@NonNull LoadListOfDataCallBack<Movie> callBack);

    void getPopularMovies(@NonNull LoadListOfDataCallBack<Movie> callBack);

    void getFavoritedMovies(@NonNull GetDataForFavoritedMoviesCallBack callBack);

    void getListOfData(@NonNull LoadListOfDataCallBack<?> callBack);

    void getMovie(@NonNull LoadMovieCallBack callback);

    void getMovieReviews(@NonNull LoadListOfDataCallBack<MovieReview> callBack);

    void getMovieVideos(@NonNull LoadListOfDataCallBack<MovieVideo> callBack);

    void addMovieToFavorite(@NonNull AddRemoveMovieFavoritesCallBack callBack);

    void checkIfMovieIsFavorited(@NonNull CheckIfMovieIsFavoritedCallBack callBack);
}
