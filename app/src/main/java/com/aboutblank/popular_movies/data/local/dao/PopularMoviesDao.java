package com.aboutblank.popular_movies.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.aboutblank.popular_movies.data.local.domain.MovieEntity;
import com.aboutblank.popular_movies.data.local.domain.PopularMoviesEntity;

import java.util.List;

@Dao
public interface PopularMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPage(PopularMoviesEntity entity);

    @Query("SELECT * FROM movie_entity where popular_id = :pageId")
    List<MovieEntity> getMoviesForPage(int pageId);

    @Query("DELETE FROM popular")
    void deleteAllPages();
}
