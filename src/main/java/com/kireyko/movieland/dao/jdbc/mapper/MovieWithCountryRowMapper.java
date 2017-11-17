package com.kireyko.movieland.dao.jdbc.mapper;

import com.kireyko.movieland.entity.MovieWithCountry;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieWithCountryRowMapper implements RowMapper<MovieWithCountry> {

    @Override
    public MovieWithCountry mapRow(ResultSet resultSet, int i) throws SQLException {
        MovieWithCountry movieWithCountry = new MovieWithCountry();
        movieWithCountry.setMovieId(resultSet.getInt("MOVIE_ID"));
        movieWithCountry.setCountryId(resultSet.getInt("COUNTRY_ID"));
        movieWithCountry.setCountryName(resultSet.getString("NAME"));
        return movieWithCountry;
    }
}