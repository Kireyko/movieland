package com.kireyko.movieland.service;

import com.kireyko.movieland.entity.Genre;
import com.kireyko.movieland.entity.Movie;

import java.util.List;
import java.util.Map;

public interface MovieService {

    List<Movie> getMoviesAll(Map<String, String> parameters);
    List<Movie> getMoviesRandom();
    Movie getById(int id);

    List<Genre> getGenresAll();
    List<Movie> getMoviesByGenreId(int id);
}