package com.aboutblank.popular_movies.presentation.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.aboutblank.popular_movies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.item_image) ImageView imageView;
    private ItemClickedListener itemClickedListener;

    public RecyclerViewHolder(View view, ItemClickedListener itemClickedListener) {
        super(view);
        ButterKnife.bind(this, view);

        this.itemClickedListener = itemClickedListener;

        view.setOnClickListener(this);
    }

    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public void onClick(View v) {
        if (itemClickedListener != null) {
            itemClickedListener.onItemClick(getAdapterPosition());
        }
    }

    public interface ItemClickedListener {
        void onItemClick(int position);
    }
}
