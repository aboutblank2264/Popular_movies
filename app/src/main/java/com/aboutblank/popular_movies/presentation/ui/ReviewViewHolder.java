package com.aboutblank.popular_movies.presentation.ui;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.aboutblank.popular_movies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewViewHolder extends AbstractViewHolder {
    @BindView(R.id.review_text)
    TextView reviewText;

    public ReviewViewHolder(View view, ItemClickedListener itemClickedListener) {
        super(view, itemClickedListener);

        ButterKnife.bind(this, view);
    }

    public void setReviewText(@NonNull String text) {
        reviewText.setText(text);
    }
}
