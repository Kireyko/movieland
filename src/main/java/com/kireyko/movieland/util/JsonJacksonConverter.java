package com.kireyko.movieland.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kireyko.movieland.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

public class JsonJacksonConverter {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String parseEntityToJson(T entity) {
        String entityJson = null;
        try { entityJson = objectMapper.writeValueAsString(entity);  }
        catch (JsonProcessingException e) { e.printStackTrace(); }
        return entityJson;
    }
}
