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
}
