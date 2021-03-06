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

    void getMovieFavorited(@NonNull String movieId);

    void toggleMovieFavorite(@NonNull String movieId, boolean value);

    interface View extends BaseView<DetailPresenter> {
        Movie getMovie();

        void showReviews(List<MovieReview> reviews);

        void showVideos(List<MovieVideo> videos);

        void showFavorited(boolean favorite);
    }
}
