package com.aboutblank.popular_movies.presentation.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

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
import com.aboutblank.popular_movies.presentation.usecase.AddGetFavoriteUseCase;
import com.aboutblank.popular_movies.presentation.usecase.GetGenresUseCase;
import com.aboutblank.popular_movies.presentation.usecase.GetListOfDataUseCase;
import com.aboutblank.popular_movies.utils.ImageUtils;
import com.aboutblank.popular_movies.utils.MovieUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements DetailPresenter.View {
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
    ToggleButton favorite;

    @BindView(R.id.detail_recycler_genres)
    RecyclerView genreRecyclerView;

    @BindView(R.id.detail_recycler_videos)
    RecyclerView trailerRecyclerView;

    @BindView(R.id.detail_recycler_reviews)
    RecyclerView reviewRecyclerView;

    private Movie movie;

    private DetailPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);

        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

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
                new GetGenresUseCase(dataRepository),
                new AddGetFavoriteUseCase(dataRepository),
                UseCaseExecutor.getInstance());

        presenter.start();

        setFavoritOnClick();

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);

        genreRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        trailerRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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

        if (darkVibrantColor < dominateColor) {
            Log.d(DetailsActivity.class.getSimpleName(), "dominate color chosen");
            dominateColor = darkVibrantColor;
        }

        shader.setBackgroundColor(dominateColor);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(
                    new ColorDrawable(dominateColor));
        }
    }

    @Override
    public void showGenres(List<String> genres) {
        Log.d(DetailsActivity.class.getSimpleName(), "Genres: " + genres);

        genreRecyclerView.setAdapter(new GenreRecyclerAdapter(getLayoutInflater(), genres));
    }

    @Override
    public void showReviews(List<MovieReview> reviews) {
        Log.d(DetailsActivity.class.getSimpleName(), "Reviews: " + reviews);


    }

    @Override
    public void showVideos(List<MovieVideo> videos) {
        Log.d(DetailsActivity.class.getSimpleName(), "Videos: " + videos);

        trailerRecyclerView.setAdapter(new TrailerRecyclerAdapter(getLayoutInflater(), videos));
    }

    @Override
    public void showFavorite(boolean isFavorite) {
        Log.d(DetailsActivity.class.getSimpleName(), "Favorite: " + isFavorite);

        favorite.setChecked(isFavorite);
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
     * set on click for favorite button
     */
    public void setFavoritOnClick() {
        favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.toggleMovieFavorite(movie.getId(), isChecked);

            }
        });
    }
}
