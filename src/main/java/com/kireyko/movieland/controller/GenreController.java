package com.kireyko.movieland.controller;

import com.kireyko.movieland.entity.Genre;
import com.kireyko.movieland.service.GenreService;
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
import static com.kireyko.movieland.util.JsonJacksonConverter.parseEntityToJson;

@Controller
@RequestMapping(value = "/genre", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
public class GenreController {
    private static final Logger LOG = LoggerFactory.getLogger(GenreController.class);

    @Autowired
    private GenreService genreService;

    @RequestMapping
    @ResponseBody
    public ResponseEntity<String> getAll() {
        LOG.info("Sending request to get all list of genres ");
        long startTime = System.currentTimeMillis();
        String genresAllJson = null;
        List<Genre> genres = genreService.getAll();
        if(null !=genres && genres.size()>0){
            genresAllJson = parseEntityToJson(genres);
        }
        LOG.info("Full list of genres was received. It took {} ms."+System.lineSeparator(), System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(genresAllJson, HttpStatus.OK);
    }

}
