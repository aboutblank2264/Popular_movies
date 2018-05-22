package com.aboutblank.popular_movies.presentation.ui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aboutblank.popular_movies.R;
import com.aboutblank.popular_movies.presentation.model.MovieReview;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewRecyclerAdapter extends RecyclerView.Adapter<ReviewRecyclerAdapter.ReviewViewHolder>
        implements ReviewRecyclerAdapter.ReviewViewHolder.ItemClickedListener, AbstractRecyclerAdapter<MovieReview>  {

    private List<MovieReview> movieReviewList;
    private LayoutInflater inflater;

    public ReviewRecyclerAdapter(@NonNull LayoutInflater inflater, @NonNull List<MovieReview> movieReviewList) {
        this.movieReviewList = movieReviewList;
        this.inflater = inflater;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.review_view_item, parent, false);

        return new ReviewViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        MovieReview review = movieReviewList.get(position);

        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
        SpannableString string = new SpannableString(review.getAuthor() + "\n" + review.getContent());
        string.setSpan(styleSpan, 0, review.getAuthor().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        holder.setReviewText(string);
    }

    @Override
    public int getItemCount() {
        return movieReviewList.size();
    }

    @Override
    public void onClicked(int position) {
//        MovieUtils.startWebIntent(inflater.getContext(), movieReviewList.get(position).getUrl());
    }

    @Override
    public void update(@NonNull List<MovieReview> newList) {
        Log.d(ReviewRecyclerAdapter.class.getSimpleName(), "Refreshing reviews:");

        movieReviewList.clear();
        movieReviewList.addAll(newList);

        Log.d(ReviewRecyclerAdapter.class.getSimpleName(), movieReviewList.toString());

        notifyDataSetChanged();
    }


    public static RecyclerView.ItemDecoration getItemDecoration(@NonNull Context context) {

        return new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
    }

    class ReviewViewHolder extends AbstractViewHolder {
        @BindView(R.id.review_text)
        TextView reviewText;

        ReviewViewHolder(View view, ItemClickedListener itemClickedListener) {
            super(view, itemClickedListener);

            ButterKnife.bind(this, view);
        }

        void setReviewText(@NonNull SpannableString text) {
            reviewText.setText(text);
        }
    }
}
