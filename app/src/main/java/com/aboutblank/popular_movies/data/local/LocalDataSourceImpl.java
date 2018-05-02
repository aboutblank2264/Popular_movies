package com.aboutblank.popular_movies.data.local;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.aboutblank.popular_movies.data.domain.Genre;
import com.aboutblank.popular_movies.data.domain.ListOfGenres;
import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.data.domain.MovieItem;
import com.aboutblank.popular_movies.data.local.domain.MovieEntity;
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

    public LocalDataSourceImpl(MoviesLocalDatabase moviesLocalDatabase) {
        this.localDatabase = moviesLocalDatabase;
    }

    private LocalDataSourceImpl(DatabaseReader databaseReader) {
        this.localDatabase = MoviesLocalDatabase.getMoviesDatabase(databaseReader);
    }

    @Override
    public void saveMovie(@NonNull SaveMovieCallback callback) {
        MovieEntity entity = callback.getMovieEntity();

        if (entity != null) {
            localDatabase.movieDao().insert(entity);
        } else {
            callback.onDataSaveFailure("Unable to save MovieEntity, passed value: " + entity.toString());
        }
    }

    @Override
    public void getListOfGenres(@NonNull LoadGenreCallBack callBack) {
        ListOfGenres listOfGenres = localDatabase.genreDao().getGenresList(callBack.getLanguage());

        if (listOfGenres != null) {
            List<Genre> genreList = listOfGenres.getGenres();
            SparseArray<String> sparseArray = new SparseArray<>();

            if (genreList.isEmpty()) {
                callBack.onDataNotAvailable("No local genre list found.");
            } else {
                for (Genre genre : genreList) {
                    sparseArray.append(genre.getId(), genre.getName());
                }
                callBack.onDataLoaded(sparseArray);
            }
        } else {
            callBack.onDataNotAvailable("No local list of genres.");
        }
    }

    @Override
    public void saveGenres(@NonNull SaveGenresCallBack callBack) {
        String language = callBack.getLanguage();
        SparseArray<String> sparseArray = callBack.getGenres();

        if (language != null && sparseArray != null) {
            List<Genre> genreList = convertSparseArrayToList(sparseArray);

            ListOfGenres listOfGenres = new ListOfGenres(language, genreList);

            localDatabase.genreDao().insert(listOfGenres);
        } else {
            callBack.onDataSaveFailure("Unable to save genre list, values: " +
                    callBack.getLanguage() + ", " + callBack.getGenres());
        }
    }

    private List<Genre> convertSparseArrayToList(SparseArray<String> sparseArray) {
        List<Genre> genreList = new ArrayList<>();

        for (int i = 0; i < sparseArray.size(); i++) {
            int key = sparseArray.keyAt(i);
            String value = sparseArray.get(key);
            genreList.add(new Genre(key, value));
        }

        return genreList;
    }

    private class SaveGenreAsyncTask extends AsyncTask<Void, Void, Void> {
        ListOfGenres listOfGenres;

        public SaveGenreAsyncTask(ListOfGenres listOfGenres) {
            this.listOfGenres = listOfGenres;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            localDatabase.genreDao().insert(listOfGenres);

            return null;
        }
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
    public void getListOfData(@NonNull LoadListOfDataCallBack<?> callBack) {
        callBack.onDataNotAvailable("Can't call this method with a local data source");
    }

    @Override
    public void getMovie(@NonNull LoadMovieCallback callback) {
        MovieEntity entity = localDatabase.movieDao().getMovie(callback.getMovieId());

        if (entity != null && entity.movieItem != null) {
            callback.onDataLoaded(MovieUtils.entryToMovie(entity.movieItem));
        } else {
            callback.onDataNotAvailable("Unable to find movie with movieId: " + callback.getMovieId());
        }
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
    public void getMovieReviews(@NonNull LoadListOfDataCallBack<MovieReview> callBack) {
        callBack.onDataNotAvailable("Can't call this method with a local data source");
    }

    @Override
    public void getMovieVideos(@NonNull LoadListOfDataCallBack<MovieVideo> callBack) {
        callBack.onDataNotAvailable("Can't call this method with a local data source");
    }

    @Override
    public void saveHighestRatedMovies(@NonNull SaveHighestRatedMoviesCallback callBack) {
        callBack.onDataSaveFailure("Can't call this method with a local data source");
    }

    @Override
    public void savePopularMovies(@NonNull SavePopularMoviesCallback callBack) {
        callBack.onDataSaveFailure("Can't call this method with a local data source");
    }

    @Override
    public void saveMovieReviews(@NonNull SaveReviewsToMovieCallback callBack) {
        callBack.onDataSaveFailure("Can't call this method with a local data source");
    }

    @Override
    public void saveMovieVideos(@NonNull SaveVideosToMovieCallback callBack) {
        callBack.onDataSaveFailure("Can't call this method with a local data source");
    }

    @Override
    public String convertPageToListId(int pageId, String prefix) {
        return prefix + pageId;
    }

    @Override
    public void close() {
        localDatabase.close();
    }
}
