package com.aboutblank.popular_movies.presentation;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.presentation.model.Movie;
import com.aboutblank.popular_movies.presentation.base.BasePresenter;
import com.aboutblank.popular_movies.presentation.base.BaseView;
import com.aboutblank.popular_movies.presentation.usecase.GetMovieData;

import java.util.List;

public interface MainPresenter extends BasePresenter {

    interface View extends BaseView<MainPresenter> {

        void showProgress(boolean active);

        void showError(@NonNull String error);

        void showMovies(@NonNull List<Movie> movies);

        void chanceMovieType(@NonNull GetMovieData.ListType listType);

        GetMovieData.ListType showMovieType();
    }

}
