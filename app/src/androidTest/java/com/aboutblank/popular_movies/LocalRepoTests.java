package com.aboutblank.popular_movies;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.aboutblank.popular_movies.data.DataSource;
import com.aboutblank.popular_movies.data.domain.MovieItem;
import com.aboutblank.popular_movies.data.local.LocalDataSource;
import com.aboutblank.popular_movies.data.local.LocalDataSourceImpl;
import com.aboutblank.popular_movies.data.local.MoviesLocalDatabase;
import com.aboutblank.popular_movies.data.local.domain.MovieEntity;
import com.aboutblank.popular_movies.presentation.DatabaseReader;
import com.aboutblank.popular_movies.presentation.model.Movie;
import com.aboutblank.popular_movies.utils.MovieUtils;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class LocalRepoTests {
    private LocalDataSource localDataSource;

    private MovieItem movie1;

    @Before
    public void createDb() {
        MoviesLocalDatabase moviesLocalDatabase = MoviesLocalDatabase.getTestableMoviesDatabase(
                new DatabaseReader(InstrumentationRegistry.getContext()));
        localDataSource = new LocalDataSourceImpl(moviesLocalDatabase);

        movie1 = new MovieItem();
        movie1.setId(123);
        movie1.setTitle("test movie");
        movie1.setVoteAverage(8.23);
        movie1.setOverview("test overview");
        movie1.setPopularity(6.23);
        movie1.setReleaseDate("04-18-2018");

        localDataSource.saveMovie(new LocalDataSource.SaveMovieCallback() {
            @Override
            public MovieEntity getMovieEntity() {
                return new MovieEntity(movie1.getId(), movie1);
            }

            @Override
            public void onDataSaveFailure(String error) {
                Log.e("Test 1, part 1", "unable to save movie in test repo");
            }
        });
    }

    @After
    public void after() {
        localDataSource.close();
    }

    @Test
    public void getMovie() {

        final List<Movie> movieList = new ArrayList<>();
        localDataSource.getMovie(new DataSource.LoadMovieCallback() {
            @Override
            public int getMovieId() {
                return 123;
            }

            @Override
            public void onDataLoaded(Movie movieItem) {
               movieList.add(movieItem);
            }

            @Override
            public void onDataNotAvailable(String error) {
                Log.e("Test 1, part 2", "unable to retrieve movie in test repo");
            }
        });

        Assert.assertEquals(movieList.get(0), MovieUtils.entryToMovie(movie1));
    }

    @Test
    public void addMovieToFavorites() {
        localDataSource.addMovieToFavorite(new DataSource.AddRemoveMovieFavoritesCallBack() {
            @Override
            public int getMovieId() {
                return 123;
            }

            @Override
            public boolean toUpdate() {
                return false;
            }

            @Override
            public boolean valueToUpdate() {
                return true;
            }

            @Override
            public void onDataLoaded(boolean isFavorite) {
                Assert.assertTrue(isFavorite);
            }

            @Override
            public void onDataNotAvailable(String error) {

            }
        });
    }

    @Test
    public void checkMovieIsFavorites() {
        addMovieToFavorites();

        localDataSource.checkIfMovieIsFavorited(new DataSource.CheckIfMovieIsFavoritedCallBack() {
            @Override
            public int getMovieId() {
                return 123;
            }

            @Override
            public void onDataLoaded(boolean isFavorite) {
                Assert.assertTrue(isFavorite);
            }

            @Override
            public void onDataNotAvailable(String error) {

            }
        });
    }
}
