package com.kireyko.movieland.service;

import com.kireyko.movieland.entity.Movie;

import java.util.List;

public interface MovieService {
    Movie getById(int id);
    List<Movie> getMoviesAll();
    List<Movie> getMoviesRandom();

    void add(Movie movie);
}