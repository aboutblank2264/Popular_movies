package com.aboutblank.popular_movies.data.domain;

import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Serving double duty with local db Room entity.
 *
 * Example MovieItem JSON
 *
 {
     "vote_count": 125,
     "pageId": 441614,
     "video": false,
     "vote_average": 4.7,
     "title": "Loving",
     "popularity": 605.444204,
     "poster_path": "/6uOMVZ6oG00xjq0KQiExRBw2s3P.jpg",
     "original_language": "es",
     "original_title": "Amar",
     "genre_ids": [
     10749
     ],
     "backdrop_path": "/iBM6zvlOmcfodGhUa36sy7pM2Er.jpg",
     "adult": false,
     "overview": "Laura and Carlos love each other as if every day was the last, and perhaps that first love intensity is what will tear them apart a year later.",
     "release_date": "2017-04-21"
 }
 */
public class MovieItem {

    @PrimaryKey
    private int id;
    @SerializedName("vote_count")
    private int voteCount;
    private boolean video;
    @SerializedName("vote_average")
    private double voteAverage;
    private String title;
    private double popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("genre_ids")
    private List<Integer> genreIds;
    @SerializedName("backdrop_path")
    private String backdropPath;
    private boolean adult;
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "MovieItem{" +
                "voteCount=" + voteCount +
                ", pageId=" + id +
                ", video=" + video +
                ", voteAverage=" + voteAverage +
                ", title='" + title + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", genreIds=" + genreIds +
                ", backdropPath='" + backdropPath + '\'' +
                ", adult=" + adult +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
