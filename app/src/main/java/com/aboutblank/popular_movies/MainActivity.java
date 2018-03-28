package com.aboutblank.popular_movies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aboutblank.popular_movies.data.DataRepository;
import com.aboutblank.popular_movies.presentation.MainPresenter;
import com.aboutblank.popular_movies.presentation.MainPresenterImpl;
import com.aboutblank.popular_movies.presentation.model.Movie;
import com.aboutblank.popular_movies.presentation.ui.RecyclerViewAdapter;
import com.aboutblank.popular_movies.presentation.usecase.GetMovieData;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    private MainPresenter presenter;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ProgressBar progressBar;

    private GetMovieData.ListType listType = GetMovieData.ListType.POPULAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        recyclerViewAdapter = new RecyclerViewAdapter(getLayoutInflater());

        recyclerView.setAdapter(recyclerViewAdapter);

        new MainPresenterImpl(this, DataRepository.getInstance(), UseCaseExecutor.getInstance());

        presenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        GetMovieData.ListType selectedType;

        switch (item.getItemId()) {
            case R.id.menu_popular:
                selectedType = GetMovieData.ListType.POPULAR;
                break;
            case R.id.menu_highest_rated:
                selectedType = GetMovieData.ListType.HIGHEST_RATED;
                break;
            default:
                selectedType = listType;
                break;
        }
        changeMovieType(selectedType);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(@NonNull MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgress(boolean active) {
        if (active) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(@NonNull String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMovies(@NonNull List<Movie> movies) {
        recyclerViewAdapter.update(movies);

        Log.d(MainActivity.class.getSimpleName(), "what thread:" + Thread.currentThread().getName());
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void changeMovieType(@NonNull GetMovieData.ListType listType) {
        Log.d(MainActivity.class.getSimpleName(), "Changing movie list " + listType.name());
        if (listType != this.listType) {
            this.listType = listType;
        }
        presenter.start();
    }

    @Override
    public GetMovieData.ListType showMovieType() {
        return listType;
    }
}
