package com.aboutblank.popular_movies.presentation;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.presentation.base.BasePresenter;
import com.aboutblank.popular_movies.presentation.base.BaseView;
import com.aboutblank.popular_movies.presentation.model.Movie;
import com.aboutblank.popular_movies.presentation.usecase.GetMovieDataUseCase;

import java.util.List;

/**
 * Presenter interface, used to act as bridge between View layer and lower layers.
 */
public interface MainPresenter extends BasePresenter {

    interface View extends BaseView<MainPresenter> {

        void showMovies(@NonNull List<Movie> movies);

        GetMovieDataUseCase.ListType showMovieType();
    }

}
