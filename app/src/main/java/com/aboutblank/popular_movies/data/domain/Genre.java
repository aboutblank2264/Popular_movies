package com.aboutblank.popular_movies.data.domain;

import java.util.List;

public class Genre {
    private int id;
    private String name;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public class ListOfGenres {
        private List<Genre> genres;

        public ListOfGenres(List<Genre> genres) {
            this.genres = genres;
        }

        public List<Genre> getGenres() {
            return genres;
        }

        @Override
        public String toString() {
            return "ListOfGenres{" +
                    "genres=" + genres +
                    '}';
        }
    }
}
