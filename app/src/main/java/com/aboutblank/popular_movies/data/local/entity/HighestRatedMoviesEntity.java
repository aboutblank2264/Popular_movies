//package com.aboutblank.popular_movies.data.local.entity;
//
//import android.arch.persistence.room.PrimaryKey;
//import android.arch.persistence.room.Relation;
//
//import com.aboutblank.popular_movies.domain.model.Movie;
//
//import java.util.List;
//
//@Entity(tableName = "highestRatedMovies")
//@TypeConverters(MovieListTypeConverter.class)
//public class HighestRatedMoviesEntity {
//
//    @PrimaryKey
//    private int pageId;
//
//    @Relation(parentColumn = "pageId", entityColumn = "id", entity = Movie.class)
//    private List<Movie> movieList;
//
//    public HighestRatedMoviesEntity(int pageId, List<Movie> movieList) {
//        this.pageId = pageId;
//        this.movieList = movieList;
//    }
//
//    public HighestRatedMoviesEntity() {
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
