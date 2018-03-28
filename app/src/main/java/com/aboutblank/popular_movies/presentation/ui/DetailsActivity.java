package com.aboutblank.popular_movies.presentation.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aboutblank.popular_movies.R;
import com.aboutblank.popular_movies.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailsActivity extends AppCompatActivity {
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);

        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Bundle bundle = getIntent().getBundleExtra(getString(R.string.bundle_movie));

        if (bundle != null) {

            description.setText(bundle.getString(getString(R.string.bundle_description)));
            title.setText(bundle.getString(getString(R.string.bundle_title)));
            date.setText(bundle.getString(getString(R.string.bundle_date)));
            votes.setText(bundle.getString(getString(R.string.bundle_votes)));

            ImageUtils.loadImageInto(this, poster, bundle.getString(getString(R.string.bundle_image)));
            ImageUtils.loadBackdropImageInto(this, backdrop, bundle.getString(getString(R.string.bundle_backdrop)));

            colorizeDetails();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void colorizeDetails() {
        Bitmap bitmap = ((BitmapDrawable) poster.getDrawable()).getBitmap();

        Palette p = Palette.from(bitmap).generate();

        int dominateColor = p.getDominantColor(getResources().getColor(R.color.primaryDarkColor));
        int darkVibrantColor = p.getDarkVibrantColor(getResources().getColor(R.color.primaryDarkColor));

        if(darkVibrantColor < dominateColor) {
            Log.d(DetailsActivity.class.getSimpleName(), "dominate color chosen");
            dominateColor = darkVibrantColor;
        }

        shader.setBackgroundColor(dominateColor);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(
                    new ColorDrawable(dominateColor));
        }
    }
}
