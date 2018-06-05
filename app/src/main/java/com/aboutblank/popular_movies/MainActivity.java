package com.aboutblank.popular_movies;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aboutblank.popular_movies.data.DataRepository;
import com.aboutblank.popular_movies.presentation.DatabaseReader;
import com.aboutblank.popular_movies.presentation.MainPresenter;
import com.aboutblank.popular_movies.presentation.implementation.MainPresenterImpl;
import com.aboutblank.popular_movies.presentation.model.Movie;
import com.aboutblank.popular_movies.presentation.ui.adapters.MovieRecyclerAdapter;
import com.aboutblank.popular_movies.presentation.usecase.GetMovieDataUseCase;

import java.util.List;

/**
 * MVP and Clean architecture reference:
 * https://github.com/googlesamples/android-architecture/tree/todo-mvp-clean/
 *
 * Navigation Drawer
 * https://developer.android.com/training/implementing-navigation/nav-drawer
 */
public class MainActivity extends AppCompatActivity implements MainPresenter.View, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private Parcelable currentViewPosition;

    private MainPresenter presenter;
    private MovieRecyclerAdapter movieRecyclerAdapter;

    private GetMovieDataUseCase.ListType listType = GetMovieDataUseCase.ListType.POPULAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Show loading screen
        showProgress(true);

        //Set RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        if (savedInstanceState != null) {
            Log.d("savedInstance", savedInstanceState.getString(getString(R.string.bundle_list_type)));

            listType = GetMovieDataUseCase.ListType.valueOf(savedInstanceState.getString(getString(R.string.bundle_list_type)));
        } else if(getIntent().hasExtra(getString(R.string.bundle_list_type))) {
            Log.d("getIntent", getIntent().getStringExtra(getString(R.string.bundle_list_type)));

            listType = GetMovieDataUseCase.ListType.valueOf(getIntent().getStringExtra(getString(R.string.bundle_list_type)));
        }

        loadToolbarAndDrawer();

        new MainPresenterImpl(this,
                new GetMovieDataUseCase(DataRepository.getInstance(new DatabaseReader(this))),
                UseCaseExecutor.getInstance());

        movieRecyclerAdapter = new MovieRecyclerAdapter(getLayoutInflater());

        recyclerView.setAdapter(movieRecyclerAdapter);

        presenter.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getString(R.string.bundle_list_type), listType.name());

        outState.putParcelable(getString(R.string.bundle_main_save_position), recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        currentViewPosition = savedInstanceState.getParcelable(getString(R.string.bundle_main_save_position));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);

        drawerLayout.closeDrawers();

        GetMovieDataUseCase.ListType selectedType = listType;

        switch (item.getItemId()) {
            case R.id.menu_popular:
                selectedType = GetMovieDataUseCase.ListType.POPULAR;
                break;
            case R.id.menu_highest_rated:
                selectedType = GetMovieDataUseCase.ListType.HIGHEST_RATED;
                break;
            case R.id.menu_favorited:
                selectedType = GetMovieDataUseCase.ListType.FAVORITED;
                break;
        }

        changeMovieType(selectedType);

        return true;
    }

    @Override
    public void setPresenter(@NonNull MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showProgress(boolean active) {
        progressBar = findViewById(R.id.progressBar);
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
        movieRecyclerAdapter.update(movies);

        movieRecyclerAdapter.notifyDataSetChanged();
        restoreViewPosition();
    }

    @Override
    public GetMovieDataUseCase.ListType showMovieType() {
        return listType;
    }

    private void changeMovieType(@NonNull GetMovieDataUseCase.ListType listType) {
        if (listType != this.listType) {
            Log.d(MainActivity.class.getSimpleName(), "Changing movie list " + listType.name());

            this.listType = listType;
            presenter.start();
        }
    }

    private void loadToolbarAndDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();

        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        NavigationView navigationView = findViewById(R.id.drawer_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void restoreViewPosition() {
        if(currentViewPosition != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(currentViewPosition);
        }
    }
}
