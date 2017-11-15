package com.kireyko.movieland.service.enrichment;

import com.kireyko.movieland.dao.jdbc.mapper.MovieWithCountryRowMapper;
import com.kireyko.movieland.entity.Country;
import com.kireyko.movieland.entity.Movie;
import com.kireyko.movieland.entity.MovieWithCountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieEnrichment {

    private final MovieWithCountryRowMapper movieWithCountryRowMapper = new MovieWithCountryRowMapper();
    //private final BeanPropertyRowMapper<MovieWithCountry> movieToCountryBeanPropertyRowMapper = new BeanPropertyRowMapper<>(MovieWithCountry.class);
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private String getMoviesWithCountrySQL;

    public List<Movie> enrichMovie(List<Movie> movies) {
        //ArrayList<Integer> movieIdList = null;
        ArrayList<Integer> movieIdList = getMovieList(movies);
        addCountry(movies,movieIdList );
        //addGenre(movies,movieIdList );

        return movies;
    }

    private ArrayList<Integer> getMovieList(List<Movie> movies) {
        ArrayList<Integer> movieIdList = new ArrayList<>();
        for (Movie movie: movies ) {
            movieIdList.add(movie.getId());
        }
        return movieIdList;
    }


    private void addCountry(List<Movie> movies, ArrayList<Integer> movieIdList) {
        Map<Integer, List<Country>> movieWithCountryMap = new HashMap<>();

        //получить лист movieid/ countries для списка
        List<MovieWithCountry> movieWithCountryList = getMovieWithCountryList(movieIdList);

        //заполнить мапу movieid/countries
        for (MovieWithCountry movieWithCountry: movieWithCountryList){
            Integer countryId = movieWithCountry.getMovieId();
            movieWithCountryMap.putIfAbsent(countryId, new ArrayList<Country>() );
            movieWithCountryMap.get(countryId).add( new Country(movieWithCountry.getCountryId(),movieWithCountry.getCountry()));
        }
        //обновить значения country для movie
        for (Movie movie: movies ) {
            movieWithCountryMap.get(movie.getId());
            movie.setCountry(movieWithCountryMap.get(movie.getId()));
        }


    }

    private List<MovieWithCountry> getMovieWithCountryList(ArrayList<Integer> movieIdList) {
        //!!!изменить на использование входной переменной movieIdList
        //jdbcTemplate.queryForObject(getMovieByIdSQL, new Object[]{id}, movieFullRowMapper);
        //List<MovieWithCountry> movieWithCountry = jdbcTemplate.query(getMoviesWithCountrySQL, movieWithCountryRowMapper);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", movieIdList);
        List<MovieWithCountry> movieWithCountry = jdbcTemplate.query(getMoviesWithCountrySQL,  parameters, movieWithCountryRowMapper);
        return movieWithCountry;
    }




}
