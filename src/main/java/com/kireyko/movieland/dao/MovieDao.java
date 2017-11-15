package com.kireyko.movieland.dao;

import com.kireyko.movieland.entity.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> getMoviesAll();
    List<Movie> getMoviesRandom();
    // Movie getById(int id);
}