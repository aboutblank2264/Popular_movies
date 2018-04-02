package com.aboutblank.popular_movies.presentation;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.UseCase;
import com.aboutblank.popular_movies.UseCaseExecutor;
import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.presentation.model.MovieReview;
import com.aboutblank.popular_movies.presentation.model.MovieVideo;
import com.aboutblank.popular_movies.presentation.usecase.GetGenresUseCase;
import com.aboutblank.popular_movies.presentation.usecase.GetListOfDataUseCase;

import java.util.List;

public class DetailPresenterImpl implements DetailPresenter {
    private DetailPresenter.View view;
    private final UseCaseExecutor executor;
    private GetListOfDataUseCase<MovieReview> movieReviewsUseCase;
    private GetListOfDataUseCase<MovieVideo> videosUseCase;
    private GetGenresUseCase genresUseCase;


    public DetailPresenterImpl(@NonNull DetailPresenter.View view,
//                               @NonNull LoadImageUseCase loadImageUseCase,
                               @NonNull GetListOfDataUseCase<MovieReview> movieReviewsUseCase,
                               @NonNull GetListOfDataUseCase<MovieVideo> videosUseCase,
                               @NonNull GetGenresUseCase genresUseCase,
                               @NonNull UseCaseExecutor executor) {
        this.view = view;
        this.executor = executor;
        this.movieReviewsUseCase = movieReviewsUseCase;
        this.videosUseCase = videosUseCase;
        this.genresUseCase = genresUseCase;

        view.setPresenter(this);
    }

    @Override
    public void start() {
        String id = view.getMovie().getId();

        getMovieReviews(id);
    }

    @Override
    public void onError(String error) {
        view.showError(error);
    }

    @Override
    public void getMovieVideos(@NonNull String movieId) {
        executor.execute(videosUseCase,
                new GetListOfDataUseCase.RequestValue(new MovieDbRequest(movieId)),
                getVideosUseCase());
    }

    @Override
    public void getMovieReviews(@NonNull String movieId) {
        executor.execute(movieReviewsUseCase,
                new GetListOfDataUseCase.RequestValue(new MovieDbRequest(movieId)),
                getReviewUseCase());
    }

    @Override
    public void getMovieGenres(@NonNull List<Integer> genres) {
        executor.execute(genresUseCase,
                new GetGenresUseCase.RequestValue(view.getMovie().getGenres()),
                getGenresUseCase());
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
            }
        };
    }

    private UseCase.CallBack<GetGenresUseCase.ResponseValue> getGenresUseCase() {
        return new UseCase.CallBack<GetGenresUseCase.ResponseValue>() {
            @Override
            public void onSuccess(GetGenresUseCase.ResponseValue response) {
                List<String> genres = response.getGenres();

                view.showGenres(genres);

                view.finishedLoading(true);
            }

            @Override
            public void onError(String error) {
                view.showError(error);
            }
        };
    }
}
