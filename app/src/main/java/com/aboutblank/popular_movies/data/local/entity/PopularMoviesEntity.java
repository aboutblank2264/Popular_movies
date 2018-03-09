//package com.aboutblank.popular_movies.data.local.entity;
//
//import android.arch.persistence.room.PrimaryKey;
//import android.arch.persistence.room.Relation;
//
//import com.aboutblank.popular_movies.domain.model.Movie;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity(tableName = "popularMovies")
//@TypeConverters(MovieListTypeConverter.class)
//public class PopularMoviesEntity {
//    @PrimaryKey
//    private int pageId;
//
//    @Relation(parentColumn = "pageId", entityColumn = "id", entity = Movie.class)
//    private List<Movie> movieList = new ArrayList<>();
//
//    public PopularMoviesEntity(int pageId, List<Movie> movieList) {
//        this.pageId = pageId;
//        this.movieList = movieList;
//    }
//
//    public PopularMoviesEntity() {
//    }
//
//    public void setPageId(int pageId) {
//        this.pageId = pageId;
//    }
//
//    public int getPageId() {
//        return pageId;
//    }
//
//    public void setMovieList(List<Movie> movieList) {
//        this.movieList = movieList;
//    }
//
//    public List<Movie> getMovieList() {
//        return movieList;
//    }
//}
