package com.aboutblank.popular_movies.presentation;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.UseCase;
import com.aboutblank.popular_movies.UseCaseExecutor;
import com.aboutblank.popular_movies.data.DataSource;
import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.presentation.model.Movie;
import com.aboutblank.popular_movies.presentation.usecase.GetMovieData;

import java.util.List;

/**
 * Class that acts as bridge between user actions and UI.
 * Communicates between Domain layer and View layer
 */
public class MainPresenterImpl implements MainPresenter {
    private final MainPresenter.View view;
    private final UseCaseExecutor executor;

    private GetMovieData getMovieData;

    public MainPresenterImpl(@NonNull MainPresenter.View view,
                             @NonNull DataSource remoteDataSource,
                             @NonNull UseCaseExecutor executor) {
        this.view = view;
        this.executor = executor;

        getMovieData = new GetMovieData(remoteDataSource);

    }

    @Override
    public void start() {
        loadData();
    }

    private void loadData() {
        view.showProgress(true);

        switch (view.showMovieType()) {
            case POPULAR:
                loadPopularMovieData();
                break;
            case HIGHEST_RATED:
                loadHighestRatedMovies();
                break;
            default:
                break;
        }
    }

    private void loadPopularMovieData() {
        executor.execute(getMovieData,
                new GetMovieData.RequestValue(GetMovieData.ListType.POPULAR, new MovieDbRequest()),
                getDefaultCallback());
    }

    private void loadHighestRatedMovies() {
        executor.execute(getMovieData,
                new GetMovieData.RequestValue(GetMovieData.ListType.HIGHEST_RATED, new MovieDbRequest()),
                getDefaultCallback());
    }

    @Override
    public void onError(String error) {
        view.showError(error);
    }

    private UseCase.CallBack<GetMovieData.ResponseValue> getDefaultCallback() {
        return new UseCase.CallBack<GetMovieData.ResponseValue>() {

            @Override
            public void onSuccess(GetMovieData.ResponseValue response) {
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
