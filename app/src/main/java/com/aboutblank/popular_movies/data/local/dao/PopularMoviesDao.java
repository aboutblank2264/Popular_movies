package com.aboutblank.popular_movies.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.aboutblank.popular_movies.data.local.domain.PopularMoviesEntity;

@Dao
public interface PopularMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPage(PopularMoviesEntity entity);

    @Query("DELETE FROM popular")
    void deleteAllPages();
}
