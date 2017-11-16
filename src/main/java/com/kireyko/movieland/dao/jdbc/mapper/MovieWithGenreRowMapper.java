package com.kireyko.movieland.dao.jdbc.mapper;

import com.kireyko.movieland.entity.MovieWithGenre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieWithGenreRowMapper implements RowMapper<MovieWithGenre> {

    @Override
    public MovieWithGenre mapRow(ResultSet resultSet, int i) throws SQLException {
        MovieWithGenre movieWithGenre = new MovieWithGenre();
        movieWithGenre.setMovieId(resultSet.getInt("MOVIE_ID"));
        movieWithGenre.setGenreId(resultSet.getInt("GENRE_ID"));
        movieWithGenre.setGenreName(resultSet.getString("NAME"));
        return movieWithGenre;
    }
}
