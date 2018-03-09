package com.aboutblank.popular_movies.presentation.usecase;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.UseCase;
import com.aboutblank.popular_movies.data.DataSource;
import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.presentation.model.Movie;

import java.util.List;

public class GetMovieData extends
        UseCase<GetMovieData.RequestValue, GetMovieData.ResponseValue> {
    private DataSource dataSource;

    public GetMovieData(@NonNull DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void execute(final RequestValue requestValue) {
        DataSource.LoadDataCallBack callback = getCallback(requestValue);

        switch (requestValue.listType) {
            case POPULAR:
                dataSource.getPopularMovies(callback);
                break;
            case HIGHEST_RATED:
                dataSource.getHighestRatedMovies(callback);
                break;
            default:
                // Throw an error
                break;
        }
    }

    private DataSource.LoadDataCallBack getCallback(final RequestValue requestValue) {
        return new DataSource.LoadDataCallBack() {
            @Override
            public MovieDbRequest getRequest() {
                return requestValue.getDbRequest();
            }

            @Override
            public void onDataLoaded(List<Movie> returnValue) {
                getCallBack().onSuccess(new ResponseValue(returnValue));
            }

            @Override
            public void onDataNotAvailable(String error) {
                getCallBack().onError(error);
            }
        };
    }

    public static class RequestValue implements UseCase.RequestValue {
        private ListType listType;
        private final MovieDbRequest dbRequest;

        public RequestValue(@NonNull ListType listType, @NonNull MovieDbRequest request) {
            this.listType = listType;
            dbRequest = request;
        }

        public MovieDbRequest getDbRequest() {
            return dbRequest;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {
        private final List<Movie> movies;

        public ResponseValue(@NonNull List<Movie> movies) {
            this.movies = movies;
        }

        public List<Movie> getMovies() {
            return movies;
        }
    }

    public enum ListType {
        POPULAR, HIGHEST_RATED
    }
}
