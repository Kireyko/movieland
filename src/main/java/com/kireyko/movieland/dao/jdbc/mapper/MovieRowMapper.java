package com.kireyko.movieland.dao.jdbc.mapper;

import com.kireyko.movieland.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();

        movie.setId(resultSet.getInt("id"));
        movie.setNameRussian(resultSet.getString("nameRussian"));
        movie.setNameNative(resultSet.getString("nameNative"));
        movie.setYearOfRelease(resultSet.getInt("yearOfRelease"));
        movie.setRaiting(resultSet.getFloat("rating"));
        movie.setPrice(resultSet.getFloat("price"));
        movie.setPicturePath(resultSet.getString("picturePath"));
        movie.setDescription(resultSet.getString("description"));

        return movie;
    }
}
