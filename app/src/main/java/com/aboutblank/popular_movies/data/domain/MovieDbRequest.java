package com.aboutblank.popular_movies.data.domain;

public class MovieDbRequest {
    private String language = "";
    private int page = 1;
    private String region = "";

    public MovieDbRequest() {
    }

    public MovieDbRequest(String language, int page, String region) {
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
