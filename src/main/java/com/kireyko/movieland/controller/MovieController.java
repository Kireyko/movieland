package com.kireyko.movieland.controller;

import com.kireyko.movieland.entity.Movie;
import com.kireyko.movieland.service.MovieService;
import com.kireyko.movieland.util.JsonJacksonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.kireyko.movieland.util.JsonJacksonConverter.parseEntityToJson;

@Controller
@RequestMapping(value = "/movie", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
public class MovieController {
    private static final Logger LOG = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @RequestMapping
    @ResponseBody
    public ResponseEntity<String> getAll(@RequestParam(required = false) Map<String, String> parameters) {
        LOG.info("Sending request to get list of movies {} ",parameters);
        long startTime = System.currentTimeMillis();
        //String moviesAllJson = null;
        List<Movie> movies = movieService.getAll( parameters);
        //if(null !=movies && movies.size()>0) {
        String moviesAllJson = parseEntityToJson(movies);
        //}
        LOG.info(" List of movies was received. It took {} ms."+System.lineSeparator(), System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(moviesAllJson, HttpStatus.OK);
    }

    @RequestMapping(value = "/random" )
    @ResponseBody
    public ResponseEntity<String> getMoviesRandom() {
        LOG.info("Sending request to get random list of movies ");
        long startTime = System.currentTimeMillis();
        String moviesRandomJson = null;
        List<Movie> movies = movieService.getMoviesRandom();
        if(null !=movies && movies.size()>0){
            moviesRandomJson = parseEntityToJson(movies);
        }
        LOG.info("Random list of movies was received. It took {} ms."+System.lineSeparator(), System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(moviesRandomJson, HttpStatus.OK);
    }

    @RequestMapping(value="/{movieId}")
    @ResponseBody
    public ResponseEntity<String> getMovieById(@PathVariable int movieId) {
        LOG.info("Sending request to get movie with id = {}", movieId);
        long startTime = System.currentTimeMillis();
        String movieJson = null;
        Movie movie = movieService.getById(movieId);
        movieJson = parseEntityToJson(movie);
        LOG.info("Movie with id {} is received. It took {} ms", movieId, System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(movieJson, HttpStatus.OK);
    }

    @RequestMapping(value="/genre/{genreId}" )
    @ResponseBody
    public ResponseEntity<String> getMoviesByGenreId(@PathVariable int genreId) {
        LOG.info("Sending request to get list of movies by genre id = {}", genreId);
        long startTime = System.currentTimeMillis();
        String moviesByGenreId = null;
        List<Movie> movie = movieService.getMoviesByGenreId(genreId);
        moviesByGenreId = parseEntityToJson(movie);
        LOG.info("Movie list for genre with id {} was received. It took {} ms", genreId, System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(moviesByGenreId, HttpStatus.OK);
    }

}
