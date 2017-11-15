package com.kireyko.movieland.service.impl;

import com.kireyko.movieland.dao.MovieDao;
import com.kireyko.movieland.entity.Movie;
import com.kireyko.movieland.service.MovieService;
import org.springframework.stereotype.Service;
//import com.trubin.citystore.dao.CityDao;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class MovieServiceImpl  implements MovieService{
    @Autowired
    private MovieDao movieDao;

    @Override
    public List<Movie> getMoviesAll() {
        return movieDao.getMoviesAll();
    }

    @Override
    public List<Movie> getMoviesRandom() {
        return movieDao.getMoviesRandom();
    }

    //    @Override
    //    public Movie getById(int id) {
    //        return movieDao.getById(id);
    //    }





}

