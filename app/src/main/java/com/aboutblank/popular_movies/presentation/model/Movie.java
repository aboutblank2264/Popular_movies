package com.aboutblank.popular_movies.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Movie implements Parcelable {
    private String id;
    private String title;
    private String posterUrl;
    private String backdrop;
    private String releaseDate;
    private String overview;
    private double vote;
    private List<Integer> genres;

    public Movie(String id, String title, String posterUrl, String backdrop, String releaseDate,
                 String overview, double vote, List<Integer> genres) {
        this.id = id;
        this.title = title;
        this.posterUrl = posterUrl;
        this.backdrop = backdrop;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.vote = vote;
        this.genres = genres;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public double getVote() {
        return vote;
    }

    public List<Integer> getGenres() {
        return genres;
    }

    //NOTE: ORDER MUST BE THE SAME AS writeToParcel
    private Movie(Parcel in) {
        id = in.readString();
        posterUrl = in.readString();
        title = in.readString();
        releaseDate = in.readString();
        overview = in.readString();
        vote = in.readDouble();
        backdrop = in.readString();
        genres = new ArrayList<>();
        in.readList(genres, null);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(getId());
        out.writeString(getPosterUrl());
        out.writeString(getTitle());
        out.writeString(getReleaseDate());
        out.writeString(getOverview());
        out.writeDouble(getVote());
        out.writeString(getBackdrop());
        out.writeList(getGenres());
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", backdrop='" + backdrop + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", overview='" + overview + '\'' +
                ", vote=" + vote +
                ", genres=" + genres +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.vote, vote) == 0 &&
                Objects.equals(id, movie.id) &&
                Objects.equals(title, movie.title) &&
                Objects.equals(posterUrl, movie.posterUrl) &&
                Objects.equals(backdrop, movie.backdrop) &&
                Objects.equals(releaseDate, movie.releaseDate) &&
                Objects.equals(overview, movie.overview) &&
                Objects.equals(genres, movie.genres);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
