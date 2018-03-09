//package com.aboutblank.popular_movies.data;
//
//import android.content.Context;
//
//import com.aboutblank.popular_movies.data.local.MoviesLocalDatabase;
//import com.aboutblank.popular_movies.data.local.entity.HighestRatedMoviesEntity;
//import com.aboutblank.popular_movies.data.local.entity.PopularMoviesEntity;
//import com.aboutblank.popular_movies.domain.model.HighestRatedMovies;
//import com.aboutblank.popular_movies.domain.model.PopularMovies;
//
//public class RemoteLocalDbHandler {
//    private MoviesLocalDatabase localDatabase;
//
//    private static RemoteLocalDbHandler handler;
//
//    public static RemoteLocalDbHandler getHandler(Context context) {
//        if (handler == null) {
//            handler = new RemoteLocalDbHandler(context);
//        }
//
//        return handler;
//    }
//
//    private RemoteLocalDbHandler(Context context) {
//        localDatabase = MoviesLocalDatabase.getMoviesDatabase(context);
//    }
//
//    public void onSuccess(PopularMovies response) {
//        PopularMoviesEntity entity = new PopularMoviesEntity(response.getPage(), response.getResults());
//        localDatabase.popularMoviesDao().addPage(entity);
//    }
//
//    public void onSuccess(HighestRatedMovies response) {
//        HighestRatedMoviesEntity entity = new HighestRatedMoviesEntity(response.getPage(), response.getResults());
//
//        localDatabase.highestRatedDao().addPage(entity);
//    }
//
//    public PopularMovies onPopularMoviesFail() {
//        PopularMoviesEntity entity = localDatabase.popularMoviesDao().getMostPopularMovies();
//
//        if(entity != null) {
//            return new PopularMovies(entity.getPageId(), entity.getMovieList());
//        }
//        return new PopularMovies();
//    }
//
//    public HighestRatedMovies onHighestMoviesFail() {
//        HighestRatedMoviesEntity entity = localDatabase.highestRatedDao().getHighestRatedMovies();
//
//        if(entity != null) {
//            return new HighestRatedMovies(entity.getPageId(), entity.getMovieList());
//        }
//
//        return new HighestRatedMovies();
//    }
//}
