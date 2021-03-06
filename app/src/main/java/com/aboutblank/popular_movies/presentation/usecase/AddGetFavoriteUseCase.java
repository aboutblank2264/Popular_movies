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
        if(requestValue.isUpdate()) {
            dataSource.addMovieToFavorite(new DataSource.AddRemoveMovieFavoritesCallBack() {
                @Override
                public int getMovieId() {
                    return requestValue.getMovieId();
                }

                @Override
                public boolean valueToUpdate() {
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

    public interface RequestValue extends UseCase.RequestValue {
        boolean isUpdate();
        int getMovieId();
        boolean valueToUpdate();
    }

    public static class RequestFavoriteValue implements RequestValue {
        private final int movieId;

        public RequestFavoriteValue(int movieId) {
            this.movieId = movieId;
        }

        @Override
        public boolean isUpdate() {
            return false;
        }

        @Override
        public int getMovieId() {
            return movieId;
        }

        @Override
        public boolean valueToUpdate() {
            return false;
        }
    }

    public static class RequestUpdateValue implements RequestValue {
        private final int movieId;
        private final boolean valueToUpdate;

        public RequestUpdateValue(int movieId, boolean valueToUpdate) {
            this.movieId = movieId;
            this.valueToUpdate = valueToUpdate;
        }

        @Override
        public boolean isUpdate() {
            return true;
        }

        @Override
        public int getMovieId() {
            return movieId;
        }

        @Override
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
