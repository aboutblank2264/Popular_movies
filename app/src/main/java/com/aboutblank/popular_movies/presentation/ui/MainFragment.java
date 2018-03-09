package com.aboutblank.popular_movies.presentation.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aboutblank.popular_movies.R;
import com.aboutblank.popular_movies.presentation.MainPresenter;
import com.aboutblank.popular_movies.presentation.model.Movie;
import com.aboutblank.popular_movies.presentation.usecase.GetMovieData;

import java.util.Collections;
import java.util.List;


public class MainFragment extends Fragment implements MainPresenter.View {
    private MainPresenter presenter;
    private RecyclerViewAdapter recyclerViewAdapter;

    private GetMovieData.ListType listType = GetMovieData.ListType.POPULAR;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.main_frag, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); //TODO test number of columns

        recyclerViewAdapter = new RecyclerViewAdapter(getLayoutInflater(),
                Collections.<Movie>emptyList());

        recyclerView.setAdapter(recyclerViewAdapter);

        presenter.start();

        return root;
    }

    @Override
    public void setPresenter(@NonNull MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgress(boolean active) {
        //TODO
    }

    @Override
    public void showError(@NonNull String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMovies(@NonNull List<Movie> movies) {
        recyclerViewAdapter.update(movies);
    }

    @Override
    public void chanceMovieType(@NonNull GetMovieData.ListType listType) {
        this.listType = listType;
    }

    @Override
    public GetMovieData.ListType showMovieType() {
        return listType;
    }
}
