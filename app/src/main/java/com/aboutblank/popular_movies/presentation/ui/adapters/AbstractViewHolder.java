package com.aboutblank.popular_movies.presentation.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class AbstractViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ItemClickedListener itemClickedListener;

    public AbstractViewHolder(View view, ItemClickedListener itemClickedListener) {
        super(view);

        this.itemClickedListener = itemClickedListener;

        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(itemClickedListener != null) {
            itemClickedListener.onClicked(getAdapterPosition());
        }
    }

    interface ItemClickedListener {
        void onClicked(int position);
    }
}
