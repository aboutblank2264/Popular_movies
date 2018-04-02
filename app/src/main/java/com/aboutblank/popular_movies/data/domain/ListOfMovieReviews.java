package com.aboutblank.popular_movies.data.domain;

import com.aboutblank.popular_movies.presentation.model.MovieReview;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class  ListOfMovieReviews {
    private int id;
    private int page;
    @SerializedName("results")
    private List<MovieReview> reviews;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_reviews")
    private int totalReviews;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<MovieReview> reviews) {
        this.reviews = reviews;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }

    @Override
    public String toString() {
        return "ListOfMovieReviews{" +
                "id=" + id +
                ", page=" + page +
                ", reviews=" + reviews +
                ", totalPages=" + totalPages +
                ", totalReviews=" + totalReviews +
                '}';
    }
}
