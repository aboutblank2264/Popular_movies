package com.aboutblank.popular_movies.presentation.usecase;

import com.aboutblank.popular_movies.UseCase;
import com.aboutblank.popular_movies.data.DataSource;
import com.aboutblank.popular_movies.data.domain.MovieDbRequest;

import java.util.List;

public class GetListOfDataUseCase<T> extends
        UseCase<GetListOfDataUseCase.RequestValue, GetListOfDataUseCase.ResponseValue<T>> {
    private DataSource dataSource;

    public GetListOfDataUseCase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void execute(final RequestValue requestValue) {
            dataSource.getListOfData(new DataSource.LoadListOfDataCallBack<T>() {
                @Override
                public MovieDbRequest getRequest() {
                    return requestValue.getDbRequest();
                }

                @Override
                public void onDataLoaded(List<T> result) {
                    getCallBack().onSuccess(new GetListOfDataUseCase.ResponseValue<>(result));
                }

                @Override
                public void onDataNotAvailable(String error) {
                    getCallBack().onError(error);
                }
            });
    }

    public static class RequestValue implements UseCase.RequestValue {
        private final MovieDbRequest dbRequest;

        public RequestValue(MovieDbRequest dbRequest) {
            this.dbRequest = dbRequest;
        }

        public MovieDbRequest getDbRequest() {
            return dbRequest;
        }
    }

    public static class ResponseValue<T> implements UseCase.ResponseValue {
        List<T> payload;

        public ResponseValue(List<T> payload) {
            this.payload = payload;
        }

        public List<T> getPayLoad() {
            return payload;
        }
    }
}
