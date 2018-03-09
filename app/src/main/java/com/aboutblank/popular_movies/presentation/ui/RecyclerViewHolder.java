package com.aboutblank.popular_movies.presentation.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.aboutblank.popular_movies.R;


public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView imageView;

    public RecyclerViewHolder(View view) {
        super(view);

        imageView = view.findViewById(R.id.item_image);
        imageView.setOnClickListener(this);
    }

    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public void onClick(View v) {
        //TODO Expand
    }
}
