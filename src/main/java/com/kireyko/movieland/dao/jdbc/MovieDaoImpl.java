package com.kireyko.movieland.dao.jdbc;

import com.kireyko.movieland.dao.MovieDao;
import com.kireyko.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.kireyko.movieland.dao.jdbc.sqlbuilder.SqlBuilder;
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
import java.util.Map;

@Service
public class MovieDaoImpl implements MovieDao{
    private static final Logger LOG = LoggerFactory.getLogger(MovieDaoImpl.class);
    private final MovieRowMapper movieRowMapper = new MovieRowMapper();

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
    private String getMoviesByGenreIdSQL;

    @Override
    public List<Movie> getAll(Map<String, String> parameters) {
        LOG.info("Start query to get list of movies ");
        long startTime = System.currentTimeMillis();
        String getMoviesAllWithParametersSQL = SqlBuilder.enrichQuery(getMoviesAllSQL, parameters);
        List<Movie> movies = namedJdbcTemplate.query(getMoviesAllWithParametersSQL, movieRowMapper);
        LOG.info("Finish query to get movie list from DB. It took {} ms",  System.currentTimeMillis() - startTime);
        return movies;
    }

    @Override
    public List<Movie> getMoviesRandom() {
        LOG.info("Start query to get random list of movies ");
        long startTime = System.currentTimeMillis();
        List<Movie> movies = namedJdbcTemplate.query(getMoviesRandomSQL, movieRowMapper);
        List<Movie> moviesEnriched = movieEnrichment.enrichMovie(movies);
        LOG.info("Finish query to get random movie list from DB. It took {} ms", System.currentTimeMillis() - startTime);
        return movies;
    }

    @Override
    public Movie getById(int id) {
        LOG.info("Start query to get movie with id {} from DB", id);
        long startTime = System.currentTimeMillis();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        Movie movie = namedJdbcTemplate.queryForObject(getMovieByIdSQL, parameters, movieRowMapper);
        Movie movieEnriched = movieEnrichment.enrichMovie(movie);
        LOG.info("Finish query to get movie with id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return movieEnriched;
    }


    @Override
    public List<Movie> getMoviesByGenreId(int id){
        LOG.info("Start query to get list of movies by genre ");
        long startTime = System.currentTimeMillis();

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        List<Movie> movies = namedJdbcTemplate.query(getMoviesByGenreIdSQL,parameters,  movieRowMapper);
        LOG.info("Finish query to get list of movies by genre from DB. It took {} ms",  System.currentTimeMillis() - startTime);
        return movies;
    }

}