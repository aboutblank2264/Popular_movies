package com.aboutblank.popular_movies.presentation.usecase;

import com.aboutblank.popular_movies.UseCase;
import com.aboutblank.popular_movies.data.domain.MovieDbRequest;
import com.aboutblank.popular_movies.presentation.model.MovieVideo;

import java.util.List;

public class GetMovieVideosUseCase extends
        UseCase<GetMovieVideosUseCase.RequestValue, GetMovieVideosUseCase.ResponseValue> {


    @Override
    public void execute(RequestValue requestValue) {

    }

    public static class RequestValue implements UseCase.RequestValue {
        private final MovieDbRequest dbRequest;

        public RequestValue(MovieDbRequest dbRequest) {
            this.dbRequest = dbRequest;
        }

        public MovieDbRequest getDbRequest() {
            return dbRequest;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {
        private List<MovieVideo> movieVideos;

        public ResponseValue(List<MovieVideo> movieVideos) {
            this.movieVideos = movieVideos;
        }

        public List<MovieVideo> getMovieVideos() {
            return movieVideos;
        }
    }
}
