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

    public Movie enrichMovie(Movie movie) {
        Integer movieId = movie.getId();
        addCountry(movie, movieId );
        addGenre(movie, movieId );

        return movie;
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
        //get list of  movieis/ countries for list
        List<MovieWithCountry> movieWithCountryList = getMovieWithCountryList(movieIdList);
        //fill map  movieid/countries
        for (MovieWithCountry movieWithCountry: movieWithCountryList){
            Integer movieId = movieWithCountry.getMovieId();
            movieWithCountryMap.putIfAbsent(movieId, new ArrayList<Country>() );
            movieWithCountryMap.get(movieId).add( new Country(movieWithCountry.getCountryId(),movieWithCountry.getCountryName()));
        }
        //update value of country for movie
        for (Movie movie: movies ) {
            movie.setCountries(movieWithCountryMap.get(movie.getId()));
        }
    }

    private void addCountry(Movie movie, Integer movieId) {
        Map<Integer, List<Country>> movieWithCountryMap = new HashMap<>();
        List<MovieWithCountry> movieWithCountryList = getMovieWithCountryList(movieId);
        for (MovieWithCountry movieWithCountry: movieWithCountryList){
            movieWithCountryMap.putIfAbsent(movieId, new ArrayList<Country>() );
            movieWithCountryMap.get(movieId).add( new Country(movieWithCountry.getCountryId(),movieWithCountry.getCountryName()));
        }
        movie.setCountries(movieWithCountryMap.get(movie.getId()));
    }


    private void addGenre(List<Movie> movies, ArrayList<Integer> movieIdList) {
            Map<Integer, List<Genre>> movieWithGenreMap = new HashMap<>();
            List<MovieWithGenre> movieWithGenreList = getMovieWithGenreList(movieIdList);
            for (MovieWithGenre movieWithGenre: movieWithGenreList){
                Integer movieId = movieWithGenre.getMovieId();
                movieWithGenreMap.putIfAbsent(movieId, new ArrayList<Genre>() );
                movieWithGenreMap.get(movieId).add( new Genre(movieWithGenre.getGenreId(),movieWithGenre.getGenreName()));
            }
            for (Movie movie: movies ) {
                movie.setGenres(movieWithGenreMap.get(movie.getId()));
            }
    }

    private void addGenre(Movie movie, Integer movieId) {
        Map<Integer, List<Genre>> movieWithGenreMap = new HashMap<>();
        List<MovieWithGenre> movieWithGenreList = getMovieWithGenreList(movieId);
        for (MovieWithGenre movieWithGenre: movieWithGenreList){
            movieWithGenreMap.putIfAbsent(movieId, new ArrayList<Genre>() );
            movieWithGenreMap.get(movieId).add( new Genre(movieWithGenre.getGenreId(),movieWithGenre.getGenreName()));
        }
        movie.setGenres(movieWithGenreMap.get(movie.getId()));
    }

    private List<MovieWithGenre> getMovieWithGenreList(ArrayList<Integer> movieIdList) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", movieIdList);
        List<MovieWithGenre> movieWithGenre = jdbcTemplate.query(getMoviesWithGenreSQL,  parameters, movieWithGenreRowMapper);
        return movieWithGenre;
    }

    private List<MovieWithGenre> getMovieWithGenreList(Integer movieId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", movieId);
        List<MovieWithGenre> movieWithGenre = jdbcTemplate.query(getMoviesWithGenreSQL,  parameters, movieWithGenreRowMapper);
        return movieWithGenre;
    }

    private List<MovieWithCountry> getMovieWithCountryList(ArrayList<Integer> movieIdList) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", movieIdList);
        List<MovieWithCountry> movieWithCountry = jdbcTemplate.query(getMoviesWithCountrySQL,  parameters, movieWithCountryRowMapper);
        return movieWithCountry;
    }

    private List<MovieWithCountry> getMovieWithCountryList(Integer movieId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", movieId);
        List<MovieWithCountry> movieWithCountry = jdbcTemplate.query(getMoviesWithCountrySQL,  parameters, movieWithCountryRowMapper);
        return movieWithCountry;
    }

}
