package com.aboutblank.popular_movies.presentation.usecase;

import com.aboutblank.popular_movies.UseCase;
import com.aboutblank.popular_movies.data.DataSource;

public class AddGetFavoriteUseCase extends
        UseCase<AddGetFavoriteUseCase.RequestValue, AddGetFavoriteUseCase.ResponseValue> {
    private DataSource dataSource;

    public AddGetFavoriteUseCase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void execute(final RequestValue requestValue, boolean addToFavorites) {
        if(addToFavorites) {
            dataSource.addMovieToFavorite(new DataSource.AddRemoveMovieFavoritesCallBack() {
                @Override
                public int getMovieId() {
                    return requestValue.getMovieId();
                }

                @Override
                public boolean toUpdate() {
                    return requestValue.update();
                }

                @Override
                public boolean valueToUpdate() {
                    return requestValue.valueToUpdate();
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

    @Override
    public void execute(RequestValue requestValue) {
        //TODO
    }

    public static class RequestValue implements UseCase.RequestValue {
        private final int movieId;
        private final boolean update;
        private final boolean valueToUpdate;

        public RequestValue(int movieId, boolean update, boolean valueToUpdate) {
            this.movieId = movieId;
            this.update = update;
            this.valueToUpdate = valueToUpdate;
        }

        public int getMovieId() {
            return movieId;
        }

        public boolean update() {
            return update;
        }

        public boolean valueToUpdate() {
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
