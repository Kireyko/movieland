package com.kireyko.movieland.entity;

import java.util.List;

public class Movie {
    private int id;
    private String nameRussian;
    private String nameNative;
    private int yearOfRelease;
    private Float rating;
    private Float price;

    private String description;
    private String picturePath;

    private List<Country> countries;
    private List<Genre> genres;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNameRussian() {
        return nameRussian;
    }
    public void setNameRussian(String nameRussian) {
        this.nameRussian = nameRussian;
    }

    public String getNameNative() {
        return nameNative;
    }
    public void setNameNative(String nameNative) {
        this.nameNative = nameNative;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }
    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public List<Country> getCountries() {
        return this.countries;
    }
    public void setCountries(List<Country> countries) {
        this.countries=countries;
    }

    public List<Genre> getGenres() {
        return this.genres;
    }
    public void setGenres(List<Genre>  genres) {
        this.genres=genres;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Float getRating() {
        return rating;
    }
    public void setRaiting(Float rating) {
        this.rating = rating;
    }

    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPicturePath() {return picturePath; }
    public void setPicturePath(String picturePath) {this.picturePath = picturePath; }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", nameRussian='" + nameRussian + '\'' +
                ", nameNative='" + nameNative + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                ", rating=" + rating +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", country=" + countries +
                ", genre=" + genres +
                '}';
    }
}


