package com.aboutblank.popular_movies.data;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.presentation.model.Movie;

import java.util.List;

/**
 * Interface for Data layer.
 * Contains callback to return data retrieved back to higher layers.
 */
public interface DataSource {

    interface LoadDataCallBack {

        MovieDbRequest getRequest();

        void onDataLoaded(List<Movie> returnValue);

        void onDataNotAvailable(String error);
    }

    void getHighestRatedMovies(@NonNull LoadDataCallBack callBack);

    void getPopularMovies(@NonNull LoadDataCallBack callBack);

    void getListOfGenres(@NonNull LoadDataCallBack callBack);
}
