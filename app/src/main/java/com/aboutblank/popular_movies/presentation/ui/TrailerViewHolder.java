package com.aboutblank.popular_movies.presentation.ui;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aboutblank.popular_movies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.trailer_text_view)
    TextView trailerTitle;

    private ItemClickedListener itemClickedListener;

    public TrailerViewHolder(View view, ItemClickedListener itemClickedListener) {
        super(view);
        ButterKnife.bind(this, view);

        this.itemClickedListener = itemClickedListener;

        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(itemClickedListener != null) {
            itemClickedListener.onClicked(getAdapterPosition());
        }
    }

    public void setTitle(@NonNull String title) {
        trailerTitle.setText(title);
    }

    interface ItemClickedListener {
        void onClicked(int position);
    }
}