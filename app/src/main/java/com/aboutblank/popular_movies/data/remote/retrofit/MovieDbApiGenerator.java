package com.aboutblank.popular_movies.data.remote.retrofit;

import android.support.annotation.NonNull;

import com.aboutblank.popular_movies.utils.ApiKey;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit API generator
 *
 * Reference: http://www.vogella.com/tutorials/Retrofit/article.html
 */
public class MovieDbApiGenerator {
    private final static String MOVIE_DB_URL = "https://api.themoviedb.org/3/";

    private final static Interceptor apiInterceptor = new Interceptor() {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder().addQueryParameter("api_key", ApiKey.apiKey()).build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        }
    };

    private final static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC);

    private final static OkHttpClient httpClient = new OkHttpClient().newBuilder()
            .addInterceptor(apiInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build();

    private final static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(MOVIE_DB_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create());

    private final static Retrofit retrofit = builder.build();

    public final static MovieDbServiceApi createTheMovieDbService() {
        return retrofit.create(MovieDbServiceApi.class);
    }
}
