package com.aboutblank.popular_movies.data.domain;

import android.support.annotation.NonNull;

public class MovieDbRequest {
    private String language;
    private int page;
    private String region;

    public MovieDbRequest() {
        this("", 1, "");
    }

    public MovieDbRequest(@NonNull String language, int page, @NonNull String region) {
        if(page <= 0) {
            throw new IllegalArgumentException("Cannot have page be less than 1, given" + page);
        }

        this.language = language;
        this.page = page;
        this.region = region;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "MovieDbRequest{" +
                "language='" + language + '\'' +
                ", page=" + page +
                ", region='" + region + '\'' +
                '}';
    }
}
