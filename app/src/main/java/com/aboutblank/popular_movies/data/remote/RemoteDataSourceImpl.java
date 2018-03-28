package com.aboutblank.popular_movies.data.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.aboutblank.popular_movies.data.DataSource;
import com.aboutblank.popular_movies.data.domain.ListOfMovies;
import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.data.remote.retrofit.MovieDbApiGenerator;
import com.aboutblank.popular_movies.data.remote.retrofit.MovieDbServiceApi;
import com.aboutblank.popular_movies.utils.MovieUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSourceImpl implements DataSource {
    private MovieDbServiceApi dbService;

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
    public void getHighestRatedMovies(@NonNull final LoadDataCallBack callBack) {
        MovieDbRequest request = callBack.getRequest();
        Call<ListOfMovies> call = dbService.getHighestRatedMovies(request.getLanguage(), request.getPage(), request.getRegion());

        Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Fetching highest rated movies with: " + request.toString());

        call.enqueue(new Callback<ListOfMovies>() {
            @Override
            public void onResponse(Call<ListOfMovies> call, Response<ListOfMovies> response) {

                Log.d(RemoteDataSourceImpl.class.getSimpleName(), response.body().toString());
                callBack.onDataLoaded(MovieUtils.entryListToMovieList(response.body().getMovies()));
            }

            @Override
            public void onFailure(Call<ListOfMovies> call, Throwable t) {

                Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Highest Movies response: " + call);
                Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Error code: " + t);

                callBack.onDataNotAvailable(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void getPopularMovies(@NonNull final LoadDataCallBack callBack) {
        MovieDbRequest request = callBack.getRequest();
        Call<ListOfMovies> call = dbService.getPopularMovies(request.getLanguage(), request.getPage(), request.getRegion());

        Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Fetching popular movies with: " + request.toString());

        call.enqueue(new Callback<ListOfMovies>() {
            @Override
            public void onResponse(Call<ListOfMovies> call, Response<ListOfMovies> response) {
                if (response.body() != null) {

                    Log.d(RemoteDataSourceImpl.class.getSimpleName(), response.body().toString());

                    callBack.onDataLoaded(MovieUtils.entryListToMovieList(response.body().getMovies()));
                }
            }

            @Override
            public void onFailure(Call<ListOfMovies> call, Throwable t) {

                Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Popular Movies response: " + call);
                Log.d(RemoteDataSourceImpl.class.getSimpleName(), "Error code: " + t);

                callBack.onDataNotAvailable(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void getListOfGenres(@NonNull LoadDataCallBack callBack) {
        //TODO
        callBack.onDataNotAvailable("Not implemented yet");
    }
}
