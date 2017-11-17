package com.kireyko.movieland.entity;

import java.util.List;

public class Movie {
    private int id;
    private String nameRussian;
    private String nameNative;
    private int year;
    private Float rating;
    private Float price;

    private String description;
    private String poster;

    private List<Country> country;
    private List<Genre> genre;

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

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public List<Country> getCountry() {
        return this.country;
    }
    public void setCountry(List<Country> country) {
        this.country=country;
    }

    public List<Genre> getGenre() {
        return this.genre;
    }
    public void setGenre(List<Genre>  genre) {
        this.genre=genre;
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

    public String getPoster() {return poster; }
    public void setPoster(String poster) {this.poster = poster; }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", nameRussian='" + nameRussian + '\'' +
                ", nameNative='" + nameNative + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", poster='" + poster + '\'' +
                ", country=" + country +
                ", genre=" + genre +
                '}';
    }
}


