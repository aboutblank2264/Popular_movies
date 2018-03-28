package com.aboutblank.popular_movies.data;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.data.remote.RemoteDataSourceImpl;
import com.aboutblank.popular_movies.presentation.model.Movie;

import java.util.List;

/**
 * Implementation of DataSource, contains all finer DataSource implementations,
 * only public DataSource class available, contains business logic to fetch the right type of data.
 */
public class DataRepository implements DataSource {
    private final DataSource remoteDataSource;

    private static DataRepository instance;

    private DataRepository() {
        this.remoteDataSource = RemoteDataSourceImpl.getInstance();
    }

    public static DataRepository getInstance() {
        if(instance == null) {
            instance = new DataRepository();
        }

        return instance;
    }

    @Override
    public void getHighestRatedMovies(@NonNull final LoadDataCallBack callBack) {
        remoteDataSource.getHighestRatedMovies(new LoadDataCallBack() {
            @Override
            public MovieDbRequest getRequest() {
                if(callBack.getRequest() != null) {
                    return callBack.getRequest();
                } else {
                    return new MovieDbRequest();
                }
            }

            @Override
            public void onDataLoaded(List<Movie> movies) {
                //TODO local repo stuff.
                callBack.onDataLoaded(movies);
            }

            @Override
            public void onDataNotAvailable(String error) {
                // TODO local repo stuff.
                callBack.onDataNotAvailable(error);
            }
        });
    }

    @Override
    public void getPopularMovies(@NonNull final LoadDataCallBack callBack) {
        remoteDataSource.getPopularMovies(new LoadDataCallBack() {
            @Override
            public MovieDbRequest getRequest() {
                if(callBack.getRequest() != null) {
                    return callBack.getRequest();
                } else {
                    return new MovieDbRequest();
                }
            }

            @Override
            public void onDataLoaded(List<Movie> movies) {
                //TODO local repo stuff.
                callBack.onDataLoaded(movies);
            }

            @Override
            public void onDataNotAvailable(String error) {
                // TODO local repo stuff.
                callBack.onDataNotAvailable(error);
            }
        });
    }

    @Override
    public void getListOfGenres(@NonNull LoadDataCallBack callBack) {
        //TODO
    }
}
