package com.aboutblank.popular_movies;

import com.aboutblank.popular_movies.data.domain.MovieEntry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Assert;
import org.junit.Test;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    String oneMovie = "{\"vote_count\":6453,\"id\":269149,\"video\":false,\"vote_average\":7.7,\"title\":\"Zootopia\",\"popularity\":573.927608,\"poster_path\":\"\\/sM33SANp9z6rXW8Itn7NnG1GOEs.jpg\",\"original_language\":\"en\",\"original_title\":\"Zootopia\",\"genre_ids\":[16,12,10751,35],\"backdrop_path\":\"\\/mhdeE1yShHTaDbJVdWyTlzFvNkr.jpg\",\"adult\":false,\"overview\":\"Determined to prove herself, Officer Judy Hopps, the first bunny on Zootopia's police force, jumps at the chance to crack her first case - even if it means partnering with scam-artist fox Nick Wilde to solve the mystery.\",\"release_date\":\"2016-02-11\"}";

    @Test
    public void test_Gson_Converting_Movie() {
        Gson gson = new GsonBuilder().create();

        GsonConverterFactory converterFactory = GsonConverterFactory.create();

        MovieEntry movie = gson.fromJson(oneMovie, MovieEntry.class);

        System.out.println(movie.toString());

        Assert.assertNotEquals(movie, null);
    }
}