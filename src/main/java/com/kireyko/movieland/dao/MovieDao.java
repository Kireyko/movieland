package com.kireyko.movieland.dao;

import com.kireyko.movieland.entity.Genre;
import com.kireyko.movieland.entity.Movie;

import java.util.List;
import java.util.Map;

public interface MovieDao {
    List<Movie> getMoviesAll(Map<String, String> parameters);
    List<Movie> getMoviesRandom();
    Movie getById(int id);
    List<Genre> getGenresAll();
    List<Movie> getMoviesByGenreId(int id);
}