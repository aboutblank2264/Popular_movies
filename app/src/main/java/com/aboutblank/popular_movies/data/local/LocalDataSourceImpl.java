package com.aboutblank.popular_movies.data.local;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.data.domain.MovieItem;
import com.aboutblank.popular_movies.data.local.domain.HighestRatedMoviesEntity;
import com.aboutblank.popular_movies.data.local.domain.ListOfMovies;
import com.aboutblank.popular_movies.data.local.domain.MovieEntity;
import com.aboutblank.popular_movies.data.local.domain.PopularMoviesEntity;
import com.aboutblank.popular_movies.presentation.DatabaseReader;
import com.aboutblank.popular_movies.presentation.model.Movie;
import com.aboutblank.popular_movies.presentation.model.MovieReview;
import com.aboutblank.popular_movies.presentation.model.MovieVideo;
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
    public void getHighestRatedMovies(@NonNull LoadListOfDataCallBack<Movie> callBack) {
        MovieDbRequest request = callBack.getRequest();
        List<MovieEntity> movieEntityList = localDatabase.listMovieDao()
                .getListOfMovies(convertPageToListId(request.getPage(), HIGHEST_LIST_PREFIX));

        if (movieEntityList != null) {
            callBack.onDataLoaded(MovieUtils.entryListToMovieList(getMovieItemsFromList(movieEntityList)));
        } else {
            callBack.onDataNotAvailable("Unable to fetch the page requested");
        }
    }

    @Override
    public void getPopularMovies(@NonNull LoadListOfDataCallBack<Movie> callBack) {
        MovieDbRequest request = callBack.getRequest();
        List<MovieEntity> movieEntityList = localDatabase.listMovieDao()
                .getListOfMovies(convertPageToListId(request.getPage(), POPULAR_LIST_PREFIX));

        if (movieEntityList != null) {
            callBack.onDataLoaded(MovieUtils.entryListToMovieList(getMovieItemsFromList(movieEntityList)));
        } else {
            callBack.onDataNotAvailable("Unable to fetch the page requested");
        }
    }

    private List<MovieItem> getMovieItemsFromList(List<MovieEntity> entities) {
        List<MovieItem> movieItems = new ArrayList<>();

        for (MovieEntity entity : entities) {
            movieItems.add(entity.movieItem);
        }

        return movieItems;
    }


    @Override
    public void getListOfGenres(@NonNull LoadGenreCallBack callBack) {

    }

    @Override
    public void getListOfData(@NonNull LoadListOfDataCallBack<?> callBack) {

    }

    @Override
    public void getMovieReviews(@NonNull LoadListOfDataCallBack<MovieReview> callBack) {

    }

    @Override
    public void getMovieVideos(@NonNull LoadListOfDataCallBack<MovieVideo> callBack) {

    }

    @Override
    public void addMovieToFavorite(@NonNull AddRemoveMovieFavoritesCallBack callBack) {
        localDatabase.movieDao().setFavorite(callBack.getMovieId(), callBack.valueToUpdate());
    }

    @Override
    public void checkIfMovieIsFavorited(@NonNull CheckIfMovieIsFavoritedCallBack callBack) {
        boolean isFavorite = localDatabase.movieDao().isFavorite(callBack.getMovieId());

        callBack.onDataLoaded(isFavorite);
    }

    @Override
    public void saveHighestRatedMovies(@NonNull SaveHighestRatedMoviesCallback callBack) {
        try {
            HighestRatedMoviesEntity entity = callBack.getHighestRatedMoviesEntity();
            localDatabase.highestRatedDao().addPage(entity);
            saveListOfMovies(convertPageToListId(entity.pageId, HIGHEST_LIST_PREFIX), callBack.getMovies());
        } catch (Exception e) {
            callBack.onDataSaveFailure(e.getLocalizedMessage());
        }
    }

    @Override
    public void savePopularMovies(@NonNull SavePopularMoviesCallback callBack) {
        try {
            PopularMoviesEntity entity = callBack.getPopularMoviesEntity();
            localDatabase.popularMoviesDao().addPage(entity);
            saveListOfMovies(convertPageToListId(entity.pageId, POPULAR_LIST_PREFIX), callBack.getMovies());
        } catch (Exception e) {
            callBack.onDataSaveFailure(e.getLocalizedMessage());
        }
    }

    private void saveListOfMovies(String listId, List<MovieEntity> movies) {
        for(MovieEntity entity : movies) {
            localDatabase.movieDao().insert(entity);
            localDatabase.listMovieDao().insert(new ListOfMovies(listId, entity.movieId));
        }
    }

    @Override
    public void saveMovieReviews(@NonNull SaveReviewsToMovieCallback callBack) {
        localDatabase.movieDao().addMovieReviews(callBack.movieId(), callBack.getReviews());
    }

    @Override
    public void saveMovieVideos(@NonNull SaveVideosToMovieCallback callBack) {
        localDatabase.movieDao().addMovieVideos(callBack.movieId(), callBack.getVideos());
    }

    @Override
    public String convertPageToListId(int pageId, String prefix) {
        return prefix + pageId;
    }
}
