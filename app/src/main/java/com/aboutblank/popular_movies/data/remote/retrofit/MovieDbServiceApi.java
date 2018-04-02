package com.aboutblank.popular_movies.data.remote.retrofit;

import com.aboutblank.popular_movies.data.domain.ListOfMovieItems;
import com.aboutblank.popular_movies.data.domain.ListOfMovieReviews;
import com.aboutblank.popular_movies.data.domain.ListOfMovieVideos;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDbServiceApi {

    @GET("movie/popular")
    Call<ListOfMovieItems> getPopularMovies(@Query("language") String lang,
                                            @Query("page") int page, @Query("region") String region);

    @GET("movie/top_rated")
    Call<ListOfMovieItems> getHighestRatedMovies(@Query("language") String lang,
                                                 @Query("page") int page, @Query("region") String region);

    @GET("/movie/{id}/videos")
    Call<ListOfMovieVideos> getMovieVideos(@Path("id") String movieId, @Query("language") String lang);

    @GET("/movie/{id}/reviews")
    Call<ListOfMovieReviews> getMovieReviews(@Path("id") String movieId, @Query("language") String lang,
                                             @Query("page") int page);
    @GET("genre/movie/list")
    Call<Map<Integer, String>> getMovieGenres(@Query("language") String lang);
}
