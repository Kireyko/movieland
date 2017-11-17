package com.kireyko.movieland.dao.jdbc.mapper;

import com.kireyko.movieland.entity.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
        Genre genre = new Genre();
        genre.setId(resultSet.getInt("ID"));
        genre.setName(resultSet.getString("NAME"));
        return genre;
    }
}