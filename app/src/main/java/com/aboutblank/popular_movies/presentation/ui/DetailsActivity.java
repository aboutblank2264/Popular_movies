package com.aboutblank.popular_movies.presentation.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aboutblank.popular_movies.MainActivity;
import com.aboutblank.popular_movies.R;
import com.aboutblank.popular_movies.UseCaseExecutor;
import com.aboutblank.popular_movies.data.DataRepository;
import com.aboutblank.popular_movies.presentation.DatabaseReader;
import com.aboutblank.popular_movies.presentation.DetailPresenter;
import com.aboutblank.popular_movies.presentation.implementation.DetailPresenterImpl;
import com.aboutblank.popular_movies.presentation.model.DataType;
import com.aboutblank.popular_movies.presentation.model.Movie;
import com.aboutblank.popular_movies.presentation.model.MovieReview;
import com.aboutblank.popular_movies.presentation.model.MovieVideo;
import com.aboutblank.popular_movies.presentation.ui.adapters.ReviewRecyclerAdapter;
import com.aboutblank.popular_movies.presentation.ui.adapters.VideoRecyclerAdapter;
import com.aboutblank.popular_movies.presentation.usecase.AddGetFavoriteUseCase;
import com.aboutblank.popular_movies.presentation.usecase.GetListOfDataUseCase;
import com.aboutblank.popular_movies.presentation.usecase.GetMovieDataUseCase;
import com.aboutblank.popular_movies.utils.ImageUtils;
import com.aboutblank.popular_movies.utils.MovieUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements DetailPresenter.View, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @BindView(R.id.detail_poster)
    ImageView poster;
    @BindView(R.id.detail_backdrop)
    ImageView backdrop;
    @BindView(R.id.detail_shader)
    View shader;
    @BindView(R.id.detail_overview)
    TextView description;
    @BindView(R.id.detail_title)
    TextView title;
    @BindView(R.id.detail_date)
    TextView date;
    @BindView(R.id.detail_votes)
    TextView votes;
    @BindView(R.id.detail_favorite)
    ImageButton favoriteButton;

    @BindDrawable(R.drawable.ic_star_white_36dp)
    Drawable solidStar;

    @BindDrawable(R.drawable.ic_star_border_white_36dp)
    Drawable borderedStar;

    @BindView(R.id.detail_recycler_videos)
    RecyclerView videoRecyclerView;
    VideoRecyclerAdapter videoRecyclerAdapter;

    @BindView(R.id.detail_recycler_reviews)
    RecyclerView reviewRecyclerView;
    ReviewRecyclerAdapter reviewRecyclerAdapter;

    private Movie movie;

    private boolean isSaved;

    private DetailPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);

        ButterKnife.bind(this);

        loadToolbarAndDrawer();

        movie = getIntent().getParcelableExtra(getString(R.string.bundle_movie));
        if (movie != null) {

            Log.d(DetailsActivity.class.getSimpleName(), movie.getOverview());

            description.setText(movie.getOverview());
            title.setText(movie.getTitle());
            date.setText(movie.getReleaseDate());
            votes.setText(MovieUtils.toPercentage(movie.getVote()));

            ImageUtils.loadImageInto(this, poster, movie.getPosterUrl());
            ImageUtils.loadBackdropImageInto(this, backdrop, movie.getBackdrop());

            colorizeActionbar();
        }

        DataRepository dataRepository = DataRepository.getInstance(new DatabaseReader(this));

        new DetailPresenterImpl(this,
                new GetListOfDataUseCase<MovieReview>(dataRepository, DataType.REVIEWS),
                new GetListOfDataUseCase<MovieVideo>(dataRepository, DataType.VIDEOS),
                new AddGetFavoriteUseCase(dataRepository),
                UseCaseExecutor.getInstance());

        presenter.start();

        setFavoriteOnClick();

        videoRecyclerAdapter = new VideoRecyclerAdapter(getLayoutInflater(), new ArrayList<MovieVideo>());
        videoRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        videoRecyclerView.setAdapter(videoRecyclerAdapter);
        videoRecyclerView.setHasFixedSize(true);

        reviewRecyclerView.addItemDecoration(ReviewRecyclerAdapter.getItemDecoration(this));
        reviewRecyclerAdapter = new ReviewRecyclerAdapter(getLayoutInflater(), new ArrayList<MovieReview>());
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        reviewRecyclerView.setAdapter(reviewRecyclerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(@NonNull DetailPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Movie getMovie() {
        return movie;
    }

    private void colorizeActionbar() {
        Bitmap bitmap = ((BitmapDrawable) poster.getDrawable()).getBitmap();

        Palette p = Palette.from(bitmap).generate();

        int dominateColor = p.getDominantColor(getResources().getColor(R.color.primaryDarkColor));
        int darkVibrantColor = p.getDarkVibrantColor(getResources().getColor(R.color.primaryDarkColor));
        int lightMutedColor = p.getVibrantColor(getResources().getColor(R.color.primaryLightColor));

        if (darkVibrantColor < dominateColor) {
            Log.d(DetailsActivity.class.getSimpleName(), "dominate color chosen");
            dominateColor = darkVibrantColor;
        }

        shader.setBackgroundColor(dominateColor);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(
                    new ColorDrawable(dominateColor));
        }

        favoriteButton.getBackground().setColorFilter(lightMutedColor, PorterDuff.Mode.MULTIPLY);
    }

    @Override
    public void showReviews(List<MovieReview> reviews) {
        Log.d(DetailsActivity.class.getSimpleName(), "Reviews: " + reviews);

        reviewRecyclerAdapter.update(reviews);
    }

    @Override
    public void showVideos(List<MovieVideo> videos) {
        Log.d(DetailsActivity.class.getSimpleName(), "Videos: " + videos);

        videoRecyclerAdapter.update(videos);
    }

    @Override
    public void showFavorited(boolean isSaved) {
        Log.d(DetailsActivity.class.getSimpleName(), "Saved: " + isSaved);

        this.isSaved = isSaved;

        if (isSaved) {
            favoriteButton.setImageDrawable(solidStar);
        } else {
            favoriteButton.setImageDrawable(borderedStar);
        }
    }

    @Override
    public void showProgress(boolean active) {
        //Do nothing
    }

    @Override
    public void showError(@NonNull String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    /**
     * set on click for favoriteButton button
     */
    public void setFavoriteOnClick() {
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean tSaved = !isSaved;
                presenter.toggleMovieFavorite(movie.getId(), tSaved);
                showFavorited(tSaved);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);

        drawerLayout.closeDrawers();

        Intent intent = new Intent(this, MainActivity.class);

        String listType = "";

        switch (item.getItemId()) {
            case R.id.menu_popular:
                listType = GetMovieDataUseCase.ListType.POPULAR.name();
                break;
            case R.id.menu_highest_rated:
                listType = GetMovieDataUseCase.ListType.HIGHEST_RATED.name();
                break;
            case R.id.menu_favorited:
                listType = GetMovieDataUseCase.ListType.FAVORITED.name();
                break;
        }

        intent.putExtra(getString(R.string.bundle_list_type), listType);

        startActivity(intent);


        return false;
    }

    private void loadToolbarAndDrawer() {
        drawerLayout = findViewById(R.id.detail_drawer_layout);

//        Toolbar toolbar = findViewById(R.id.detail_toolbar);
//        setSupportActionBar(toolbar);

//        ActionBar actionbar = getSupportActionBar();
//
//        if (actionbar != null) {
//            actionbar.setDisplayHomeAsUpEnabled(true);
//            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
//        }

        NavigationView navigationView = findViewById(R.id.detail_drawer_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}
