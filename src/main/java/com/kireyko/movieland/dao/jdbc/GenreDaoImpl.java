package com.kireyko.movieland.dao.jdbc;

import com.kireyko.movieland.dao.GenreDao;
import com.kireyko.movieland.dao.jdbc.mapper.GenreRowMapper;
import com.kireyko.movieland.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import java.util.List;

@Repository
public class GenreDaoImpl implements GenreDao{
    private static final Logger LOG = LoggerFactory.getLogger(GenreDaoImpl.class);
    private final GenreRowMapper genreRowMapper = new GenreRowMapper();

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    private String getGenresAllSQL;

    @Override
    public List<Genre> getAll() {
        LOG.info("Start query to get list of genres from DB");
        long startTime = System.currentTimeMillis();
        List<Genre> genres = namedJdbcTemplate.query(getGenresAllSQL, genreRowMapper);
        LOG.info("Finish query to get genres list from DB. It took {} ms",  System.currentTimeMillis() - startTime);
        //LOG.debug("test debug ");
        return genres;
    }
}
