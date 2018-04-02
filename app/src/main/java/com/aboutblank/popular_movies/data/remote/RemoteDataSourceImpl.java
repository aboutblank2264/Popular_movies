package com.aboutblank.popular_movies.data.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.aboutblank.popular_movies.data.DataSource;
import com.aboutblank.popular_movies.data.domain.ListOfMovieItems;
import com.aboutblank.popular_movies.data.domain.ListOfMovieReviews;
import com.aboutblank.popular_movies.data.domain.ListOfMovieVideos;
import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.data.remote.retrofit.MovieDbApiGenerator;
import com.aboutblank.popular_movies.data.remote.retrofit.MovieDbServiceApi;
import com.aboutblank.popular_movies.utils.MovieUtils;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSourceImpl implements DataSource {
    private final MovieDbServiceApi dbService;

    private static RemoteDataSourceImpl instance;

    public static RemoteDataSourceImpl getInstance() {
        if (instance == null) {
            instance = new RemoteDataSourceImpl();
        }

        return instance;
    }

    private RemoteDataSourceImpl() {
        dbService = MovieDbApiGenerator.createTheMovieDbService();
    }

    @Override
    public void getHighestRatedMovies(@NonNull final LoadMovieDataCallBack callBack) {
        MovieDbRequest request = callBack.getRequest();
        Call<ListOfMovieItems> call = dbService.getHighestRatedMovies(request.getLanguage(), request.getPage(), request.getRegion());

        Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Fetching highest rated movies with: " + request.toString());

        call.enqueue(new Callback<ListOfMovieItems>() {
            @Override
            public void onResponse(Call<ListOfMovieItems> call, Response<ListOfMovieItems> response) {

                Log.d(RemoteDataSourceImpl.class.getSimpleName(), response.body().toString());
                callBack.onDataLoaded(MovieUtils.entryListToMovieList(response.body().getMovies()));
            }

            @Override
            public void onFailure(Call<ListOfMovieItems> call, Throwable t) {

                Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Highest Movies response: " + call);
                Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Error code: " + t);

                callBack.onDataNotAvailable(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void getPopularMovies(@NonNull final LoadMovieDataCallBack callBack) {
        MovieDbRequest request = callBack.getRequest();
        Call<ListOfMovieItems> call = dbService.getPopularMovies(request.getLanguage(), request.getPage(), request.getRegion());

        Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Fetching popular movies with: " + request.toString());

        call.enqueue(new Callback<ListOfMovieItems>() {
            @Override
            public void onResponse(Call<ListOfMovieItems> call, Response<ListOfMovieItems> response) {
                if (response.body() != null) {

                    Log.d(RemoteDataSourceImpl.class.getSimpleName(), response.body().toString());

                    callBack.onDataLoaded(MovieUtils.entryListToMovieList(response.body().getMovies()));
                }
            }

            @Override
            public void onFailure(Call<ListOfMovieItems> call, Throwable t) {

                Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Popular Movies response: " + call);
                Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Error code: " + t);

                callBack.onDataNotAvailable(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void getMovieReviews(@NonNull final LoadMovieReviewCallBack callBack) {
        MovieDbRequest request = callBack.getRequest();

        Call<ListOfMovieReviews> call = dbService.getMovieReviews(request.getId(), request.getLanguage(), request.getPage());

        Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Fetching movie reviews with: " + request.toString());

        call.enqueue(new Callback<ListOfMovieReviews>() {
            @Override
            public void onResponse(Call<ListOfMovieReviews> call, Response<ListOfMovieReviews> response) {
                if(response.body() != null) {

                    Log.d(RemoteDataSourceImpl.class.getSimpleName(), response.body().toString());

                    callBack.onDataLoaded(response.body().getReviews());
                }
            }

            @Override
            public void onFailure(Call<ListOfMovieReviews> call, Throwable t) {

                Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Fetch movie reviews response: " + call);
                Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Error code: " + t);

                callBack.onDataNotAvailable(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void getMovieVideos(@NonNull final LoadMovieVideosCallBack callBack) {
        MovieDbRequest request = callBack.getRequest();

        Call<ListOfMovieVideos> call = dbService.getMovieVideos(request.getId(), request.getLanguage());

        Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Fetching movie videos with: " + request.toString());

        call.enqueue(new Callback<ListOfMovieVideos>() {
            @Override
            public void onResponse(Call<ListOfMovieVideos> call, Response<ListOfMovieVideos> response) {
                if(response.body() != null) {

                    Log.d(RemoteDataSourceImpl.class.getSimpleName(), response.body().toString());

                    callBack.onDataLoaded(response.body().getVideos());
                }
            }

            @Override
            public void onFailure(Call<ListOfMovieVideos> call, Throwable t) {

                Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Fetch movie videos response: " + call);
                Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Error code: " + t);

                callBack.onDataNotAvailable(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void getListOfGenres(@NonNull final LoadGenreCallBack callBack) {
        String language = callBack.getLanguage();
        Call<Map<Integer, String>> call = dbService.getMovieGenres(language);

        Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Fetching list of genres in language: " + language);
        call.enqueue(new Callback<Map<Integer, String>>() {
            @Override
            public void onResponse(Call<Map<Integer, String>> call, Response<Map<Integer, String>> response) {
                if(response.body() != null) {
                    Log.d(RemoteDataSourceImpl.class.getSimpleName(), response.body().toString());
                    callBack.onDataLoaded(response.body());
                }
            }

            @Override
            public void onFailure(Call<Map<Integer, String>> call, Throwable t) {
                Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Load Genres response: " + call);
                Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Error code: " + t);

                callBack.onDataNotAvailable(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void getListOfData(@NonNull LoadListOfDataCallBack<?> callBack) {

    }
}
