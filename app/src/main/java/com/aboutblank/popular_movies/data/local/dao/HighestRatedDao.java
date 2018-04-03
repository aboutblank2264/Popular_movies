package com.aboutblank.popular_movies.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.aboutblank.popular_movies.data.local.domain.HighestRatedMoviesEntity;

@Dao
public interface HighestRatedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPage(HighestRatedMoviesEntity entity);

    @Query("DELETE FROM highest_rated")
    void deleteAllPages();
}
