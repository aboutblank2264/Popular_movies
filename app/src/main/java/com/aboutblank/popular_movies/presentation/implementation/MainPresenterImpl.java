package com.aboutblank.popular_movies.presentation.implementation;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.UseCase;
import com.aboutblank.popular_movies.UseCaseExecutor;
import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.presentation.MainPresenter;
import com.aboutblank.popular_movies.presentation.model.Movie;
import com.aboutblank.popular_movies.presentation.usecase.GetMovieDataUseCase;

import java.util.List;

/**
 * Class that acts as bridge between user actions and UI.
 * Communicates between Domain layer and View layer
 */
public class MainPresenterImpl implements MainPresenter {
    private final MainPresenter.View view;
    private final UseCaseExecutor executor;

    private GetMovieDataUseCase getMovieDataUseCase;

    public MainPresenterImpl(@NonNull MainPresenter.View view,
                             @NonNull GetMovieDataUseCase getMovieDataUseCase,
                             @NonNull UseCaseExecutor executor) {
        this.view = view;
        this.executor = executor;

        this.getMovieDataUseCase = getMovieDataUseCase;

        //Allows for View to not know about presenter past constructor.
        view.setPresenter(this);

    }

    @Override
    public void start() {
        view.showProgress(true);

        switch (view.showMovieType()) {
            case POPULAR:
                loadPopularMovieData();
                break;
            case HIGHEST_RATED:
                loadHighestRatedMovies();
                break;
            case FAVORITED:
                loadFavoriteMovies();
                break;
            default:
                break;
        }
    }

    private void loadPopularMovieData() {
        executor.execute(getMovieDataUseCase,
                new GetMovieDataUseCase.RequestValue(GetMovieDataUseCase.ListType.POPULAR, new MovieDbRequest()),
                getDefaultCallback());
    }

    private void loadHighestRatedMovies() {
        executor.execute(getMovieDataUseCase,
                new GetMovieDataUseCase.RequestValue(GetMovieDataUseCase.ListType.HIGHEST_RATED, new MovieDbRequest()),
                getDefaultCallback());
    }

    private void loadFavoriteMovies() {
        executor.execute(getMovieDataUseCase,
                new GetMovieDataUseCase.RequestValue(GetMovieDataUseCase.ListType.FAVORITED, new MovieDbRequest()),
                getDefaultCallback());
    }

    @Override
    public void onError(String error) {
        view.showError(error);
    }

    private UseCase.CallBack<GetMovieDataUseCase.ResponseValue> getDefaultCallback() {
        return new UseCase.CallBack<GetMovieDataUseCase.ResponseValue>() {

            @Override
            public void onSuccess(GetMovieDataUseCase.ResponseValue response) {
                List<Movie> movies = response.getMovies();

                view.showMovies(movies);
                view.showProgress(false);
            }

            @Override
            public void onError(String error) {
                view.showError(error);

                view.showProgress(false);
            }
        };
    }
}
