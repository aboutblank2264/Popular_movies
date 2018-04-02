package com.aboutblank.popular_movies.presentation.usecase;

import android.util.Log;
import android.util.SparseArray;

import com.aboutblank.popular_movies.UseCase;
import com.aboutblank.popular_movies.data.DataSource;

import java.util.ArrayList;
import java.util.List;

public class GetGenresUseCase extends
        UseCase<GetGenresUseCase.RequestValue, GetGenresUseCase.ResponseValue> {
    private DataSource dataSource;

    public GetGenresUseCase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void execute(final RequestValue requestValue) {
        dataSource.getListOfGenres(new DataSource.LoadGenreCallBack() {
            @Override
            public String getLanguage() {
                return "";
            }

            @Override
            public void onDataLoaded(SparseArray<String> genreMap) {

                Log.d(GetGenresUseCase.class.getSimpleName(), requestValue.getGenreIds().toString());

                List<String> genres = new ArrayList<>();
                for(int id : requestValue.getGenreIds()) {
                    genres.add(genreMap.get(id));
                }
                getCallBack().onSuccess(new ResponseValue(genres));
            }

            @Override
            public void onDataNotAvailable(String error) {
                getCallBack().onError(error);
            }
        });
    }

    public static class RequestValue implements UseCase.RequestValue {
        private final List<Integer> genreIds;

        public RequestValue(List<Integer> genreIds) {
            this.genreIds = genreIds;
        }

        public List<Integer> getGenreIds() {
            return genreIds;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {
        private final List<String> genres;

        public ResponseValue(List<String> genres) {
            this.genres = genres;
        }

        public List<String> getGenres() {
            return genres;
        }
    }
}
