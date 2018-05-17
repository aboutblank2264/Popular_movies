package com.aboutblank.popular_movies.presentation.ui;


import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.aboutblank.popular_movies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoViewHolder extends AbstractViewHolder {

    @BindView(R.id.trailer_text_view)
    TextView trailerTitle;

    public VideoViewHolder(View view, ItemClickedListener itemClickedListener) {
        super(view, itemClickedListener);
        ButterKnife.bind(this, view);
    }

    public void setTitle(@NonNull String title) {
        trailerTitle.setText(title);
    }
}