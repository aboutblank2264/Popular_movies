package com.aboutblank.popular_movies.data.local;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.data.DataSource;
import com.aboutblank.popular_movies.data.domain.ListOfMovieItems;
import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.data.local.domain.BaseMoviesEntity;

public interface LocalDataSource extends DataSource {

    interface SaveDataCallBack {
        BaseMoviesEntity getBaseMovieEntity();
        ListOfMovieItems getListOfMovieItems();

        void onDataSave(MovieDbRequest request, ListOfMovieItems movies);
        void onDataSaveFailure(String error);
    }

    void putHighestRatedMovies(@NonNull LocalDataSource.SaveDataCallBack callBack);

    void putPopularMovies(@NonNull LocalDataSource.SaveDataCallBack callBack);

    void putListOfGenres(@NonNull LocalDataSource.SaveDataCallBack callBack);
}
