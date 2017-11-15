package com.kireyko.movieland.service;

import com.kireyko.movieland.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getMoviesAll();
    List<Movie> getMoviesRandom();
//
//    Movie getById(int id);
}