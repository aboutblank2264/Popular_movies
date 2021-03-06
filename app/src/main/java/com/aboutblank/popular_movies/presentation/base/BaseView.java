package com.aboutblank.popular_movies.presentation.base;

import android.support.annotation.NonNull;

public interface BaseView<T extends BasePresenter> {

    void showProgress(boolean active);

    void showError(@NonNull String error);

    void setPresenter(@NonNull T presenter);
}
