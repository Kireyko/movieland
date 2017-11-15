package com.kireyko.movieland.entity;

import java.util.List;

public class Movie {
    private int id;
    private String moviename;
    private String movienameorig;
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

    public String getMoviename() {
        return moviename;
    }
    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getMovienameorig() {
        return movienameorig;
    }
    public void setMovienameorig(String movienameorig) {
        this.movienameorig = movienameorig;
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
                ", moviename='" + moviename + '\'' +
                ", movienameorig='" + movienameorig + '\'' +
                ", year=" + year +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                '}';
    }


}


