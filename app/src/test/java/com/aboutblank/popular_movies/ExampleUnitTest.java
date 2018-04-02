package com.aboutblank.popular_movies;

import com.aboutblank.popular_movies.data.domain.Genre;
import com.aboutblank.popular_movies.data.domain.MovieItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private final String oneMovie = "{\"vote_count\":6453,\"pageId\":269149,\"video\":false,\"vote_average\":7.7,\"title\":\"Zootopia\",\"popularity\":573.927608,\"poster_path\":\"\\/sM33SANp9z6rXW8Itn7NnG1GOEs.jpg\",\"original_language\":\"en\",\"original_title\":\"Zootopia\",\"genre_ids\":[16,12,10751,35],\"backdrop_path\":\"\\/mhdeE1yShHTaDbJVdWyTlzFvNkr.jpg\",\"adult\":false,\"overview\":\"Determined to prove herself, Officer Judy Hopps, the first bunny on Zootopia's police force, jumps at the chance to crack her first case - even if it means partnering with scam-artist fox Nick Wilde to solve the mystery.\",\"release_date\":\"2016-02-11\"}";
    private final String genreList = "{\"genres\":[{\"id\":28,\"name\":\"Action\"},{\"id\":12,\"name\":\"Adventure\"},{\"id\":16,\"name\":\"Animation\"},{\"id\":35,\"name\":\"Comedy\"},{\"id\":80,\"name\":\"Crime\"},{\"id\":99,\"name\":\"Documentary\"},{\"id\":18,\"name\":\"Drama\"},{\"id\":10751,\"name\":\"Family\"},{\"id\":14,\"name\":\"Fantasy\"},{\"id\":36,\"name\":\"History\"},{\"id\":27,\"name\":\"Horror\"},{\"id\":10402,\"name\":\"Music\"},{\"id\":9648,\"name\":\"Mystery\"},{\"id\":10749,\"name\":\"Romance\"},{\"id\":878,\"name\":\"Science Fiction\"},{\"id\":10770,\"name\":\"TV Movie\"},{\"id\":53,\"name\":\"Thriller\"},{\"id\":10752,\"name\":\"War\"},{\"id\":37,\"name\":\"Western\"}]}";

    @Test
    public void test_Gson_Converting_Movie() {
        Gson gson = new GsonBuilder().create();

        MovieItem movie = gson.fromJson(oneMovie, MovieItem.class);

        System.out.println(movie.toString());

        Assert.assertNotEquals(movie, null);
    }

    @Test
    public void test_Gson_Converting_GenreList() {
        Gson gson = new GsonBuilder().create();
        
        Type type = new TypeToken<Genre.ListofGenres>() {}.getType();

        List<Genre> genres = gson.fromJson(genreList, type);

        System.out.println(genres.toString());

        Assert.assertNotEquals(genres, null);
    }
}