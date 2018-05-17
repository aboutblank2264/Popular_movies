package com.aboutblank.popular_movies.presentation.ui;

import android.view.View;
import android.widget.ImageView;

import com.aboutblank.popular_movies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieViewHolder extends AbstractViewHolder {
    @BindView(R.id.item_image) ImageView imageView;

    public MovieViewHolder(View view, ItemClickedListener itemClickedListener) {
        super(view, itemClickedListener);
        ButterKnife.bind(this, view);
    }


    public ImageView getImageView() {
        return imageView;
    }
}
