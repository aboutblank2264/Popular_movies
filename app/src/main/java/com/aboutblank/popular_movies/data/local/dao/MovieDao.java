package com.aboutblank.popular_movies.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.aboutblank.popular_movies.data.local.domain.MovieEntity;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MovieEntity entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<MovieEntity> entity);

    @Query("SELECT * FROM movie_entity WHERE movieId = :movieId")
    MovieEntity getMovie(int movieId);

    @Query("SELECT movieId FROM movie_entity WHERE favorite = 1")
    List<Integer> getFavoritedMovies();

    @Query("UPDATE movie_entity SET favorite = :favorite WHERE movieId = :id ")
    void setFavorite(int id, boolean favorite);

    @Query("SELECT favorite FROM movie_entity WHERE movieId = :id")
    boolean isFavorite(int id);
}
