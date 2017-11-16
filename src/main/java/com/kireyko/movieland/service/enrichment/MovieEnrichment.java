package com.kireyko.movieland.service.enrichment;

import com.kireyko.movieland.dao.jdbc.mapper.MovieWithCountryRowMapper;
import com.kireyko.movieland.dao.jdbc.mapper.MovieWithGenreRowMapper;
import com.kireyko.movieland.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final MovieWithGenreRowMapper movieWithGenreRowMapper = new MovieWithGenreRowMapper();
    //private final BeanPropertyRowMapper<MovieWithCountry> movieToCountryBeanPropertyRowMapper = new BeanPropertyRowMapper<>(MovieWithCountry.class);
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private String getMoviesWithCountrySQL;

    @Autowired
    private String getMoviesWithGenreSQL;

    public List<Movie> enrichMovie(List<Movie> movies) {
        ArrayList<Integer> movieIdList = getMovieList(movies);
        addCountry(movies,movieIdList );
        addGenre(movies,movieIdList );

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
            movieWithCountryMap.get(countryId).add( new Country(movieWithCountry.getCountryId(),movieWithCountry.getCountryName()));
        }
        //обновить значения country для movie
        for (Movie movie: movies ) {
            movieWithCountryMap.get(movie.getId());
            movie.setCountry(movieWithCountryMap.get(movie.getId()));
        }
    }

    private void addGenre(List<Movie> movies, ArrayList<Integer> movieIdList) {
            Map<Integer, List<Genre>> movieWithGenreMap = new HashMap<>();
            //получить лист movieid/ countries для списка
            List<MovieWithGenre> movieWithGenreList = getMovieWithGenreList(movieIdList);
            //заполнить мапу movieid/countries
            for (MovieWithGenre movieWithGenre: movieWithGenreList){
                Integer countryId = movieWithGenre.getMovieId();
                movieWithGenreMap.putIfAbsent(countryId, new ArrayList<Genre>() );
                movieWithGenreMap.get(countryId).add( new Genre(movieWithGenre.getGenreId(),movieWithGenre.getGenreName()));
            }
            //обновить значения country для movie
            for (Movie movie: movies ) {
                movieWithGenreMap.get(movie.getId());
                movie.setGenre(movieWithGenreMap.get(movie.getId()));
            }


    }

    private List<MovieWithGenre> getMovieWithGenreList(ArrayList<Integer> movieIdList) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", movieIdList);
        List<MovieWithGenre> movieWithGenre = jdbcTemplate.query(getMoviesWithGenreSQL,  parameters, movieWithGenreRowMapper);
        return movieWithGenre;
    }

    private List<MovieWithCountry> getMovieWithCountryList(ArrayList<Integer> movieIdList) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", movieIdList);
        List<MovieWithCountry> movieWithCountry = jdbcTemplate.query(getMoviesWithCountrySQL,  parameters, movieWithCountryRowMapper);
        return movieWithCountry;
    }




}
