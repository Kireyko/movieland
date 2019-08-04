package com.kireyko.movieland.service.impl;

import com.kireyko.movieland.dao.GenreDao;
import com.kireyko.movieland.entity.Genre;
import com.kireyko.movieland.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreDao genreDao;

    @Override
    public List<Genre> getAll() {
        return  genreDao.getAll();
    }

}
