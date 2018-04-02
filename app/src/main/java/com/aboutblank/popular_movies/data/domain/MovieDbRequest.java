package com.aboutblank.popular_movies.data.domain;

import android.support.annotation.NonNull;

public class MovieDbRequest {
    private String id;
    private String language;
    private int page;
    private String region;

    public MovieDbRequest(@NonNull String id, @NonNull String language, int page, @NonNull String region) {
        if(page <= 0) {
            throw new IllegalArgumentException("Cannot have page be less than 1, given" + page);
        }

        this.id = id;
        this.language = language;
        this.page = page;
        this.region = region;
    }

    public MovieDbRequest() {
        this("", "", 1, "");
    }

    public MovieDbRequest(String id, String language, int page) {
        this(id, language, page, "");
    }

    public MovieDbRequest(String id, String language) {
        this(id, language, 1, "");
    }

    public MovieDbRequest(String id) {
        this(id, "", 1, "");
    }

    public String getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public int getPage() {
        return page;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return "MovieDbRequest{" +
                "id='" + id + '\'' +
                ", language='" + language + '\'' +
                ", page=" + page +
                ", region='" + region + '\'' +
                '}';
    }
}
