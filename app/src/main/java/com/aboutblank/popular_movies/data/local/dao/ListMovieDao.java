package com.aboutblank.popular_movies.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.aboutblank.popular_movies.data.local.domain.ListOfMovies;
import com.aboutblank.popular_movies.data.local.domain.MovieEntity;

import java.util.List;

@Dao
public interface ListMovieDao {
    @Insert
    void insert(ListOfMovies listOfMovies);

    @Query("SELECT * FROM movie_entity INNER JOIN list_movie ON " +
            "movie_entity.movieId = list_movie.movieId WHERE " +
            "list_movie.listId=:listId")
    List<MovieEntity> getListOfMovies(final String listId);
}
