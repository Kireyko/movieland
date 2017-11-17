package com.kireyko.movieland.dao;

import com.kireyko.movieland.entity.Genre;
import com.kireyko.movieland.entity.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> getMoviesAll();
    List<Movie> getMoviesRandom();
    Movie getById(int id);
    List<Genre> getGenresAll();
    List<Movie> getMoviesByGenreId(int id);
}