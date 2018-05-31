package com.aboutblank.popular_movies.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.aboutblank.popular_movies.data.local.dao.MovieDao;
import com.aboutblank.popular_movies.data.local.domain.MovieEntity;
import com.aboutblank.popular_movies.presentation.DatabaseReader;

@Database(entities = {MovieEntity.class},
        version = 3,
        exportSchema = false)
public abstract class MoviesLocalDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

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

    public static MoviesLocalDatabase getTestableMoviesDatabase(DatabaseReader transporter) {
        return Room.inMemoryDatabaseBuilder(transporter.getContext(), MoviesLocalDatabase.class)
                .fallbackToDestructiveMigration()
                .build();
    }

    public static void destroyInstance() {
        initializedDatabase = null;
    }
}
