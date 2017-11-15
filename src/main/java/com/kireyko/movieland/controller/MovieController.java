package com.kireyko.movieland.controller;

import com.kireyko.movieland.entity.Movie;
import com.kireyko.movieland.service.MovieService;
import com.kireyko.movieland.util.JsonJacksonConverter;
import com.kireyko.movieland.util.JsonManualConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1")
public class MovieController {
    private int movieid = 0;
    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final String COMMA_SEPARATOR = ",";
    private static final String COLON_SEPARATOR = ":";
    private static final String LINE_SEPARATOR = System.lineSeparator();

//    String[] movieFieldNamesShort   = {"id", "moviename", "movienameorig", "year", "price", "rating", "poster"};
//    String[] movieFieldNamesAll     = {"id", "moviename", "movienameorig", "year", "price", "rating", "poster", "description", "country", "genre"};

    @Autowired
    private MovieService movieService;

    @Autowired
    private JsonManualConverter jsonConverter;

    @Autowired
    private JsonJacksonConverter jsonJacksonConverter;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String welcome(ModelMap model) {
        model.addAttribute("movieid", ++movieid);
        log.debug("[movie_] movieid : {}", movieid);
        return "v1";
    }

    @RequestMapping(value = "/movie", method = RequestMethod.GET, produces = "application/json; charset=utf-8" )
    @ResponseBody
    public ResponseEntity<String> getMoviesAll() {
        log.info("Sending request to get list of movies ");
        long startTime = System.currentTimeMillis();
        String moviesAllJson = null;
        List<Movie> movies = movieService.getMoviesAll();
        if(null !=movies && movies.size()>0) {
            moviesAllJson = jsonJacksonConverter.parseEntityToJson(movies);
        }
        log.info(" List of movies was received. It took {} ms."+System.lineSeparator()+" List: {}", System.currentTimeMillis() - startTime, moviesAllJson);
        return new ResponseEntity<>(moviesAllJson, HttpStatus.OK);
    }

    @RequestMapping(value = "/movie/random", method = RequestMethod.GET, produces = "application/json; charset=utf-8" )
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


/*
    @RequestMapping(value="/movie/{movieId}", produces = "application/json; charset=utf-8" )
    @ResponseBody
    public ResponseEntity<String> getMovieById(@PathVariable int movieId) {
        log.info("Sending request to get movie with id = {}", movieId);
        long startTime = System.currentTimeMillis();
        Movie movie = movieService.getById(movieId);
        String movieJson = null;
        if(null !=movie ) {
            Object[] movieFieldsAll = {
                    movie.getId(), movie.getMoviename(), movie.getMovienameorig(), movie.getYear(),  movie.getPrice(),movie.getRating(), movie.getPoster(),
                    movie.getDescription(), movie.getCountry(), movie.getGenre()
            };
            movieJson = jsonConverter.toJson(movieFieldNamesAll, movieFieldsAll);
        }
        log.info("Movie {} is received. It took {} ms", movieJson, System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(movieJson, HttpStatus.OK);
    }
*/


}
