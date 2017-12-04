package com.kireyko.movieland.service;

import com.kireyko.movieland.entity.Movie;
import java.util.List;
import java.util.Map;

public interface MovieService {
    List<Movie> getAll(Map<String, String> parameters);
    List<Movie> getMoviesRandom();
    Movie getById(int id);
    List<Movie> getMoviesByGenreId(int id);
}