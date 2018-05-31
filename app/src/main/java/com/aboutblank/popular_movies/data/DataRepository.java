package com.aboutblank.popular_movies.data;

import android.support.annotation.NonNull;
import android.util.Log;

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

    private List<Movie> highestRankingMoviesCache;
    private List<Movie> popularMoviesCache;
    private List<Movie> favoriteMoviesCache;

    private String latestMovieId;
    private List<MovieReview> latestMovieReviews;
    private List<MovieVideo> latestMovieVideos;

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
        if (highestRankingMoviesCache != null) {
            Log.d("Highest Ranking Movies", "Cached!");

            callBack.onDataLoaded(highestRankingMoviesCache);
        } else {
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
                    highestRankingMoviesCache = movies;

                    callBack.onDataLoaded(movies);
                }

                @Override
                public void onDataNotAvailable(String error) {
                    callBack.onDataNotAvailable(error);
                }
            });
        }
    }

    @Override
    public void getPopularMovies(@NonNull final LoadListOfDataCallBack<Movie> callBack) {
        if (popularMoviesCache != null) {
            Log.d("Popular Movies", "Cached!");

            callBack.onDataLoaded(popularMoviesCache);
        } else {
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
                    popularMoviesCache = movies;

                    callBack.onDataLoaded(movies);
                }

                @Override
                public void onDataNotAvailable(String error) {
                    callBack.onDataNotAvailable(error);
                }
            });
        }
    }

    @Override
    public void getFavoritedMovies(@NonNull final GetDataForFavoritedMoviesCallBack callBack) {
        if (favoriteMoviesCache != null) {
            Log.d("Favorite Movies", "Cached!");

            callBack.onDataLoaded(favoriteMoviesCache);
        } else {
            localDataSource.getFavoritedMovies(new LocalDataSource.GetFavoritedMoviesCallBack() {
                @Override
                public void onDataLoaded(List<Integer> movieIds) {
                    callBack.setMovieIds(movieIds);

                    remoteDataSource.getFavoritedMovies(new GetDataForFavoritedMoviesCallBack() {
                        @Override
                        public List<Integer> getMovieIds() {
                            return callBack.getMovieIds();
                        }

                        @Override
                        public void setMovieIds(List<Integer> movieIds) {
                            //Do nothing.
                        }

                        @Override
                        public void onDataLoaded(List<Movie> movies) {
                            favoriteMoviesCache = movies;

                            callBack.onDataLoaded(movies);
                        }

                        @Override
                        public void onDataNotAvailable(String error) {
                            callBack.onDataNotAvailable(error);
                        }
                    });
                }

                @Override
                public void onDataNotAvailable(String error) {
                    callBack.onDataNotAvailable(error);
                }
            });
        }
    }

    //Not used
    @Override
    public void getMovie(@NonNull final LoadMovieCallBack callBack) {
        localDataSource.getMovie(new LoadMovieCallBack() {
            @Override
            public int getMovieId() {
                return callBack.getMovieId();
            }

            @Override
            public void onDataLoaded(Movie movieItem) {
                callBack.onDataLoaded(movieItem);
            }

            @Override
            public void onDataNotAvailable(String error) {
                callBack.onDataNotAvailable(error);
            }
        });
    }

    @Override
    public void getMovieReviews(@NonNull final LoadListOfDataCallBack<MovieReview> callBack) {
        if (latestMovieReviews != null && latestMovieId.equals(callBack.getRequest().getId())) {
            Log.d("Movie Reviews", "Cached!");

            callBack.onDataLoaded(latestMovieReviews);
        } else {
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
                    latestMovieReviews = reviews;
                    latestMovieId = callBack.getRequest().getId();

                    callBack.onDataLoaded(reviews);
                }

                @Override
                public void onDataNotAvailable(String error) {
                    callBack.onDataNotAvailable(error);
                }
            });
        }
    }

    @Override
    public void getMovieVideos(@NonNull final LoadListOfDataCallBack<MovieVideo> callBack) {
        if (latestMovieVideos != null && latestMovieId.equals(callBack.getRequest().getId())) {
            Log.d("Movie Videos", "Cached!");

            callBack.onDataLoaded(latestMovieVideos);
        } else {
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
                    latestMovieVideos = videos;
                    latestMovieId = callBack.getRequest().getId();

                    callBack.onDataLoaded(videos);
                }

                @Override
                public void onDataNotAvailable(String error) {
                    callBack.onDataNotAvailable(error);
                }
            });
        }
    }

    @Override
    public void addMovieToFavorite(@NonNull AddRemoveMovieFavoritesCallBack callBack) {
        favoriteMoviesCache = null;
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
