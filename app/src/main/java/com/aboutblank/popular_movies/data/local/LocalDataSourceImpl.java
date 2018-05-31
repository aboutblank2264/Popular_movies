package com.aboutblank.popular_movies.data.local;

import android.support.annotation.NonNull;
import android.util.Log;

import com.aboutblank.popular_movies.data.local.domain.MovieEntity;
import com.aboutblank.popular_movies.presentation.DatabaseReader;
import com.aboutblank.popular_movies.presentation.model.Movie;
import com.aboutblank.popular_movies.presentation.model.MovieReview;
import com.aboutblank.popular_movies.presentation.model.MovieVideo;

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

    public LocalDataSourceImpl(MoviesLocalDatabase moviesLocalDatabase) {
        localDatabase = moviesLocalDatabase;
    }

    private LocalDataSourceImpl(DatabaseReader databaseReader) {
        localDatabase = MoviesLocalDatabase.getMoviesDatabase(databaseReader);
    }

    @Override
    public void getFavoritedMovies(@NonNull GetFavoritedMoviesCallBack callBack) {
        List<Integer> entityList = localDatabase.movieDao().getFavoritedMovies();
        if(entityList != null) {
            callBack.onDataLoaded(entityList);
        } else {
            callBack.onDataNotAvailable("Unable to retrieve any favorited movies.");
        }
    }

    @Override
    public void saveMovie(@NonNull SaveMovieCallBack callback) {
        MovieEntity entity = callback.getMovieEntity();

        if (entity != null) {
            localDatabase.movieDao().insert(entity);
        } else {
            callback.onDataSaveFailure("Unable to save MovieEntity, passed value: " + callback.toString());
        }
    }

    @Override
    public void addMovieToFavorite(@NonNull AddRemoveMovieFavoritesCallBack callBack) {
        MovieEntity movieEntity = getMovieEntity(callBack.getMovieId());

        if (movieEntity == null) {
            localDatabase.movieDao().insert(new MovieEntity(callBack.getMovieId()));
        }
        localDatabase.movieDao().setFavorite(callBack.getMovieId(), callBack.valueToUpdate());
    }

    @Override
    public void checkIfMovieIsFavorited(@NonNull CheckIfMovieIsFavoritedCallBack callBack) {
        boolean isFavorite = localDatabase.movieDao().isFavorite(callBack.getMovieId());

        Log.d(LocalDataSourceImpl.class.getSimpleName(), "Movie " + callBack.getMovieId() + " is favorited: " + isFavorite);

        callBack.onDataLoaded(isFavorite);
    }

    private MovieEntity getMovieEntity(int movieId) {
        return localDatabase.movieDao().getMovie(movieId);
    }

    @Override
    public void getMovie(@NonNull LoadMovieCallBack callBack) {
        callBack.onDataNotAvailable("Can't call this method with a local data source");
    }

    @Override
    public void getHighestRatedMovies(@NonNull LoadListOfDataCallBack<Movie> callBack) {
        callBack.onDataNotAvailable("Can't call this method with a local data source");
    }

    @Override
    public void getPopularMovies(@NonNull LoadListOfDataCallBack<Movie> callBack) {
        callBack.onDataNotAvailable("Can't call this method with a local data source");
    }

    @Override
    public void getFavoritedMovies(@NonNull GetDataForFavoritedMoviesCallBack callBack) {
        callBack.onDataNotAvailable("Can't call this method with a local data source");

    }

    @Override
    public void getListOfData(@NonNull LoadListOfDataCallBack<?> callBack) {
        callBack.onDataNotAvailable("Can't call this method with a local data source");
    }

    @Override
    public void getMovieReviews(@NonNull LoadListOfDataCallBack<MovieReview> callBack) {
        callBack.onDataNotAvailable("Can't call this method with a local data source");
    }

    @Override
    public void getMovieVideos(@NonNull LoadListOfDataCallBack<MovieVideo> callBack) {
        callBack.onDataNotAvailable("Can't call this method with a local data source");
    }

    @Override
    public void close() {
        localDatabase.close();
    }
}
