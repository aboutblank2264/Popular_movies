package com.aboutblank.popular_movies.data.remote.retrofit;

import com.aboutblank.popular_movies.data.domain.ListOfMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDbServiceApi {

    @GET("movie/popular")
    Call<ListOfMovies> getPopularMovies(@Query("language") String lang,
                                        @Query("page") int page, @Query("region") String region);

    @GET("movie/top_rated")
    Call<ListOfMovies> getHighestRatedMovies(@Query("language") String lang,
                                                   @Query("page") int page, @Query("region") String region);

//    @GET("genre/movie/list")
}
