package com.aboutblank.popular_movies.presentation.usecase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.aboutblank.popular_movies.UseCase;
import com.aboutblank.popular_movies.utils.ImageUtils;

public class LoadImageUseCase extends UseCase<LoadImageUseCase.RequestValue, LoadImageUseCase.ResponseValue> {

    @Override
    public void execute(RequestValue requestValue) {
        ImageUtils.loadImageInto(requestValue.context, requestValue.view, requestValue.imageUrl);
    }

    public static class RequestValue implements UseCase.RequestValue {
            private String imageUrl;
            private Context context;
            private ImageView view;

        public RequestValue(@NonNull String imageUrl, @NonNull Context context, @NonNull ImageView view) {
            this.imageUrl = imageUrl;
            this.context = context;
            this.view = view;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {

    }
}
