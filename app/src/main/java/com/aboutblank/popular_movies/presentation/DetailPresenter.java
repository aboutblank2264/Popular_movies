package com.aboutblank.popular_movies.presentation;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.presentation.base.BasePresenter;
import com.aboutblank.popular_movies.presentation.base.BaseView;
import com.aboutblank.popular_movies.presentation.model.Movie;
import com.aboutblank.popular_movies.presentation.model.MovieReview;
import com.aboutblank.popular_movies.presentation.model.MovieVideo;

import java.util.List;

public interface DetailPresenter extends BasePresenter {

    void getMovieVideos(@NonNull String movieId);

    void getMovieReviews(@NonNull String movieId);

    void getMovieGenres(@NonNull List<Integer> genres);

    interface View extends BaseView<DetailPresenter> {
        Movie getMovie();

        void showGenres(List<String> genres);

        void showReviews(List<MovieReview> reviews);

        void showVideos(List<MovieVideo> videos);

        void finishedLoading(boolean value);
    }
}
