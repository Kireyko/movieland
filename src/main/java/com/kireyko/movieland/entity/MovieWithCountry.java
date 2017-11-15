package com.kireyko.movieland.entity;


public class MovieWithCountry {
    private int movieId;
    private int countryId;
    private String countryName;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return countryName;
    }

    public void setCountry(String country) {
        this.countryName = country;
    }

    @Override
    public String toString() {
        return "MovieToCountry{" +
                "movieId=" + movieId +
                ", countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}