package com.aboutblank.popular_movies.presentation.ui.adapters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aboutblank.popular_movies.R;
import com.aboutblank.popular_movies.presentation.model.MovieReview;

import java.util.List;

public class ReviewRecyclerAdapter extends RecyclerView.Adapter<ReviewViewHolder>
        implements ReviewViewHolder.ItemClickedListener, IRecyclerAdapter<MovieReview> {

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
        return new ReviewDividerDeco(context);
    }

    private static class ReviewDividerDeco extends RecyclerView.ItemDecoration {
        protected Drawable divider;

        public ReviewDividerDeco(@NonNull Context context) {
            divider = ContextCompat.getDrawable(context, R.drawable.line_drawable);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + divider.getIntrinsicHeight();

                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }
}
