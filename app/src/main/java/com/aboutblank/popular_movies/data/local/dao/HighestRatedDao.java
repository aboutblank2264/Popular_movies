//package com.aboutblank.popular_movies.data.local.dao;
//
//import android.arch.persistence.room.Dao;
//import android.arch.persistence.room.Insert;
//import android.arch.persistence.room.OnConflictStrategy;
//import android.arch.persistence.room.Query;
//
//import com.aboutblank.popular_movies.data.local.entity.HighestRatedMoviesEntity;
//
//@Dao
//public interface HighestRatedDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void addPage(HighestRatedMoviesEntity entity);
//
//    @Query("SELECT * FROM highestRatedMovies WHERE pageId = 1 LIMIT 1")
//    HighestRatedMoviesEntity getHighestRatedMovies();
//
//    @Query("SELECT * FROM highestRatedMovies WHERE pageId = :page LIMIT 1")
//    HighestRatedMoviesEntity getHighestRatedMovies(int page);
//
//    @Query("DELETE FROM highestRatedMovies")
//    void deleteAllPages();
//}
