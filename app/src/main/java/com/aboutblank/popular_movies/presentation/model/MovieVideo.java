package com.aboutblank.popular_movies.presentation.model;

import com.google.gson.annotations.SerializedName;

public class MovieVideo {
    private String id;
    @SerializedName("iso_639_1")
    private String iso639;
    @SerializedName("iso_3166_1")
    private String iso3166;
    private String key;
    private String name;
    private String site;
    private int size;
    private VideoType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIso639() {
        return iso639;
    }

    public void setIso639(String iso639) {
        this.iso639 = iso639;
    }

    public String getIso3166() {
        return iso3166;
    }

    public void setIso3166(String iso3166) {
        this.iso3166 = iso3166;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public VideoType getType() {
        return type;
    }

    public void setType(VideoType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MovieVideo{" +
                "id='" + id + '\'' +
                ", iso639='" + iso639 + '\'' +
                ", iso3166='" + iso3166 + '\'' +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", site='" + site + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                '}';
    }

    public enum VideoType {Trailer, Teaser, Clip, Featurette}
}
