package com.aboutblank.popular_movies.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.aboutblank.popular_movies.data.domain.ListOfGenres;
import com.aboutblank.popular_movies.data.local.dao.GenreDao;
import com.aboutblank.popular_movies.data.local.dao.HighestRatedDao;
import com.aboutblank.popular_movies.data.local.dao.ListMovieDao;
import com.aboutblank.popular_movies.data.local.dao.MovieDao;
import com.aboutblank.popular_movies.data.local.dao.PopularMoviesDao;
import com.aboutblank.popular_movies.data.local.domain.HighestRatedMoviesEntity;
import com.aboutblank.popular_movies.data.local.domain.ListOfMovies;
import com.aboutblank.popular_movies.data.local.domain.MovieEntity;
import com.aboutblank.popular_movies.data.local.domain.PopularMoviesEntity;
import com.aboutblank.popular_movies.presentation.DatabaseReader;

@Database(entities = {MovieEntity.class, ListOfGenres.class, HighestRatedMoviesEntity.class, PopularMoviesEntity.class, ListOfMovies.class},
        version = 1)
public abstract class MoviesLocalDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();
    public abstract GenreDao genreDao();

    public abstract HighestRatedDao highestRatedDao();
    public abstract PopularMoviesDao popularMoviesDao();
    public abstract ListMovieDao listMovieDao();

    private static MoviesLocalDatabase initializedDatabase;

    private static final Object lock = new Object();

    public static MoviesLocalDatabase getMoviesDatabase(DatabaseReader transporter) {
        synchronized (lock) {
            if (initializedDatabase == null) {
                initializedDatabase = Room.databaseBuilder(transporter.getContext(), MoviesLocalDatabase.class, "moviesDatabase")
                        .fallbackToDestructiveMigration()
                        .build();
            }

            return initializedDatabase;
        }
    }

    public static void destroyInstance() {
        initializedDatabase = null;
    }
}
