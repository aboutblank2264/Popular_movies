package com.aboutblank.popular_movies.data.remote.retrofit;

import com.aboutblank.popular_movies.data.domain.Genre;
import com.aboutblank.popular_movies.data.domain.ListOfMovieItems;
import com.aboutblank.popular_movies.data.domain.ListOfMovieReviews;
import com.aboutblank.popular_movies.data.domain.ListOfMovieVideos;

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

    @GET("genre/movie/list")
    Call<Genre.ListOfGenres> getMovieGenres(@Query("language") String lang);

    @GET("/3/movie/{id}/videos")
    Call<ListOfMovieVideos> getMovieVideos(@Path("id") String movieId, @Query("language") String lang);
    @GET("/3/movie/{id}/getReviews")
    Call<ListOfMovieReviews> getMovieReviews(@Path("id") String movieId, @Query("language") String lang,
                                             @Query("page") int page);
}
