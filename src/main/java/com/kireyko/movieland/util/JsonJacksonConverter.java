package com.kireyko.movieland.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kireyko.movieland.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsonJacksonConverter {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> String parseEntityToJson(T entity) {
        log.info("Start parsing entity to json {}", entity);
        long startTime = System.currentTimeMillis();
        String entityJson = null;
        try { entityJson = objectMapper.writeValueAsString(entity);  }
        catch (JsonProcessingException e) { e.printStackTrace(); }
        log.info("Entity {} is received. It took {} ms", entity, System.currentTimeMillis() - startTime);
        return entityJson;
    }
/*
    public Movie parseJsonToEntity(String json) {
        log.info("Start parsing entity from json {}", json);
        long startTime = System.currentTimeMillis();
        Movie movie = parseValue(json, Movie.class);
        log.info("Movie {} is received. It took {} ms", movie, System.currentTimeMillis() - startTime);
        return movie;
    }

    private <T> T parseValue(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
*/
}
