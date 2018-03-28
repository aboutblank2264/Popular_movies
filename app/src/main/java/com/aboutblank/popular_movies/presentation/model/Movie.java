package com.aboutblank.popular_movies.presentation.model;

public class Movie {
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
}
