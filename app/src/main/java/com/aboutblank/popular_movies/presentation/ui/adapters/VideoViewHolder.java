package com.aboutblank.popular_movies.presentation.ui.adapters;

import android.view.View;
import android.widget.ImageView;

import com.aboutblank.popular_movies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoViewHolder extends AbstractViewHolder {

    @BindView(R.id.trailer_image_view)
    ImageView trailerImage;

    public VideoViewHolder(View view, ItemClickedListener itemClickedListener) {
        super(view, itemClickedListener);
        ButterKnife.bind(this, view);
    }

    public ImageView getTrailerImageView() {
        return trailerImage;
    }
}