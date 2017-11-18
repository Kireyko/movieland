package com.kireyko.movieland.controller;

import com.kireyko.movieland.entity.Genre;
import com.kireyko.movieland.entity.Movie;
import com.kireyko.movieland.service.MovieService;
import com.kireyko.movieland.util.JsonJacksonConverter;
import com.kireyko.movieland.util.JsonManualConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/movie", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
public class MovieController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService movieService;

    @Autowired
    private JsonManualConverter jsonConverter;

    @Autowired
    private JsonJacksonConverter jsonJacksonConverter;

    @RequestMapping
    @ResponseBody
    public ResponseEntity<String> getMoviesAll(@RequestParam(required = false) Map<String, String> parameters) {
        log.info("Sending request to get list of movies {} ",parameters);
        long startTime = System.currentTimeMillis();
        String moviesAllJson = null;
        List<Movie> movies = movieService.getMoviesAll( parameters);
        if(null !=movies && movies.size()>0) {
            moviesAllJson = jsonJacksonConverter.parseEntityToJson(movies);
        }
        log.info(" List of movies was received. It took {} ms."+System.lineSeparator()+" List: {}", System.currentTimeMillis() - startTime, moviesAllJson);
        return new ResponseEntity<>(moviesAllJson, HttpStatus.OK);
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET, produces = "application/json;charset=utf-8" )
    @ResponseBody
    public ResponseEntity<String> getMoviesRandom() {
        log.info("Sending request to get random list of movies ");
        long startTime = System.currentTimeMillis();
        String moviesRandomJson = null;
        List<Movie> movies = movieService.getMoviesRandom();
        if(null !=movies && movies.size()>0){
            moviesRandomJson = jsonJacksonConverter.parseEntityToJson(movies);
        }
        log.info("Random list of movies was received. It took {} ms."+System.lineSeparator()+" List: {}", System.currentTimeMillis() - startTime, moviesRandomJson);
        return new ResponseEntity<>(moviesRandomJson, HttpStatus.OK);
    }

    @RequestMapping(value="/{movieId}", method = RequestMethod.GET, produces = "application/json;charset=utf-8" )
    @ResponseBody
    public ResponseEntity<String> getMovieById(@PathVariable int movieId) {
        log.info("Sending request to get movie with id = {}", movieId);
        long startTime = System.currentTimeMillis();
        String movieJson = null;
        Movie movie = movieService.getById(movieId);
        movieJson = jsonJacksonConverter.parseEntityToJson(movie);
        log.info("Movie {} is received. It took {} ms", movieJson, System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(movieJson, HttpStatus.OK);
    }
    //to remove
    @RequestMapping(value = "/genre", method = RequestMethod.GET, produces = "application/json;charset=utf-8" )
    @ResponseBody
    public ResponseEntity<String> getGenresAll() {
        log.info("Sending request to get all list of genres ");
        long startTime = System.currentTimeMillis();
        String genresAllJson = null;
        List<Genre> genres = movieService.getGenresAll();
        if(null !=genres && genres.size()>0){
            genresAllJson = jsonJacksonConverter.parseEntityToJson(genres);
        }
        log.info("Full list of genres was received. It took {} ms."+System.lineSeparator()+" List: {}", System.currentTimeMillis() - startTime, genresAllJson);
        return new ResponseEntity<>(genresAllJson, HttpStatus.OK);
    }


    @RequestMapping(value="/genre/{genreId}", method = RequestMethod.GET, produces = "application/json;charset=utf-8" )
    @ResponseBody
    public ResponseEntity<String> getMoviesByGenreId(@PathVariable int genreId) {
        log.info("Sending request to get list of movies by genre id = {}", genreId);
        long startTime = System.currentTimeMillis();
        String moviesByGenreId = null;
        List<Movie> movie = movieService.getMoviesByGenreId(genreId);
        moviesByGenreId = jsonJacksonConverter.parseEntityToJson(movie);
        log.info("Movie {} is received. It took {} ms", moviesByGenreId, System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(moviesByGenreId, HttpStatus.OK);
    }

}
