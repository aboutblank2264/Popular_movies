package com.aboutblank.popular_movies.presentation.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aboutblank.popular_movies.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenreRecyclerAdapter extends RecyclerView.Adapter<GenreRecyclerAdapter.GenreRecyclerViewHolder>
        implements AbstractRecyclerAdapter<String> {

    private final LayoutInflater inflater;
    private final List<String> genres;

    public GenreRecyclerAdapter(@NonNull LayoutInflater inflater, @NonNull List<String> genres) {
        this.inflater = inflater;
        this.genres = genres;
    }

    @Override
    public GenreRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.genre_view_item, parent, false);
        return new GenreRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GenreRecyclerViewHolder holder, int position) {
        holder.setText(genres.get(position));
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    @Override
    public void update(@NonNull List<String> newList) {
        Log.d(GenreRecyclerAdapter.class.getSimpleName(), "Refreshing genres:");

        genres.clear();
        genres.addAll(newList);

        Log.d(GenreRecyclerAdapter.class.getSimpleName(), genres.toString());

        notifyDataSetChanged();
    }

    class GenreRecyclerViewHolder extends AbstractViewHolder {
        @BindView(R.id.genre_item_textView)
        TextView textItem;

        public GenreRecyclerViewHolder(View view) {
            super(view, null);
            ButterKnife.bind(this, view);
        }

        public void setText(@NonNull String text) {
            textItem.setText(text);
        }
    }
}
