package com.aboutblank.popular_movies.presentation.usecase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.aboutblank.popular_movies.UseCase;
import com.aboutblank.popular_movies.data.DataSource;
import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.presentation.model.DataType;
import com.aboutblank.popular_movies.presentation.model.Movie;

import java.util.List;

public class GetMovieDataUseCase extends
        UseCase<GetMovieDataUseCase.RequestValue, GetMovieDataUseCase.ResponseValue> {
    private DataSource dataSource;

    public GetMovieDataUseCase(@NonNull DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void execute(final RequestValue requestValue) {
        switch (requestValue.listType) {
            case POPULAR:
                dataSource.getPopularMovies(getCallback(requestValue, DataType.POPULAR));
                break;
            case HIGHEST_RATED:
                dataSource.getHighestRatedMovies(getCallback(requestValue, DataType.HIGHEST_RATED));
                break;
            case FAVORITED:
                dataSource.getFavoritedMovies(getFavoriteCallBack());
            default:
                // Throw an error
                break;
        }
    }

    private DataSource.LoadListOfDataCallBack<Movie> getCallback(final RequestValue requestValue, final @DataType int dataType) {
        return new DataSource.LoadListOfDataCallBack<Movie>() {
            @Override
            public MovieDbRequest getRequest() {
                return requestValue.getDbRequest();
            }

            @Override
            public int getDataType() {
                return dataType;
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

    private DataSource.GetDataForFavoritedMoviesCallBack getFavoriteCallBack() {
        return new DataSource.GetDataForFavoritedMoviesCallBack() {
            private List<Integer> movieIds = null;

            @Override
            public List<Integer> getMovieIds() {
                return movieIds;
            }

            @Override
            public void setMovieIds(List<Integer> movieIds) {
                this.movieIds = movieIds;
            }

            @Override
            public void onDataLoaded(List<Movie> returnValue) {

                Log.d("GetFavoriteMovies", returnValue.toString());

                getCallBack().onSuccess(new ResponseValue(returnValue));
            }

            @Override
            public void onDataNotAvailable(String error) {
                getCallBack().onError(error);
            }
        };
    }

    public static class RequestValue implements UseCase.RequestValue {
        private final ListType listType;
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
        POPULAR, HIGHEST_RATED, FAVORITED
    }
}
