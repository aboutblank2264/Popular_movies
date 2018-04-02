package com.aboutblank.popular_movies.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String title;
    private String posterUrl;
    private String backdrop;
    private String releaseDate;
    private String overview;
    private double vote;

    public Movie(String title, String posterUrl, String backdrop, String releaseDate,
                 String overview, double vote) {
        this.title = title;
        this.posterUrl = posterUrl;
        this.backdrop = backdrop;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.vote = vote;
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

    //NOTE: ORDER MUST BE THE SAME AS writeToParcel
    private Movie(Parcel in) {
        posterUrl = in.readString();
        title = in.readString();
        releaseDate = in.readString();
        overview = in.readString();
        vote = in.readDouble();
        backdrop = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(getPosterUrl());
        out.writeString(getTitle());
        out.writeString(getReleaseDate());
        out.writeString(getOverview());
        out.writeDouble(getVote());
        out.writeString(getBackdrop());
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", backdrop='" + backdrop + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", overview='" + overview + '\'' +
                ", vote=" + vote +
                '}';
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
