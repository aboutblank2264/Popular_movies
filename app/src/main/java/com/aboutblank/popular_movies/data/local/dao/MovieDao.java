package com.aboutblank.popular_movies.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import com.aboutblank.popular_movies.data.local.domain.MovieEntity;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<MovieEntity> entity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(List<MovieEntity> entity);
}
