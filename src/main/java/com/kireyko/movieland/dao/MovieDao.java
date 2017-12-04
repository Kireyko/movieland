package com.kireyko.movieland.dao;

import com.kireyko.movieland.entity.Movie;
import java.util.List;
import java.util.Map;

public interface MovieDao {
    List<Movie> getAll(Map<String, String> parameters);
    List<Movie> getMoviesRandom();
    Movie getById(int id);
    List<Movie> getMoviesByGenreId(int id);
}