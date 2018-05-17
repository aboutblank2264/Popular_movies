package com.aboutblank.popular_movies.presentation.usecase;

import com.aboutblank.popular_movies.UseCase;
import com.aboutblank.popular_movies.data.DataSource;

public class AddGetFavoriteUseCase extends
        UseCase<AddGetFavoriteUseCase.RequestValue, AddGetFavoriteUseCase.ResponseValue> {
    private DataSource dataSource;

    public AddGetFavoriteUseCase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void execute(final RequestValue requestValue) {
        if(requestValue.valueToUpdate != null) {
            dataSource.addMovieToFavorite(new DataSource.AddRemoveMovieFavoritesCallBack() {
                @Override
                public int getMovieId() {
                    return requestValue.getMovieId();
                }

                @Override
                public Boolean valueToUpdate() {
                    return requestValue.valueToUpdate();
                }

                @Override
                public void onDataNotAvailable(String error) {
                    getCallBack().onError(error);
                }
            });
        } else {
            dataSource.checkIfMovieIsFavorited(new DataSource.CheckIfMovieIsFavoritedCallBack() {
                @Override
                public int getMovieId() {
                    return requestValue.getMovieId();
                }

                @Override
                public void onDataLoaded(boolean isFavorite) {
                    getCallBack().onSuccess(new ResponseValue(isFavorite));
                }

                @Override
                public void onDataNotAvailable(String error) {
                    getCallBack().onError(error);
                }
            });
        }
    }

    public static class RequestValue implements UseCase.RequestValue {
        private final int movieId;
        private final Boolean valueToUpdate;

        public RequestValue(int movieId, Boolean valueToUpdate) {
            this.movieId = movieId;
            this.valueToUpdate = valueToUpdate;
        }

        public int getMovieId() {
            return movieId;
        }

        public Boolean valueToUpdate() {
            return valueToUpdate;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {
        private final boolean isFavorite;

        public ResponseValue(boolean isFavorite) {
            this.isFavorite = isFavorite;
        }

        public boolean isFavorite() {
            return isFavorite;
        }
    }
}
