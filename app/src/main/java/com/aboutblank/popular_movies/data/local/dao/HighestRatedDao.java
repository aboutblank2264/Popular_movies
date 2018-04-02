package com.aboutblank.popular_movies.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.aboutblank.popular_movies.data.local.domain.HighestRatedMoviesEntity;
import com.aboutblank.popular_movies.data.local.domain.MovieEntity;

import java.util.List;

@Dao
public interface HighestRatedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPage(HighestRatedMoviesEntity entity);

    @Query("SELECT * FROM movie_entity where highest_id = :pageId")
    List<MovieEntity> getMoviesForPage(int pageId);

    @Query("DELETE FROM highest_rated")
    void deleteAllPages();
}
