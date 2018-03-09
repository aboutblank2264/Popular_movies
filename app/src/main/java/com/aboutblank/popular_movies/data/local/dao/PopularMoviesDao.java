//package com.aboutblank.popular_movies.data.local.dao;
//
//import android.arch.persistence.room.Dao;
//import android.arch.persistence.room.Insert;
//import android.arch.persistence.room.OnConflictStrategy;
//import android.arch.persistence.room.Query;
//
//import com.aboutblank.popular_movies.data.local.entity.PopularMoviesEntity;
//import com.aboutblank.popular_movies.domain.model.Movie;
//
//import java.util.List;
//
//@Dao
//public interface PopularMoviesDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void addPage(PopularMoviesEntity entity);
//
//    @Query("SELECT * FROM popularMovies WHERE pageId = 1 LIMIT 1")
//    PopularMoviesEntity getMostPopularMovies();
//
//    @Query("SELECT * FROM popularMovies WHERE pageId = :page LIMIT 1")
//    List<Movie> getMostPopularMovies(int page);
//
//    @Query("DELETE FROM popularMovies")
//    void deleteAllPages();
//}
