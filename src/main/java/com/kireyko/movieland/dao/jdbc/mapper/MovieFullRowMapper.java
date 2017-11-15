package com.kireyko.movieland.dao.jdbc.mapper;


import com.kireyko.movieland.entity.Country;
import com.kireyko.movieland.entity.Genre;
import com.kireyko.movieland.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MovieFullRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();
        String columnValue = null;

        Country country = new Country();
        Genre genre = new Genre();
        /*
        String[] countryArray = null;
        String[] genreArray = null;
        */
        movie.setId(resultSet.getInt("id"));
        movie.setMoviename(resultSet.getString("moviename"));
        movie.setMovienameorig(resultSet.getString("movienameorig"));
        movie.setYear(resultSet.getInt("year"));

        columnValue = resultSet.getString("country");
        /*
        countryArray= columnValue.substring(0, columnValue.length()).trim().split("\\s*,\\s*");
        movie.setCountry(countryArray);
*/
        columnValue = resultSet.getString("genre");
        /*
        genreArray= columnValue.substring(0, columnValue.length()).trim().split("\\s*,\\s*");
        movie.setGenre(genreArray);
*/

        movie.setDescription(resultSet.getString("description"));
        movie.setPoster(resultSet.getString("poster"));
        movie.setRaiting(resultSet.getFloat("rating"));
        movie.setPrice(resultSet.getFloat("price"));
        return movie;
    }
}
