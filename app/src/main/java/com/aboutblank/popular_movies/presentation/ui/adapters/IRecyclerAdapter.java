package com.aboutblank.popular_movies.presentation.ui.adapters;

import android.support.annotation.NonNull;

import java.util.List;

public interface IRecyclerAdapter<T> {
    void update(@NonNull List<T> newList);
}
