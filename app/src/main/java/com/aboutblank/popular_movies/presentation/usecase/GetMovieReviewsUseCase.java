package com.aboutblank.popular_movies.presentation.usecase;

import com.aboutblank.popular_movies.UseCase;
import com.aboutblank.popular_movies.data.DataSource;
import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.presentation.model.MovieReview;

import java.util.List;

public class GetMovieReviewsUseCase extends UseCase<GetMovieReviewsUseCase.RequestValue, GetMovieReviewsUseCase.ResponseValue> {
    private DataSource dataSource;

    public GetMovieReviewsUseCase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void execute(final RequestValue requestValue) {
        dataSource.getMovieReviews(new DataSource.LoadMovieReviewCallBack() {
            @Override
            public MovieDbRequest getRequest() {
                return requestValue.getDbRequest();
            }

            @Override
            public void onDataLoaded(List<MovieReview> reviews) {
                getCallBack().onSuccess(new ResponseValue(reviews));
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

    public static class ResponseValue implements UseCase.ResponseValue {
        List<MovieReview> reviews;

        public ResponseValue(List<MovieReview> reviews) {
            this.reviews = reviews;
        }

        public List<MovieReview> getReviews() {
            return reviews;
        }
    }
}
