package com.kireyko.movieland.dao.jdbc;

import com.kireyko.movieland.dao.MovieDao;
import com.kireyko.movieland.dao.jdbc.mapper.MovieFullRowMapper;
import com.kireyko.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.kireyko.movieland.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieDaoImpl implements MovieDao{
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MovieFullRowMapper movieFullRowMapper = new MovieFullRowMapper();
    private final MovieRowMapper movieShortRowMapper = new MovieRowMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    private String getMovieByIdSQL;

    @Autowired
    private String getMoviesAllSQL;

    @Autowired
    private String getMoviesRandomSQL;

    @Autowired
    private String addMovieSQL;

    @Override
    public Movie getById(int id) {
        log.info("Start query to get movie with id {} from DB", id);
        long startTime = System.currentTimeMillis();
        Movie movie = jdbcTemplate.queryForObject(getMovieByIdSQL, new Object[]{id}, movieFullRowMapper);
        log.info("Finish query to get movie with id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return movie;
    }



    @Override
    public List<Movie> getMoviesAll() {
        log.info("Start query to get list of movies ");
        long startTime = System.currentTimeMillis();
        List<Movie> movies = jdbcTemplate.query(getMoviesAllSQL, movieShortRowMapper);
        log.info("Finish query to get movie list from DB. It took {} ms",  System.currentTimeMillis() - startTime);
        return movies;
    }

    @Override
    public List<Movie> getMoviesRandom() {
        log.info("Start query to get random list of movies ");
        long startTime = System.currentTimeMillis();
        List<Movie> movies = jdbcTemplate.query(getMoviesRandomSQL, movieFullRowMapper);
        log.info("Finish query to get random movie list from DB. It took {} ms",  System.currentTimeMillis() - startTime);
        return movies;
    }

    @Override
    public void add(Movie movie) {
        log.info("Start query to add movie {} to DB", movie);
        long startTime = System.currentTimeMillis();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue("moviename", movie.getMoviename());
        parameterSource.addValue("movienameorig", movie.getMovienameorig());
        parameterSource.addValue("year", movie.getYear());
        parameterSource.addValue("country", movie.getCountry());
        parameterSource.addValue("genre", movie.getGenre());
        parameterSource.addValue("description", movie.getDescription());
        parameterSource.addValue("raiting", movie.getRating());
        parameterSource.addValue("price", movie.getPrice());

        namedJdbcTemplate.update(addMovieSQL, parameterSource);
        log.info("Finish query to add movie {} to DB. It took {} ms", movie, System.currentTimeMillis() - startTime);
    }
}
