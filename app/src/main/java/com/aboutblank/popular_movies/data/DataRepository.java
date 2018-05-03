package com.aboutblank.popular_movies.data;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;

import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.data.local.LocalDataSource;
import com.aboutblank.popular_movies.data.local.LocalDataSourceImpl;
import com.aboutblank.popular_movies.data.remote.RemoteDataSourceImpl;
import com.aboutblank.popular_movies.presentation.DatabaseReader;
import com.aboutblank.popular_movies.presentation.model.DataType;
import com.aboutblank.popular_movies.presentation.model.Movie;
import com.aboutblank.popular_movies.presentation.model.MovieReview;
import com.aboutblank.popular_movies.presentation.model.MovieVideo;

import java.util.List;

/**
 * Implementation of DataSource, contains all finer DataSource implementations,
 * only public DataSource class available, contains business logic to fetch the right type of data.
 */
public class DataRepository implements DataSource {
    private final DataSource remoteDataSource;
    private final LocalDataSource localDataSource;

    private static DataRepository instance;

    private SparseArray<String> cached_genres;

    private DataRepository(DatabaseReader databaseReader) {
        this.remoteDataSource = RemoteDataSourceImpl.getInstance();
        this.localDataSource = LocalDataSourceImpl.getInstance(databaseReader);
    }

    public static DataRepository getInstance(DatabaseReader databaseReader) {
        if (instance == null) {
            instance = new DataRepository(databaseReader);
        }

        return instance;
    }

    @Override
    public void getHighestRatedMovies(@NonNull final LoadListOfDataCallBack<Movie> callBack) {
        remoteDataSource.getHighestRatedMovies(new LoadListOfDataCallBack<Movie>() {
            @Override
            public MovieDbRequest getRequest() {
                if (callBack.getRequest() != null) {
                    return callBack.getRequest();
                } else {
                    return new MovieDbRequest();
                }
            }

            @Override
            public DataType getDataType() {
                return DataType.HIGHEST_RATED;
            }

            @Override
            public void onDataLoaded(List<Movie> movies) {
                callBack.onDataLoaded(movies);
            }

            @Override
            public void onDataNotAvailable(String error) {
                callBack.onDataNotAvailable(error);
            }
        });
    }

    @Override
    public void getPopularMovies(@NonNull final LoadListOfDataCallBack<Movie> callBack) {
        remoteDataSource.getPopularMovies(new LoadListOfDataCallBack<Movie>() {
            @Override
            public MovieDbRequest getRequest() {
                if (callBack.getRequest() != null) {
                    return callBack.getRequest();
                } else {
                    return new MovieDbRequest();
                }
            }

            @Override
            public DataType getDataType() {
                return DataType.POPULAR;
            }

            @Override
            public void onDataLoaded(List<Movie> movies) {
                callBack.onDataLoaded(movies);
            }

            @Override
            public void onDataNotAvailable(String error) {
                callBack.onDataNotAvailable(error);
            }
        });
    }

    @Override
    public void getMovie(@NonNull LoadMovieCallback callback) {

    }

    @Override
    public void getMovieReviews(@NonNull final LoadListOfDataCallBack<MovieReview> callBack) {
        remoteDataSource.getMovieReviews(new LoadListOfDataCallBack<MovieReview>() {
            @Override
            public MovieDbRequest getRequest() {
                if (callBack.getRequest() != null) {
                    return callBack.getRequest();
                } else {
                    return new MovieDbRequest();
                }
            }

            @Override
            public DataType getDataType() {
                return callBack.getDataType();
            }

            @Override
            public void onDataLoaded(List<MovieReview> reviews) {
                callBack.onDataLoaded(reviews);
            }

            @Override
            public void onDataNotAvailable(String error) {
                callBack.onDataNotAvailable(error);
            }
        });
    }

    @Override
    public void getMovieVideos(@NonNull final LoadListOfDataCallBack<MovieVideo> callBack) {
        remoteDataSource.getMovieVideos(new LoadListOfDataCallBack<MovieVideo>() {
            @Override
            public MovieDbRequest getRequest() {
                if (callBack.getRequest() != null) {
                    return callBack.getRequest();
                } else {
                    return new MovieDbRequest();
                }
            }

            @Override
            public DataType getDataType() {
                return callBack.getDataType();
            }

            @Override
            public void onDataLoaded(List<MovieVideo> videos) {
                callBack.onDataLoaded(videos);
            }

            @Override
            public void onDataNotAvailable(String error) {
                callBack.onDataNotAvailable(error);
            }
        });
    }

    @Override
    public void getListOfGenres(@NonNull final LoadGenreCallBack callBack) {
        //1. Check if genres have been cached
        // doesn't check for language change, when the language is changed, cached_genres should be set to null.
        if (cached_genres != null) {
            Log.d("Genres", "Using cached list of genres");

            callGenreFetchedCallback(callBack);
        } else {
            //2. Else get from local data source
            getGenresFromLocal(callBack);
        }
    }

    private void getGenresFromLocal(@NonNull final LoadGenreCallBack callBack) {
        Log.d("Genres", "Checking for local stored list of genres");

        localDataSource.getListOfGenres(new LoadGenreCallBack() {
            @Override
            public String getLanguage() {
                return callBack.getLanguage();
            }

            @Override
            public void onDataLoaded(SparseArray<String> genres) {
                cached_genres = genres;

                callGenreFetchedCallback(callBack);
            }

            @Override
            public void onDataNotAvailable(String error) {

                Log.d("Genres", "No local list of genres");

                //3. if local repo doesn't have the genres, fetch from remote.
                getGenresFromRemote(callBack);
            }
        });
    }

    private void getGenresFromRemote(@NonNull final LoadGenreCallBack callBack) {

        Log.d("Genres", "Fetching list of genres from remote");

        remoteDataSource.getListOfGenres(new LoadGenreCallBack() {

            @Override
            public String getLanguage() {
                return callBack.getLanguage();
            }

            @Override
            public void onDataLoaded(final SparseArray<String> genres) {
                localDataSource.saveGenres(new LocalDataSource.SaveGenresCallBack() {
                    @Override
                    public String getLanguage() {
                        return callBack.getLanguage();
                    }

                    @Override
                    public SparseArray<String> getGenres() {
                        return genres;
                    }

                    @Override
                    public void onDataSaveFailure(String error) {
                        callBack.onDataNotAvailable(error);
                    }
                });
                cached_genres = genres;

                callGenreFetchedCallback(callBack);

                Log.d(DataRepository.class.getSimpleName(), genres.toString());
            }

            @Override
            public void onDataNotAvailable(String error) {
                callBack.onDataNotAvailable(error);
            }
        });
    }

    private void callGenreFetchedCallback(@NonNull LoadGenreCallBack callBack) {
        callBack.onDataLoaded(cached_genres);
    }

    @Override
    public void addMovieToFavorite(@NonNull AddRemoveMovieFavoritesCallBack callBack) {
        localDataSource.addMovieToFavorite(callBack);
    }

    @Override
    public void checkIfMovieIsFavorited(@NonNull CheckIfMovieIsFavoritedCallBack callBack) {
        localDataSource.checkIfMovieIsFavorited(callBack);
    }

    @Override
    public void invalidateCaches() {
        cached_genres = null;
    }

    @Override
    public void getListOfData(@NonNull LoadListOfDataCallBack callBack) {
        switch (callBack.getDataType()) {
            case REVIEWS:
                getMovieReviews(callBack);
                break;
            case VIDEOS:
                getMovieVideos(callBack);
                break;
        }
    }
}
