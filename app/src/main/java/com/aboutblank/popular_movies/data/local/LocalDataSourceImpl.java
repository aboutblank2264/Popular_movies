package com.aboutblank.popular_movies.data.local;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.data.domain.MovieItem;
import com.aboutblank.popular_movies.data.local.domain.MovieEntity;
import com.aboutblank.popular_movies.presentation.DatabaseReader;
import com.aboutblank.popular_movies.utils.MovieUtils;

import java.util.ArrayList;
import java.util.List;

public class LocalDataSourceImpl implements LocalDataSource {

    private static LocalDataSourceImpl instance;

    private MoviesLocalDatabase localDatabase;

    public static LocalDataSourceImpl getInstance(DatabaseReader databaseReader) {
        if (instance == null) {
            instance = new LocalDataSourceImpl(databaseReader);
        }

        return instance;
    }

    private LocalDataSourceImpl(DatabaseReader databaseReader) {
        this.localDatabase = MoviesLocalDatabase.getMoviesDatabase(databaseReader);
    }

    @Override
    public void getHighestRatedMovies(@NonNull LoadMovieDataCallBack callBack) {
        MovieDbRequest request = callBack.getRequest();
        List<MovieEntity> movieEntityList = localDatabase.highestRatedDao().getMoviesForPage(request.getPage());

        if (movieEntityList != null) {
            callBack.onDataLoaded(MovieUtils.entryListToMovieList(getMovieItemsFromList(movieEntityList)));
        } else {
            callBack.onDataNotAvailable("Unable to fetch the page requested");
        }
    }

    @Override
    public void getPopularMovies(@NonNull LoadMovieDataCallBack callBack) {
        MovieDbRequest request = callBack.getRequest();
        List<MovieEntity> movieEntityList = localDatabase.popularMoviesDao().getMoviesForPage(request.getPage());

        if (movieEntityList != null) {
            callBack.onDataLoaded(MovieUtils.entryListToMovieList(getMovieItemsFromList(movieEntityList)));
        } else {
            callBack.onDataNotAvailable("Unable to fetch the page requested");
        }
    }

    @Override
    public void getMovieReviews(@NonNull LoadMovieReviewCallBack callBack) {

    }

    @Override
    public void getMovieVideos(@NonNull LoadMovieVideosCallBack callBack) {

    }

    @Override
    public void getListOfGenres(@NonNull LoadGenreCallBack callBack) {

    }

    @Override
    public void getListOfData(@NonNull LoadListOfDataCallBack<?> callBack) {

    }

    private List<MovieItem> getMovieItemsFromList(List<MovieEntity> entities) {
        List<MovieItem> movieItems = new ArrayList<>();

        for (MovieEntity entity : entities) {
            movieItems.add(entity.getMovieItem());
        }

        return movieItems;
    }

    @Override
    public void putHighestRatedMovies(@NonNull SaveDataCallBack callBack) {
    }

    @Override
    public void putPopularMovies(@NonNull SaveDataCallBack callBack) {

    }

    @Override
    public void putListOfGenres(@NonNull SaveDataCallBack callBack) {

    }
}
