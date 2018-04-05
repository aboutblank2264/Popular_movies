package com.aboutblank.popular_movies.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.aboutblank.popular_movies.data.domain.ListOfGenres;

@Dao
public interface GenreDao {
    @Insert
    void insert(ListOfGenres genres);

    @Query("SELECT * FROM genres WHERE language = :lang")
    ListOfGenres getGenresList(String lang);
}
