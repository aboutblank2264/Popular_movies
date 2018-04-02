package com.aboutblank.popular_movies.presentation;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.UseCase;
import com.aboutblank.popular_movies.UseCaseExecutor;
import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.presentation.model.MovieReview;
import com.aboutblank.popular_movies.presentation.model.MovieVideo;
import com.aboutblank.popular_movies.presentation.usecase.GetListOfDataUseCase;
import com.aboutblank.popular_movies.presentation.usecase.LoadImageUseCase;

import java.util.List;

public class DetailPresenterImpl implements DetailPresenter {
    private DetailPresenter.View view;
    private final UseCaseExecutor executor;
    private GetListOfDataUseCase<MovieReview> getMovieReviewsUseCase;
    private GetListOfDataUseCase<MovieVideo> getMovieVideosUseCase;


    public DetailPresenterImpl(@NonNull DetailPresenter.View view,
                               @NonNull LoadImageUseCase loadImageUseCase,
                               @NonNull GetListOfDataUseCase<MovieReview> getMovieReviewsUseCase,
                               @NonNull GetListOfDataUseCase<MovieVideo> getMovieVideosUseCase,
                               @NonNull UseCaseExecutor executor) {
        this.view = view;
        this.executor = executor;
        this.getMovieReviewsUseCase = getMovieReviewsUseCase;
        this.getMovieVideosUseCase = getMovieVideosUseCase;

        view.setPresenter(this);
    }

    @Override
    public void start() {
        String id = view.getMovieId();

        getMovieReviews(id);
    }

    @Override
    public void onError(String error) {
        view.showError(error);
    }

    @Override
    public void getMovieVideos(@NonNull String movieId) {
        executor.execute(getMovieVideosUseCase,
                new GetListOfDataUseCase.RequestValue(new MovieDbRequest(movieId)),
                getVideosUseCase());
    }

    @Override
    public void getMovieReviews(@NonNull String movieId) {
        executor.execute(getMovieReviewsUseCase,
                new GetListOfDataUseCase.RequestValue(new MovieDbRequest(movieId)),
                getReviewUseCase());
    }

    private UseCase.CallBack<GetListOfDataUseCase.ResponseValue<MovieReview>> getReviewUseCase() {
        return new UseCase.CallBack<GetListOfDataUseCase.ResponseValue<MovieReview>>() {
            @Override
            public void onSuccess(GetListOfDataUseCase.ResponseValue<MovieReview> response) {
                List<MovieReview> reviews = response.getPayLoad();
                view.showReviews(reviews);

                view.finishedLoading(true);
            }

            @Override
            public void onError(String error) {
                view.showError(error);

                view.finishedLoading(false);
            }
        };
    }

    private UseCase.CallBack<GetListOfDataUseCase.ResponseValue<MovieVideo>> getVideosUseCase() {
        return new UseCase.CallBack<GetListOfDataUseCase.ResponseValue<MovieVideo>>() {
            @Override
            public void onSuccess(GetListOfDataUseCase.ResponseValue<MovieVideo> response) {
                List<MovieVideo> videos = response.getPayLoad();
                view.showVideos(videos);

                view.finishedLoading(true);
            }

            @Override
            public void onError(String error) {
                view.showError(error);

                view.finishedLoading(false);
            }
        };
    }
}
