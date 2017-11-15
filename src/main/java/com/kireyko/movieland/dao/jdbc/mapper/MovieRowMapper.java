package com.kireyko.movieland.dao.jdbc.mapper;

import com.kireyko.movieland.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();

        movie.setId(resultSet.getInt("id"));
        movie.setMoviename(resultSet.getString("moviename"));
        movie.setMovienameorig(resultSet.getString("movienameorig"));
        movie.setYear(resultSet.getInt("year"));
        movie.setRaiting(resultSet.getFloat("rating"));
        movie.setPrice(resultSet.getFloat("price"));
        movie.setPoster(resultSet.getString("poster"));

        return movie;
    }
}
