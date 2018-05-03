package com.aboutblank.popular_movies.data.domain;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(tableName = "genres",
        primaryKeys = {"id", "language"})
public class Genre {
    private int id;
    @NonNull
    private String language;
    private String name;

    public Genre(int id, @NonNull String language, String name) {
        this.id = id;
        this.language = language;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
