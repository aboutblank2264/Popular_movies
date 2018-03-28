//package com.aboutblank.popular_movies.data.local.dao;
//
//import android.arch.persistence.room.Dao;
//import android.arch.persistence.room.Insert;
//import android.arch.persistence.room.OnConflictStrategy;
//import android.arch.persistence.room.Query;
//
//import com.aboutblank.popular_movies.movies.domain.model.MovieEntry;
//
//import java.util.List;
//
//@Dao
//public interface MovieDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    public void addMovie(MovieEntry movie);
//
//    @Query("SELECT * FROM movies WHERE id LIKE :id LIMIT 1")
//    public MovieEntry getMovie(int id);
//
//    @Query("SELECT * FROM movies")
//    public List<MovieEntry> getAllMovies();
//
//    @Query("DELETE FROM movies")
//    public void deleteAllMovies();
//}
