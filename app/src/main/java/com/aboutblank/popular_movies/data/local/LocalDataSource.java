package com.aboutblank.popular_movies.data.local;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.data.DataSource;
import com.aboutblank.popular_movies.data.local.domain.MovieEntity;

import java.util.List;

public interface LocalDataSource extends DataSource {

    interface SaveMovieCallBack {
        MovieEntity getMovieEntity();

        void onDataSaveFailure(String error);
    }

    interface GetFavoritedMoviesCallBack {
        void onDataLoaded(List<Integer> list);

        void onDataNotAvailable(String error);
    }

    void getFavoritedMovies(@NonNull GetFavoritedMoviesCallBack callBack);

    void saveMovie(@NonNull SaveMovieCallBack callback);

    void close();
}
