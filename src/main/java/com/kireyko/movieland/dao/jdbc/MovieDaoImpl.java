package com.kireyko.movieland.dao.jdbc;

import com.kireyko.movieland.dao.MovieDao;
import com.kireyko.movieland.dao.jdbc.mapper.GenreRowMapper;
import com.kireyko.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.kireyko.movieland.entity.Genre;
import com.kireyko.movieland.entity.Movie;
import com.kireyko.movieland.service.enrichment.MovieEnrichment;
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
    private final MovieRowMapper movieRowMapper = new MovieRowMapper();
    private final GenreRowMapper genreRowMapper = new GenreRowMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    private MovieEnrichment movieEnrichment;


    @Autowired
    private String getMovieByIdSQL;

    @Autowired
    private String getMoviesAllSQL;

    @Autowired
    private String getMoviesRandomSQL;

    @Autowired
    private String getGenresAllSQL;

    @Autowired
    private String getMoviesByGenreIdSQL;

    @Override
    public List<Movie> getMoviesAll() {
        log.info("Start query to get list of movies ");
        long startTime = System.currentTimeMillis();
        List<Movie> movies = jdbcTemplate.query(getMoviesAllSQL, movieRowMapper);
        log.info("Finish query to get movie list from DB. It took {} ms",  System.currentTimeMillis() - startTime);
        return movies;
    }

    @Override
    public List<Movie> getMoviesRandom() {
        log.info("Start query to get random list of movies ");
        long startTime = System.currentTimeMillis();
        List<Movie> movies = jdbcTemplate.query(getMoviesRandomSQL, movieRowMapper);
        //populate additional fields
        List<Movie> moviesEnriched = movieEnrichment.enrichMovie(movies);

        log.info("Finish query to get random movie list from DB. It took {} ms", System.currentTimeMillis() - startTime);
        return movies;
    }

    @Override
    public Movie getById(int id) {
        log.info("Start query to get movie with id {} from DB", id);
        long startTime = System.currentTimeMillis();
        //add enrich movie
        Movie movie = jdbcTemplate.queryForObject(getMovieByIdSQL, new Object[]{id}, movieRowMapper);
        log.info("Finish query to get movie with id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return movie;
    }

    @Override
    public List<Genre> getGenresAll() {
        log.info("Start query to get list of genres ");
        long startTime = System.currentTimeMillis();
        List<Genre> genres = jdbcTemplate.query(getGenresAllSQL, genreRowMapper);
        log.info("Finish query to get genres list from DB. It took {} ms",  System.currentTimeMillis() - startTime);
        return genres;
    }

    @Override
    public List<Movie> getMoviesByGenreId(int id){
        log.info("Start query to get list of movies by genre ");
        long startTime = System.currentTimeMillis();

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        List<Movie> movies = namedJdbcTemplate.query(getMoviesByGenreIdSQL,parameters,  movieRowMapper);
        //List<Movie> moviesEnriched = movieEnrichment.enrichMovie(movies);
        log.info("Finish query to get list of movies by genre from DB. It took {} ms",  System.currentTimeMillis() - startTime);
        return movies;
    }

}
