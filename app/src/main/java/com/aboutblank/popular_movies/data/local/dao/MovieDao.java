//package com.aboutblank.popular_movies.data.local.dao;
//
//import android.arch.persistence.room.Dao;
//import android.arch.persistence.room.Insert;
//import android.arch.persistence.room.OnConflictStrategy;
//import android.arch.persistence.room.Query;
//
//import com.aboutblank.popular_movies.movies.domain.model.Movie;
//
//import java.util.List;
//
//@Dao
//public interface MovieDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    public void addMovie(Movie movie);
//
//    @Query("SELECT * FROM movies WHERE id LIKE :id LIMIT 1")
//    public Movie getMovie(int id);
//
//    @Query("SELECT * FROM movies")
//    public List<Movie> getAllMovies();
//
//    @Query("DELETE FROM movies")
//    public void deleteAllMovies();
//}
