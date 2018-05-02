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
        //Check if genres have been cached
        if (cached_genres != null) {
            callBack.onDataLoaded(cached_genres);
        } else {
            //Else get from local data source
            localDataSource.getListOfGenres(new LoadGenreCallBack() {
                @Override
                public String getLanguage() {
                    if (callBack.getLanguage() != null && !callBack.getLanguage().isEmpty()) {
                        return callBack.getLanguage();
                    } else {
                        return "";
                    }
                }

                @Override
                public void onDataLoaded(SparseArray<String> genres) {
                    cached_genres = genres;

                    Log.d(DataRepository.class.getSimpleName(), genres.toString());
                }

                @Override
                public void onDataNotAvailable(String error) {
                    callBack.onDataNotAvailable(error + "/n Trying to retrieve from web.");
                }
            });
        }
        if(cached_genres == null) {
            //finally try to get from remote
            remoteDataSource.getListOfGenres(new LoadGenreCallBack() {

                @Override
                public String getLanguage() {
                    if (callBack.getLanguage() != null && !callBack.getLanguage().isEmpty()) {
                        return callBack.getLanguage();
                    } else {
                        return "";
                    }
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

                    Log.d(DataRepository.class.getSimpleName(), genres.toString());

                }

                @Override
                public void onDataNotAvailable(String error) {
                    callBack.onDataNotAvailable(error);
                }
            });
        }
        //Finally return genres, there should be error messages propagated up already.
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
