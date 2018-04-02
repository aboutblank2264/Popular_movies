package com.aboutblank.popular_movies.data;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.presentation.model.MovieReview;
import com.aboutblank.popular_movies.presentation.model.MovieVideo;
import com.aboutblank.popular_movies.data.local.LocalDataSourceImpl;
import com.aboutblank.popular_movies.data.remote.RemoteDataSourceImpl;
import com.aboutblank.popular_movies.presentation.DatabaseReader;
import com.aboutblank.popular_movies.presentation.model.Movie;

import java.util.List;
import java.util.Map;

/**
 * Implementation of DataSource, contains all finer DataSource implementations,
 * only public DataSource class available, contains business logic to fetch the right type of data.
 */
public class DataRepository implements DataSource {
    private final DataSource remoteDataSource;
    private final DataSource localDataSource;

    private static DataRepository instance;

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
    public void getHighestRatedMovies(@NonNull final LoadMovieDataCallBack callBack) {
        remoteDataSource.getHighestRatedMovies(new LoadMovieDataCallBack() {
            @Override
            public MovieDbRequest getRequest() {
                if (callBack.getRequest() != null) {
                    return callBack.getRequest();
                } else {
                    return new MovieDbRequest();
                }
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
    public void getPopularMovies(@NonNull final LoadMovieDataCallBack callBack) {
        remoteDataSource.getPopularMovies(new LoadMovieDataCallBack() {
            @Override
            public MovieDbRequest getRequest() {
                if (callBack.getRequest() != null) {
                    return callBack.getRequest();
                } else {
                    return new MovieDbRequest();
                }
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
    public void getMovieReviews(@NonNull final LoadMovieReviewCallBack callBack) {
        remoteDataSource.getMovieReviews(new LoadMovieReviewCallBack() {
            @Override
            public MovieDbRequest getRequest() {
                if(callBack.getRequest() != null) {
                    return callBack.getRequest();
                } else {
                    return new MovieDbRequest();
                }
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
    public void getMovieVideos(@NonNull final LoadMovieVideosCallBack callBack) {
        remoteDataSource.getMovieVideos(new LoadMovieVideosCallBack() {
            @Override
            public MovieDbRequest getRequest() {
                if(callBack.getRequest() != null) {
                    return callBack.getRequest();
                } else {
                    return new MovieDbRequest();
                }
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
        remoteDataSource.getListOfGenres(new LoadGenreCallBack() {

            @Override
            public String getLanguage() {
                if(callBack.getLanguage() != null && !callBack.getLanguage().isEmpty()) {
                    return callBack.getLanguage();
                } else {
                    return "";
                }
            }

            @Override
            public void onDataLoaded(Map<Integer, String> genres) {
                callBack.onDataLoaded(genres);
            }

            @Override
            public void onDataNotAvailable(String error) {
                callBack.onDataNotAvailable(error);
            }
        });
    }

    @Override
    public void getListOfData(@NonNull LoadListOfDataCallBack callBack) {

    }
}
