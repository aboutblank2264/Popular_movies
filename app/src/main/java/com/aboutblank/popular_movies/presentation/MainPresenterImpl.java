package com.aboutblank.popular_movies.presentation;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.UseCase;
import com.aboutblank.popular_movies.UseCaseExecutor;
import com.aboutblank.popular_movies.data.DataSource;
import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.presentation.model.Movie;
import com.aboutblank.popular_movies.presentation.usecase.GetMovieData;

import java.util.List;

public class MainPresenterImpl implements MainPresenter {
    private MainPresenter.View view;
    private DataSource remoteDataSource;
    private UseCaseExecutor executor;

    private GetMovieData getMovieData;

    public MainPresenterImpl(@NonNull MainPresenter.View view,
                             @NonNull DataSource remoteDataSource,
                             @NonNull UseCaseExecutor executor) {
        this.view = view;
        this.remoteDataSource = remoteDataSource;
        this.executor = executor;

        this.view.setPresenter(this);

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
                new UseCase.CallBack<GetMovieData.ResponseValue>(){

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
                });
    }

    private void loadHighestRatedMovies() {
        executor.execute(getMovieData,
                new GetMovieData.RequestValue(GetMovieData.ListType.HIGHEST_RATED, new MovieDbRequest()),
                new UseCase.CallBack<GetMovieData.ResponseValue>(){

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
                });
    }

    @Override
    public void onError(String error) {

    }
}
