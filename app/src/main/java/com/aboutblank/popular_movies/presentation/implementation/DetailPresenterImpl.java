package com.aboutblank.popular_movies.presentation.implementation;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.UseCase;
import com.aboutblank.popular_movies.UseCaseExecutor;
import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.presentation.DetailPresenter;
import com.aboutblank.popular_movies.presentation.model.Movie;
import com.aboutblank.popular_movies.presentation.model.MovieReview;
import com.aboutblank.popular_movies.presentation.model.MovieVideo;
import com.aboutblank.popular_movies.presentation.usecase.AddGetFavoriteUseCase;
import com.aboutblank.popular_movies.presentation.usecase.GetGenresUseCase;
import com.aboutblank.popular_movies.presentation.usecase.GetListOfDataUseCase;

import java.util.List;

public class DetailPresenterImpl implements DetailPresenter {
    private DetailPresenter.View view;
    private final UseCaseExecutor executor;
    private GetListOfDataUseCase<MovieReview> movieReviewsUseCase;
    private GetListOfDataUseCase<MovieVideo> videosUseCase;
    private AddGetFavoriteUseCase addGetFavoriteUseCase;
    private GetGenresUseCase genresUseCase;


    public DetailPresenterImpl(@NonNull DetailPresenter.View view,
                               @NonNull GetListOfDataUseCase<MovieReview> movieReviewsUseCase,
                               @NonNull GetListOfDataUseCase<MovieVideo> videosUseCase,
                               @NonNull GetGenresUseCase genresUseCase,
                               @NonNull AddGetFavoriteUseCase addGetFavoriteUseCase,
                               @NonNull UseCaseExecutor executor) {
        this.view = view;
        this.executor = executor;
        this.movieReviewsUseCase = movieReviewsUseCase;
        this.videosUseCase = videosUseCase;
        this.genresUseCase = genresUseCase;
        this.addGetFavoriteUseCase = addGetFavoriteUseCase;

        //Allows for View to not know about presenter past constructor.
        view.setPresenter(this);
    }

    @Override
    public void start() {
        view.showProgress(true);

        Movie movie = view.getMovie();

        getMovieGenres(movie.getGenres());
        getMovieReviews(movie.getId());
        getMovieVideos(movie.getId());
    }

    @Override
    public void onError(String error) {
        view.showError(error);
    }

    @Override
    public void getMovieVideos(@NonNull String movieId) {
        executor.execute(videosUseCase,
                new GetListOfDataUseCase.RequestValue(new MovieDbRequest(movieId)),
                new UseCase.CallBack<GetListOfDataUseCase.ResponseValue<MovieVideo>>() {
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
                });
    }

    @Override
    public void getMovieReviews(@NonNull String movieId) {
        executor.execute(movieReviewsUseCase,
                new GetListOfDataUseCase.RequestValue(new MovieDbRequest(movieId)),
                new UseCase.CallBack<GetListOfDataUseCase.ResponseValue<MovieReview>>() {
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
                });
    }

    @Override
    public void getMovieGenres(@NonNull List<Integer> genres) {
        executor.execute(genresUseCase,
                new GetGenresUseCase.RequestValue(view.getMovie().getGenres()),
                new UseCase.CallBack<GetGenresUseCase.ResponseValue>() {
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
                });
    }

    @Override
    public void addMovieToFavorites(@NonNull String movieId) {
        executor.execute(addGetFavoriteUseCase,
                new AddGetFavoriteUseCase.RequestValue(Integer.valueOf(movieId), true, true),
                new UseCase.CallBack<AddGetFavoriteUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(AddGetFavoriteUseCase.ResponseValue response) {
                        view.updateFavorite(response.isFavorite());
                    }

                    @Override
                    public void onError(String error) {
                        view.showError(error);
                    }
                });
    }
}