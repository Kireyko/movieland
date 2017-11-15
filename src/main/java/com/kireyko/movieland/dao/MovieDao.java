package com.kireyko.movieland.dao;

import com.kireyko.movieland.entity.Movie;

import java.util.List;

public interface MovieDao {
    Movie getById(int id);
    List<Movie> getMoviesAll();
    List<Movie> getMoviesRandom();
    void add(Movie movie);
}