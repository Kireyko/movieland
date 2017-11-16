package com.kireyko.movieland.entity;

public class MovieWithGenre {
    private int movieId;
    private int genreId;
    private String genreName;

    public int getMovieId() {return movieId;}
    public void setMovieId(int movieId) {this.movieId = movieId;}

    public int getGenreId() {return genreId;}
    public void setGenreId(int genreId) {this.genreId = genreId;}

    public String getGenreName() {return genreName;}
    public void setGenreName(String genreName) {this.genreName = genreName;}

    @Override
    public String toString() {
        return "MovieWithGenre{" +
                "movieId=" + movieId +
                ", genreId=" + genreId +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}
