package com.kireyko.movieland.service.impl;

import com.kireyko.movieland.dao.MovieDao;
import com.kireyko.movieland.entity.Genre;
import com.kireyko.movieland.entity.Movie;
import com.kireyko.movieland.service.MovieService;
import org.springframework.stereotype.Service;
//import com.trubin.citystore.dao.CityDao;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieDao movieDao;

    @Override
    public List<Movie> getAll(Map<String, String> parameters) {
        return movieDao.getAll(parameters);
    }

    @Override
    public List<Movie> getMoviesRandom() {
        return movieDao.getMoviesRandom();
    }

    @Override
    public Movie getById(int id) {
        return movieDao.getById(id);
    }

    @Override
    public List<Movie> getMoviesByGenreId(int id) {
        return movieDao.getMoviesByGenreId(id);
    }
}