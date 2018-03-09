//package com.aboutblank.popular_movies.data.local;
//
//import android.arch.persistence.room.Database;
//import android.arch.persistence.room.Room;
//import android.arch.persistence.room.RoomDatabase;
//import android.content.Context;
//
//import com.aboutblank.popular_movies.data.local.entity.HighestRatedMoviesEntity;
//import com.aboutblank.popular_movies.data.local.entity.PopularMoviesEntity;
//import com.aboutblank.popular_movies.domain.model.Movie;
//
//@Database(entities = {Movie.class, HighestRatedMoviesEntity.class, PopularMoviesEntity.class},
//        version = 1)
//public abstract class MoviesLocalDatabase extends RoomDatabase {
//
//    public abstract MovieDao movieDao();
//    public abstract PopularMoviesDao popularMoviesDao();
//    public abstract HighestRatedDao highestRatedDao();
//
//    private static MoviesLocalDatabase initializedDatabase;
//
//    private static final Object lock = new Object();
//
//    public static MoviesLocalDatabase getMoviesDatabase(Context context) {
//        synchronized (lock) {
//            if (initializedDatabase == null) {
//                initializedDatabase = Room.databaseBuilder(context, MoviesLocalDatabase.class, "moviesDatabase")
//                        .fallbackToDestructiveMigration()
//                        .build();
//            }
//
//            return initializedDatabase;
//        }
//    }
//
//    public static void destroyInstance() {
//        initializedDatabase = null;
//    }
//}
